// Simple DNS Cache simulation with TTL

import java.util.*;

class DNSEntry {
    String ip;
    long expiry;

    DNSEntry(String ip, int ttl) {
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttl * 1000;
    }
}

public class Program3DNSCache {

    static HashMap<String, DNSEntry> cache = new HashMap<>();
    static int hits = 0;
    static int misses = 0;

    public static String resolve(String domain) {

        if (cache.containsKey(domain)) {
            DNSEntry entry = cache.get(domain);

            if (System.currentTimeMillis() < entry.expiry) {
                hits++;
                return "Cache HIT → " + entry.ip;
            } else {
                cache.remove(domain);
                System.out.println("Cache EXPIRED");
            }
        }

        misses++;

        // fake upstream DNS query
        String ip = "172.217.14." + new Random().nextInt(255);

        cache.put(domain, new DNSEntry(ip, 300));

        return "Cache MISS → " + ip;
    }

    public static void getStats() {
        int total = hits + misses;
        double rate = (total == 0) ? 0 : (hits * 100.0 / total);
        System.out.println("Hit Rate: " + rate + "%");
    }

    public static void main(String[] args) {

        System.out.println(resolve("google.com"));
        System.out.println(resolve("google.com"));

        getStats();
    }
}