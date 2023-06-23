import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class WordsCounter {

    private final File file;
    private HashMap<String, Integer> wordsMap = new HashMap<>();

    public WordsCounter(File file) {
        this.file = file;
    }

    public WordsCounter(String filePath) {
        this(new File(filePath));
    }

    public WordsCounter() {
        this(new File("src//main//resources//words.txt"));
    }

    public String countWordsInFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                countWordsInLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return makeSortedString();
    }

    private void countWordsInLine(String line) {
        String[] words = line.split(" ");
        for (String word : words) {
            if (wordsMap.containsKey(word)) {
                wordsMap.replace(word, wordsMap.get(word) + 1);
            } else {
                wordsMap.put(word, 1);
            }
        }
    }

    private String makeSortedString() {
        StringBuilder builder = new StringBuilder();
        while (!wordsMap.isEmpty()) {
            int max = 0;
            String maxKey = null;
            for (String key : wordsMap.keySet()) {
                if (max < wordsMap.get(key)) {
                    max = wordsMap.get(key);
                    maxKey = key;
                }
            }
            builder.append(maxKey).append(" ").append(wordsMap.get(maxKey)).append("\n");
            wordsMap.remove(maxKey);
        }
        return builder.toString();
    }

}
