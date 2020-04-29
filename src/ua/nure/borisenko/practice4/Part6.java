package ua.nure.borisenko.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
	private static final String LATN = "[A-Za-z]+";
	private static final String CYRL = "[À-ßà-ÿ¸¨¡¢¯¿²³ªº¥´]+";

	public static void main(String[] args) {

		String text = readText("part6.txt");

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			if ("stop".equalsIgnoreCase(s)) {
				scanner.close();
				break;
			}
			System.out.println(s + ": " + find(text, s));
		}
	}

	private static String find(String input, String how) {

		if ("Latn".equalsIgnoreCase(how)) {
			how = LATN;
		}
		if ("Cyrl".equalsIgnoreCase(how)) {
			how = CYRL;
		}
		if (how.equals(CYRL) || how.equals(LATN)) {
			StringBuilder result = new StringBuilder();
			Pattern pattern = Pattern.compile(how);
			Matcher matcher = pattern.matcher(input);
			while (matcher.find()) {
				result.append(matcher.group());
				result.append(" ");
			}
			return result.toString();
		} else {
			return "Incorrect input";
		}
	}

	private static String readText(String textPath) {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(textPath), Charset.forName("windows-1251"))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		return sb.toString();
	}
}
