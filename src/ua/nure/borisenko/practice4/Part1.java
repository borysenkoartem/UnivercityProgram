package ua.nure.borisenko.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part1 {

    private static final String REGEX = "[A-Za-zÀ-ßà-ÿ¸¨¡¢¯¿²³ªº¥´]{4,}";
    private static final String PATH = "src/ua/nure/borisenko/practice4/";

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(PATH + "part1.txt"), Charset.forName("windows-1251"))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(sb.toString());

        StringBuffer sb1 = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb1, reverseCase(m.group()));
        }
        m.appendTail(sb1);
        System.out.println(sb1);

    }

    public static String reverseCase(String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (Character.isUpperCase(c)) {
                chars[i] = Character.toLowerCase(c);
            } else if (Character.isLowerCase(c)) {
                chars[i] = Character.toUpperCase(c);
            }
        }
        return new String(chars);
    }
}
