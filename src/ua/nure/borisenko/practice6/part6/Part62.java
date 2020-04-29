package ua.nure.borisenko.practice6.part6;

import java.util.LinkedHashMap;
import java.util.Map;

public class Part62 {
    private static final String REGEX = "\\s";
    private String inText;
    public Part62(String inText) {
        this.inText = inText;
    }
    public void find() {
        String[] text = inText.split(REGEX);
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String word : text) {
            map.put(word, word.length());
        }
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(k -> System.out.println(k.getKey() + " ==> " + k.getValue()));
    }


}
