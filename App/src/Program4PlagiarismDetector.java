// Basic plagiarism detection using n-grams and HashMap

import java.util.*;

public class Program4PlagiarismDetector {

    static HashMap<String, Set<String>> ngramMap = new HashMap<>();
    static int N = 5;

    // break document into n-grams
    public static List<String> extractNgrams(String text) {

        String[] words = text.split(" ");
        List<String> grams = new ArrayList<>();

        for (int i = 0; i <= words.length - N; i++) {

            String gram = "";

            for (int j = 0; j < N; j++) {
                gram += words[i + j] + " ";
            }

            grams.add(gram.trim());
        }

        return grams;
    }

    public static void addDocument(String docId, String text) {

        List<String> grams = extractNgrams(text);

        for (String g : grams) {

            ngramMap.putIfAbsent(g, new HashSet<>());
            ngramMap.get(g).add(docId);
        }
    }

    public static void analyze(String docId, String text) {

        List<String> grams = extractNgrams(text);
        HashMap<String, Integer> matches = new HashMap<>();

        for (String g : grams) {

            if (ngramMap.containsKey(g)) {

                for (String d : ngramMap.get(g)) {

                    matches.put(d, matches.getOrDefault(d, 0) + 1);
                }
            }
        }

        for (String d : matches.keySet()) {

            double sim = matches.get(d) * 100.0 / grams.size();
            System.out.println("Similarity with " + d + ": " + sim + "%");
        }
    }

    public static void main(String[] args) {

        addDocument("essay1", "machine learning is a powerful tool in data science");

        analyze("essay2", "machine learning is a powerful tool for big data");
    }
}