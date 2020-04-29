package ua.nure.borisenko.practice3;

import java.util.Locale;

public class Part5 {
	private static final int MAX_VALUE = 100;
	private static final int ROMAN_DISMAL_C = 100;
	private static final int ROMAN_DISMAL_L = 50;
	private static final int ROMAN_DISMAL_X = 10;
	private static final int ROMAN_DISMAL_V = 5;
	private static final int ROMAN_DISMAL_I = 1;

	public static void main(String[] args) {
		System.out.println(decimal2Roman(2));
		System.out.println(roman2Decimal("XIV"));
	}

	public static String decimal2Roman(int x) {

		if (x > MAX_VALUE) {
			throw new IllegalArgumentException();
		}
		if (x == MAX_VALUE) {
			return "C";
		}
		String[] ch = Integer.toString(x).split("");
		StringBuilder sb = new StringBuilder();
		if (ch.length == 1) {
			sb.append(changeDismal(ch[0]));
		}
		if (ch.length == 2) {
			sb.append(changeDismall(ch[0]));
			sb.append(changeDismal(ch[1]));
		}

		return sb.toString();
	}

	public static int roman2Decimal(String s) {
		int number = 0;
		int lastNumber = 0;
		String romanNumeral = s.toUpperCase(Locale.getDefault());

		for (int x = romanNumeral.length() - 1; x >= 0; x--) {
			char convertToDecimal = romanNumeral.charAt(x);

			switch (convertToDecimal) {

			case 'C':
				number = processDecimal(ROMAN_DISMAL_C, lastNumber, number);
				lastNumber = ROMAN_DISMAL_C;
				break;

			case 'L':
				number = processDecimal(ROMAN_DISMAL_L, lastNumber, number);
				lastNumber = ROMAN_DISMAL_L;
				break;

			case 'X':
				number = processDecimal(ROMAN_DISMAL_X, lastNumber, number);
				lastNumber = ROMAN_DISMAL_X;
				break;

			case 'V':
				number = processDecimal(ROMAN_DISMAL_V, lastNumber, number);
				lastNumber = ROMAN_DISMAL_V;
				break;

			case 'I':
				number = processDecimal(ROMAN_DISMAL_I, lastNumber, number);
				lastNumber = ROMAN_DISMAL_I;
				break;
			default:
				break;
			}
		}
		return number;
	}

	public static int processDecimal(int number, int lastNumber, int lastDecimal) {
		if (lastNumber > number) {
			return lastDecimal - number;
		} else {
			return lastDecimal + number;
		}
	}

	public static String changeDismal(String x) {
		StringBuilder sb = new StringBuilder();

		switch (x) {
		case "0":
			break;
		case "1":
			sb.append("I");
			break;
		case "2":
			sb.append("II");
			break;
		case "3":
			sb.append("III");
			break;
		case "4":
			sb.append("IV");
			break;
		case "5":
			sb.append("V");
			break;
		case "6":
			sb.append("VI");
			break;
		case "7":
			sb.append("VII");
			break;
		case "8":
			sb.append("VIII");
			break;
		case "9":
			sb.append("IX");
			break;
		default:
			break;
		}
		return sb.toString();
	}

	private static String changeDismall(String x) {
		StringBuilder sb = new StringBuilder();

		switch (x) {
		case "1":
			sb.append("X");
			break;
		case "2":
			sb.append("XX");
			break;
		case "3":
			sb.append("XXX");
			break;
		case "4":
			sb.append("XL");
			break;
		case "5":
			sb.append("L");
			break;
		case "6":
			sb.append("LX");
			break;
		case "7":
			sb.append("LXX");
			break;
		case "8":
			sb.append("LXXX");
			break;
		case "9":
			sb.append("XC");
			break;
		default:
			break;
		}
		return sb.toString();
	}
}
