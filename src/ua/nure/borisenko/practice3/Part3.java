package ua.nure.borisenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

	private static final String REGEX = "[A-Za-zÀ-ßà-ÿ¸¨úÚ]{3,}";

	public static void main(String[] args) {

		String text = Util.readFile("part3.txt");
		System.out.println(convert(text));
	}

	public static String convert(String input) {
		
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(input);

		StringBuffer sb1 = new StringBuffer();

		while (m.find()) {
			m.appendReplacement(sb1, reverseCase(m.group()));
		}
		m.appendTail(sb1);

		return sb1.toString();
	}

	public static String reverseCase(String word) {
		char[] chars = word.toCharArray();
		if (Character.isUpperCase(chars[0])) {
			chars[0] = Character.toLowerCase(chars[0]);
		} else if (Character.isLowerCase(chars[0])) {
			chars[0] = Character.toUpperCase(chars[0]);
		}
		return new String(chars);
	}
}
