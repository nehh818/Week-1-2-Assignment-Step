// Simple program to simulate a Social Media Username Availability Checker
// Idea: use HashMap for fast O(1) lookup of usernames
// Also keep track of how many times people tried a username

import java.util.*;

class UsernameChecker {

    // HashMap to store username -> userId
    // if username exists here, it means the username is already taken
    HashMap<String, Integer> users;

    // HashMap to store username -> number of attempts
    // this helps us track popularity of attempted usernames
    HashMap<String, Integer> attempts;

    // constructor
    UsernameChecker() {
        users = new HashMap<>();
        attempts = new HashMap<>();

        // adding some existing users (like already registered users)
        users.put("john_doe", 101);
        users.put("admin", 102);
        users.put("user123", 103);
    }

    // method to check if username is available
    public boolean checkAvailability(String username) {

        // update attempt count
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);

        // check if username already exists
        if (users.containsKey(username)) {
            return false; // already taken
        }

        return true; // available
    }

    // method to suggest alternative usernames
    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        // simple idea: add numbers or change characters
        suggestions.add(username + "1");
        suggestions.add(username + "2");
        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    // method to find the most attempted username
    public String getMostAttempted() {

        String mostAttempted = "";
        int max = 0;

        for (String name : attempts.keySet()) {

            if (attempts.get(name) > max) {
                max = attempts.get(name);
                mostAttempted = name;
            }
        }

        return mostAttempted + " (" + max + " attempts)";
    }
}

public class Program1UsernameChecker {

    public static void main(String[] args) {

        // creating object of checker
        UsernameChecker checker = new UsernameChecker();

        // checking username availability
        System.out.println("checkAvailability(\"john_doe\") → "
                + checker.checkAvailability("john_doe"));

        System.out.println("checkAvailability(\"jane_smith\") → "
                + checker.checkAvailability("jane_smith"));

        // showing suggestions
        System.out.println("suggestAlternatives(\"john_doe\") → "
                + checker.suggestAlternatives("john_doe"));

        // simulate more attempts
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");
        checker.checkAvailability("admin");

        // most attempted username
        System.out.println("getMostAttempted() → "
                + checker.getMostAttempted());
    }
}