package ua.nure.borisenko.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
	private static final int LOCALE = 1;
	private static final int WORD = 0;

	public static void main(String[] args) {
		System.out.println("Insert word and locale or insert exit to close");
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextLine()) {
			String s = scanner.nextLine();
			if ("stop".equalsIgnoreCase(s)) {
				scanner.close();
				break;
			}
			System.out.println(changeWord(s));
		}
	}

	private static String changeWord(String input) {
		String[] in = input.split("\\s");
		if (in.length != 2) {
			return "No such values";
		}
		Locale locale = new Locale(in[LOCALE]);
		ResourceBundle bundle = ResourceBundle.getBundle("resources", locale);
		try {
			return bundle.getString(in[WORD]);

		} catch (MissingResourceException e) {
			return "No such values";
		}

	}
}
