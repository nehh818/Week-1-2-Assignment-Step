// Simple token bucket rate limiter

import java.util.*;

class TokenBucket {

    int tokens;
    long lastRefill;

    TokenBucket(int max) {
        tokens = max;
        lastRefill = System.currentTimeMillis();
    }
}

public class Program6RateLimiter {

    static HashMap<String, TokenBucket> clients = new HashMap<>();
    static int LIMIT = 1000;

    public static boolean checkRateLimit(String clientId) {

        clients.putIfAbsent(clientId, new TokenBucket(LIMIT));

        TokenBucket bucket = clients.get(clientId);

        long now = System.currentTimeMillis();

        if (now - bucket.lastRefill > 3600000) {
            bucket.tokens = LIMIT;
            bucket.lastRefill = now;
        }

        if (bucket.tokens > 0) {
            bucket.tokens--;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(checkRateLimit("abc123"));
        System.out.println(checkRateLimit("abc123"));
    }
}