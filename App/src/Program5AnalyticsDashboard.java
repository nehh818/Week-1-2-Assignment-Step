// Real time website analytics simulation

import java.util.*;

public class Program5AnalyticsDashboard {

    static HashMap<String, Integer> pageViews = new HashMap<>();
    static HashMap<String, Set<String>> uniqueVisitors = new HashMap<>();
    static HashMap<String, Integer> sources = new HashMap<>();

    public static void processEvent(String url, String userId, String source) {

        pageViews.put(url, pageViews.getOrDefault(url, 0) + 1);

        uniqueVisitors.putIfAbsent(url, new HashSet<>());
        uniqueVisitors.get(url).add(userId);

        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    public static void getDashboard() {

        System.out.println("Top Pages:");

        for (String url : pageViews.keySet()) {

            System.out.println(url + " - " + pageViews.get(url)
                    + " views (" + uniqueVisitors.get(url).size() + " unique)");
        }

        System.out.println("\nTraffic Sources:");

        for (String s : sources.keySet()) {
            System.out.println(s + ": " + sources.get(s));
        }
    }

    public static void main(String[] args) {

        processEvent("/news", "user1", "google");
        processEvent("/news", "user2", "facebook");
        processEvent("/sports", "user3", "direct");

        getDashboard();
    }
}