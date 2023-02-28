import java.util.HashMap;
import java.util.Map;

public class WordSuggester {
    Map<String, Map<String, Integer>> map;
    int numOfWords;

    public WordSuggester(int numOfWords) {
        this.map = new HashMap<>();
        this.numOfWords = numOfWords;
    }

    public void addData(String data) {
        String[] words = data.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s");
        for (int i = 0; i < words.length - 1; i++) {
            String currentWord = words[i];
            String nextWord = words[i + 1];
            Map<String, Integer> wordFreq = this.map.getOrDefault(currentWord, new HashMap<>());
            wordFreq.put(nextWord, wordFreq.getOrDefault(nextWord, 0) + 1);
            this.map.put(currentWord, wordFreq);
        }
    }

    public String getPredictions(String input) {
        String[] tokens = input.split("\\s+");
        String context = tokens[tokens.length - 1];
        Map<String, Integer> wordFreq = this.map.get(context);

        if (wordFreq == null) {
            return null;
        }

        String[] words = new String[this.numOfWords];
        int[] freqs = new int[this.numOfWords];
        for (String word : wordFreq.keySet()) {
            int freq = wordFreq.get(word);
            for (int i = 0; i < this.numOfWords; i++) {
                if (freq > freqs[i]) {
                    for (int j = this.numOfWords - 1; j > i; j--) {
                        words[j] = words[j - 1];
                        freqs[j] = freqs[j - 1];
                    }
                    words[i] = word;
                    freqs[i] = freq;
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word != null) {
                sb.append(word).append(" ");
            }
        }
        return sb.toString().trim();
    }
}