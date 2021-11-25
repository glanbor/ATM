package task2613.command;


import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class ExitCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH + "exit");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("exit.question.y.n"));
        String responce = ConsoleHelper.readString();
        if (responce != null && responce.equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage(res.getString("thank.message"));

    }
}
