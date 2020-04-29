package ua.nure.borisenko.practice6.part6;


import java.util.LinkedHashMap;
import java.util.Map;

public class Part61 {
    private static final String REGEX = "\\s";
    private String inText;

    public Part61(String inText) {
        this.inText = inText;
    }

    public void find() {
        String[] text = inText.split(REGEX);
        Map<String, Integer> map = new LinkedHashMap<>();
        for (String word : text) {
            int k = 1;
            if (map.containsKey(word)) {
                k = map.get(word) + 1;
            }
            map.put(word, k);
        }
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .sorted(Map.Entry.<String, Integer>comparingByKey().reversed())
                .forEach(k -> System.out.println(k.getKey() + " ==> " + k.getValue()));
    }

}

