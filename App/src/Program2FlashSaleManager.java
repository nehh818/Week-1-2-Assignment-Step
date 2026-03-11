// Program to simulate a flash sale inventory manager
// using HashMap for fast stock lookup

import java.util.*;

public class Program2FlashSaleManager {

    static HashMap<String, Integer> stock = new HashMap<>();
    static LinkedList<Integer> waitingList = new LinkedList<>();

    // check stock instantly
    public static void checkStock(String productId) {
        int count = stock.getOrDefault(productId, 0);
        System.out.println(productId + " → " + count + " units available");
    }

    // synchronized so multiple users don't reduce stock at same time
    public synchronized static void purchaseItem(String productId, int userId) {

        int count = stock.getOrDefault(productId, 0);

        if (count > 0) {
            stock.put(productId, count - 1);
            System.out.println("User " + userId + " purchase success. Remaining: " + (count - 1));
        } else {
            waitingList.add(userId);
            System.out.println("User " + userId + " added to waiting list. Position #" + waitingList.size());
        }
    }

    public static void main(String[] args) {

        stock.put("IPHONE15_256GB", 100);

        checkStock("IPHONE15_256GB");

        purchaseItem("IPHONE15_256GB", 12345);
        purchaseItem("IPHONE15_256GB", 67890);

        // simulate stock finished
        stock.put("IPHONE15_256GB", 0);
        purchaseItem("IPHONE15_256GB", 99999);
    }
}