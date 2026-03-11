// Basic autocomplete using HashMap

import java.util.*;

public class Program7Autocomplete {

    static HashMap<String, Integer> queries = new HashMap<>();

    public static void addQuery(String q) {
        queries.put(q, queries.getOrDefault(q, 0) + 1);
    }

    public static void search(String prefix) {

        System.out.println("Suggestions:");

        for (String q : queries.keySet()) {

            if (q.startsWith(prefix)) {
                System.out.println(q + " (" + queries.get(q) + " searches)");
            }
        }
    }

    public static void main(String[] args) {

        addQuery("java tutorial");
        addQuery("javascript");
        addQuery("java download");

        search("jav");
    }
}