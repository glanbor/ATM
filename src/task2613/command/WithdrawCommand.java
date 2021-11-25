package task2613.command;



import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;
import task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"withdraw" );

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String s = ConsoleHelper.readString();
            if (s == null) ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            else {
                try {
                    int sum = Integer.parseInt(s);
                    if (currencyManipulator.isAmountAvailable(sum)) {
                        Map<Integer, Integer> forWithdrawing = currencyManipulator.withdrawAmount(sum);
                        for (Map.Entry<Integer, Integer> entry : forWithdrawing.entrySet()) {
                            ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                        }
                        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), sum, currencyCode));
                        break;
                    }
                    else ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }

            }
        }
    }
}
