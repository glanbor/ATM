package task2613.command;


import task2613.CashMachine;
import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"info" );

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        int money = 0;
        for (CurrencyManipulator cm : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
            if (cm.hasMoney()) {
                int totalAmount = cm.getTotalAmount();
                ConsoleHelper.writeMessage(cm.getCurrencyCode() + " - " + totalAmount);
                money += totalAmount;
            }
        }
        if (money == 0) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
