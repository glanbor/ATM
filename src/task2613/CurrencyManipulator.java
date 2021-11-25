package task2613;



import task2613.exception.NotEnoughMoneyException;

import java.util.*;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new HashMap<>();

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int countForAdding = denominations.get(denomination);
            countForAdding += count;
            denominations.put(denomination, countForAdding);
        } else denominations.put(denomination, count);
    }

    public int getTotalAmount() {
        return denominations.entrySet().stream()
                .mapToInt(x -> x.getKey() * x.getValue())
                .sum();
    }

    public boolean hasMoney() {
        return getTotalAmount() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        List<Integer> allBanknotes = getAllBanknotes();
        Map<Integer, Integer> mapSums = getMapSums(expectedAmount, allBanknotes);
        if (!mapSums.containsKey(expectedAmount))
            throw new NotEnoughMoneyException();

        Map<Integer, Integer> banknotesExpectedAmount = getBanknotesExpectedAmount(expectedAmount, mapSums);
        return banknotesExpectedAmount;
    }

    private List<Integer> getAllBanknotes() {
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e : denominations.entrySet()) {
            for (int i = 0; i < e.getValue(); i++)
                result.add(e.getKey());
        }
        Collections.sort(result);
        return result;
    }

    private Map<Integer, Integer> getMapSums(Integer expectedAmount, List<Integer> allBanknotes) {
        Map<Integer, Integer> result = new TreeMap<>();
        result.put(0, 0);
        for (Integer a : allBanknotes) {
            Map<Integer, Integer> map = new TreeMap<>();
            for (Map.Entry<Integer, Integer> e : result.entrySet()) {
                int newKey = e.getKey() + a;
                if (newKey > expectedAmount) continue;
                map.put(newKey, a);
            }
            result.putAll(map);
        }
        return result;
    }


    private Map<Integer, Integer> getBanknotesExpectedAmount(Integer expectedAmount, Map<Integer, Integer> mapSums) {
        Map<Integer, Integer> result = new TreeMap<>(Collections.reverseOrder());
        while (expectedAmount > 0) {
            int banknote = mapSums.get(expectedAmount);
            if (result.containsKey(banknote)) {
                result.put(banknote, result.get(banknote) + 1);
            } else {
                result.put(banknote, 1);
            }
            expectedAmount -= banknote;
            removeDenominations(banknote);
        }
        return result;
    }

    private void removeDenominations(int banknote) {
        if (denominations.get(banknote) > 0)
            denominations.put(banknote, denominations.get(banknote) - 1);
        else
            denominations.remove(banknote);

    }

/*    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        HashMap<Integer, Integer> result = new LinkedHashMap<>();
        List<Integer> temp = new ArrayList<>();
        for (Integer integer : denominations.keySet()) {
            int value = denominations.get(integer);
            do {
                temp.add(integer);
                value--;
            } while (value > 0);
        }
        Collections.sort(temp, Collections.reverseOrder());
        for (int i = 0; i < temp.size() - 1; i++) {
            int sum = temp.get(i);
            result.put(temp.get(i), 1);
            for (int j = i + 1; j < temp.size(); j++) {
                if (sum + temp.get(j) <= expectedAmount) {
                    sum += temp.get(j);
                    result.merge(temp.get(j), 1, Integer::sum);
                }
                if (sum == expectedAmount) {
                    result.forEach(
                            (k, v) -> denominations.merge(k, v, (v1, v2) -> v1 - v2)
                    );
                    return result;
                }
            }
            result.clear();
        }
        throw new NotEnoughMoneyException();
    }

 */
}
