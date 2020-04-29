package ua.nure.borisenko.practice3;


import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
	private static final String REGEX = "(?m)^(.*);(.*)( )(.*);(.*)(@)(.*)";
	private static final String REGEX_WITHOUT_DOMEN = "(?m)^(.*);(.*);(.*)";
	private static final int LOGIN = 1;
	private static final int FIRST_NAME = 2;
	private static final int LAST_NAME = 4;
	private static final int MAIL = 5;
	private static final int AT = 6;
	private static final int DOMEN = 7;
	private static final int FOR_RANDOM = 8999;
	private static final int FOR_RANDOM1 = 1000;

	public static void main(String[] args) {
		String text = Util.readFile("part1.txt");
		System.out.println(convert4(text));

	}

	public static String convert1(String input) {

		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(withOur1Line(input));
		while (matcher.find()) {
			result.append(matcher.group(LOGIN)).append(": ").append(matcher.group(MAIL)).append(matcher.group(AT))
					.append(matcher.group(DOMEN)).append(System.lineSeparator());
		}
		return result.toString();
	}

	public static String convert2(String input) {
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(withOur1Line(input));
		while (matcher.find()) {
			result.append(matcher.group(LAST_NAME)).append(" ").append(matcher.group(FIRST_NAME)).append(" (email: ")
					.append(matcher.group(MAIL)).append(matcher.group(AT)).append(matcher.group(DOMEN)).append(")")
					.append(System.lineSeparator());
		}
		return result.toString();
	}

	public static String convert3(String input) {

		StringBuilder sb = new StringBuilder();

		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(withOur1Line(input));

		while (matcher.find()) {

			sb.append(matcher.group(DOMEN)).append(" ==> ");

			Pattern p = Pattern.compile(REGEX_WITHOUT_DOMEN + matcher.group(DOMEN));
			Matcher m = p.matcher(withOur1Line(input));

			while (m.find()) {
				sb.append(m.group(LOGIN)).append(", ");
			}
			sb.deleteCharAt(sb.length() - 2);
			sb.deleteCharAt(sb.length() - 1);
			sb.append(System.lineSeparator());
		}
		return removeDuplicates(sb.toString());

	}

	public static String convert4(String input) {
		SecureRandom rand = new SecureRandom();
		
		StringBuilder result = new StringBuilder();
		String[] text1 = input.split(System.lineSeparator());
		result.append(text1[0] + ";Password" + System.lineSeparator());
		for (int i = 1; i < text1.length; i++) {
			result.append(text1[i] + ';' + (rand.nextInt(FOR_RANDOM)+FOR_RANDOM1)+ System.lineSeparator());
		}

		return result.toString();
	}

	private static String withOur1Line(String incomeText) {

		StringBuilder sb = new StringBuilder();
		String[] text1 = incomeText.split(System.lineSeparator());

		for (int i = 1; i < text1.length; i++) {
			sb.append(text1[i] + System.lineSeparator());
		}
		return sb.toString();
	}

	private static String removeDuplicates(String str) {

		String[] arr = str.split(System.lineSeparator());
		String[] temp = new String[arr.length];
		StringBuilder result = new StringBuilder();
		int k = 0;
		boolean isDuplicate = false;

		for (int i = 0; i < arr.length; i++) {
			isDuplicate = false;
			for (String s : temp) {

				if (arr[i].equals(s)) {
					isDuplicate = true;
				}
			}
			if (!isDuplicate) {
				temp[k] = arr[i];
				k++;
			}
		}
		for (int i = 0; i < k; i++) {
			result.append(temp[i]).append(System.lineSeparator());
		}
		return result.toString();
	}
}
