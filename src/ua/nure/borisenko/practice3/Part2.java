package ua.nure.borisenko.practice3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Part2 {

	private static final String REGEX = "[\\s|,|-|']+|-";
	private static final int MAX_WORD_VALUE = 100;

	public static void main(String[] args) {

		String text = Util.readFile("part2.txt");
		System.out.println(convert(text));

	}

	public static String convert(String input) {

		StringBuilder sbMin = new StringBuilder();
		StringBuilder sbMax = new StringBuilder();
		String[] textArray = input.split(REGEX);

		int min = MAX_WORD_VALUE;
		int max = 0;

		for(int i = 0; i<textArray.length;i++) {
			
		
			if (min > textArray[i].length()) {
				min = textArray[i].length();
			}
			if (max < textArray[i].length()) {
				max = textArray[i].length();
			}

		}
		for (String a : textArray) {
			if (a.length() == min) {
				sbMin.append(a).append(" ");
			}
			if (a.length() == max) {
				sbMax.append(a).append(" ");
			}
		}
		String minString = "Min: "
				+ Arrays.stream(sbMin.toString().split("\\s+")).distinct().collect(Collectors.joining(", "));

		String maxString = "Max: "
				+ Arrays.stream(sbMax.toString().split("\\s+")).distinct().collect(Collectors.joining(", "));

		return minString + System.lineSeparator() + maxString;
	}

}
