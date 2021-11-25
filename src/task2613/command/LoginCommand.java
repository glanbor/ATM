package task2613.command;



import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command {

    private ResourceBundle validCreditCards = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"login" );

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        String creditCardNumber = ConsoleHelper.readString().trim();
        String pinCode = ConsoleHelper.readString().trim();
        if (creditCardNumber == null || pinCode == null
                || creditCardNumber.length() != 12 || pinCode.length() != 4) {
            ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
            execute();
        } else {
            if (!validCreditCards.containsKey(creditCardNumber)
                    || !validCreditCards.getString(creditCardNumber).equals(pinCode)) {
                ConsoleHelper.writeMessage(res.getString("not.verified.format"));
                execute();
            } else {
                ConsoleHelper.writeMessage(res.getString("success.format"));
            }
        }
    }
}
