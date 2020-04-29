package ua.nure.borisenko.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String REGEX_TEXT = "\\s";
    private static final String REGEX_STRING = "[A-Za-zА-Яа-яёЁЎўЇїІіЄєҐґ]{2,}";
    private static final String REGEX_DOUBLE = "[0-9]{0,}\\.+[0-9]{0,}";
    public static final String REGEX_INT = "(?m)(\\s\\b\\d+\\s)";
    public static final String REGEX_CHAR = "[A-Za-zА-Яа-яёЁЎўЇїІіЄєҐґ]";
    private static final String PATH = "src/ua/nure/borisenko/practice4/";

    public static void main(String[] args) {

        String text = readText(PATH + "part3.txt");
        Scanner scanner = new Scanner(System.in);
        boolean stop = false;
        while (!stop) {
            String s = scanner.nextLine();
            switch (s) {
                case "char":
                    System.out.println(findChar(text));
                    break;
                case "String":
                    System.out.println(find(text, REGEX_STRING));
                    break;
                case "int":
                    System.out.println(find(text, REGEX_INT));
                    break;
                case "double":
                    System.out.println(find(text, REGEX_DOUBLE));
                    break;
                case "stop":
                    scanner.close();
                    stop = true;
                    break;
                default:
                    System.out.println("Incorrect input");
            }
        }
    }

    private static String readText(String textPath) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(textPath), Charset.forName("windows-1251"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return sb.toString();
    }

    private static String findChar(String input) {
        StringBuilder result = new StringBuilder();
        String[] text = input.split(REGEX_TEXT);
        for (String s : text) {
            if (s.length() == 1) {
                result.append(s).append(" ");
            }
        }
        if (result.length() == 0) {
            return "No such values";
        }
        return result.toString();
    }

    private static String find(String input, String regex) {
        StringBuilder result = new StringBuilder();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()) {
            result.append(m.group().trim()).append(" ");
        }
        if (result.length() == 0) {
            return "No such values";
        }
        return result.toString();
    }
}
