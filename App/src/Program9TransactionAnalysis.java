// Two sum detection for transactions

import java.util.*;

class Transaction {
    int id;
    int amount;

    Transaction(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }
}

public class Program9TransactionAnalysis {

    public static void findTwoSum(List<Transaction> list, int target) {

        HashMap<Integer, Transaction> map = new HashMap<>();

        for (Transaction t : list) {

            int complement = target - t.amount;

            if (map.containsKey(complement)) {

                System.out.println("Pair Found: "
                        + map.get(complement).id + " & " + t.id);
                return;
            }

            map.put(t.amount, t);
        }

        System.out.println("No pair found");
    }

    public static void main(String[] args) {

        List<Transaction> list = new ArrayList<>();

        list.add(new Transaction(1, 500));
        list.add(new Transaction(2, 300));
        list.add(new Transaction(3, 200));

        findTwoSum(list, 500);
    }
}