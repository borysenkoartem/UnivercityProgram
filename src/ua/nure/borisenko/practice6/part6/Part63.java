package ua.nure.borisenko.practice6.part6;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Part63 {
    private static final String REGEX = "\\s";
    private String inText;

    public Part63(String inText) {
        this.inText = inText;
    }
    public void find() {
        String[] text = inText.split(REGEX);
        Map<String, Integer> map = new LinkedHashMap<>();
        List<String> result = new ArrayList<>();
        for (String word : text) {
            int k = 1;
            if (map.containsKey(word)) {
                k = map.get(word) + 1;
            }
            map.put(word, k);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                result.add(entry.getKey());
            }
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(reversWord(result.get(i)));
        }
    }

    private static String reversWord(String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i > -1; i--) {
            sb.append(Character.toUpperCase(word.charAt(i)));
        }
        return sb.toString();
    }
}

