package ua.nure.borisenko.practice3;

public class Part6 {
	public static final String REGEX = "\n";

	public static void main(String[] args) {
		String text = Util.readFile("part6.txt");
		System.out.println(convert(text));
	}

	public static String convert(String input) {
		StringBuilder sb = new StringBuilder();
		String[] text = input.split(REGEX);
		String[][] text2 = new String[text.length][];

		for (int i = 0; i < text.length; i++) {
			text2[i] = text[i].split(" ");
		}
		boolean hasEquel = false;

		for (int i = 0; i < text2.length; i++) {
			for (int j = 0; j < text2[i].length; j++) {
				hasEquel = checkDuplicates(i, j, text2);

				if (!hasEquel) {
					sb.append(text2[i][j]).append(" ");
				} else {
					sb.append("_").append(text2[i][j]).append(" ");
				}
				hasEquel = false;
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	private static boolean checkDuplicates(int i, int j, String[][] text) {
		for (int k = 0; k < text.length; k++) {
			for (int z = 0; z < text[k].length; z++) {

				if (k == i && j == z) {
					continue;
				}
				if (text[i][j].equals(text[k][z])) {
					return true;
				}
			}
		}
		return false;
	}
}