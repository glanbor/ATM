package task2613;



import task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static ResourceBundle res = ResourceBundle.getBundle(
            CashMachine.RESOURCE_PATH +"common_en" );

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String s = "";
        try {
            s = bis.readLine();
            if (s.equalsIgnoreCase("EXIT")) {

                throw new InterruptOperationException();
            }
        } catch (IOException e) {

        }

        return s;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        writeMessage(res.getString("choose.currency.code"));
        String code = readString().trim();
        if (code.length() == 3) {
            return code.toUpperCase();
        } else {
            writeMessage(res.getString("invalid.data"));
            return askCurrencyCode();
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] twoDigits = new String[2];
        writeMessage(res.getString("choose.denomination.and.count.format"));
        String s = readString().trim();
        if (s.matches("\\d+ \\d+")) {
            twoDigits = s.split(" ");
            return twoDigits;
        } else {
            writeMessage(res.getString("invalid.data"));
            return getValidTwoDigits(currencyCode);
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("choose.operation"));
        ConsoleHelper.writeMessage("\t 1 - operation." + res.getString("operation.INFO"));
        ConsoleHelper.writeMessage("\t 2 - operation." + res.getString("operation.DEPOSIT"));
        ConsoleHelper.writeMessage("\t 3 - operation." + res.getString("operation.WITHDRAW"));
        ConsoleHelper.writeMessage("\t 4 - operation." + res.getString("operation.EXIT"));
        try {
            String op = readString().trim();
            int operation = Integer.parseInt(op);
            return Operation.getAllowableOperationByOrdinal(operation);
        } catch (IllegalArgumentException e) {
            writeMessage(res.getString("invalid.data"));
            return askOperation();
        }
    }
    public static void printExitMessage() {
        ConsoleHelper.writeMessage(res.getString("the.end"));
    }
}
