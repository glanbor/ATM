package task2613.command;



import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"deposit" );
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] strings = ConsoleHelper.getValidTwoDigits(currencyCode);
        int denomination = Integer.parseInt(strings[0]);
        int amount = Integer.parseInt(strings[1]);
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        currencyManipulator.addAmount(denomination, amount);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), denomination*amount, currencyCode));
    }
}
