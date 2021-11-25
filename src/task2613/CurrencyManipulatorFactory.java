package task2613;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        currencyCode = currencyCode.toUpperCase();
        if (!map.containsKey(currencyCode)) {
            CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode);
            map.put(currencyCode, currencyManipulator);
            return currencyManipulator;
        }
        else {
            return map.get(currencyCode);
        }
    }
    public static Collection <CurrencyManipulator> getAllCurrencyManipulators() {
        Collection <CurrencyManipulator> allCurrencyManipulators = new ArrayList<>();
        for (Map.Entry entry : map.entrySet()) {
            allCurrencyManipulators.add((CurrencyManipulator) entry.getValue());
        }
        return allCurrencyManipulators;
    }
}
