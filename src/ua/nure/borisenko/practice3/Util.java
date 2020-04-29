package ua.nure.borisenko.practice3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Util {
	private static final String ENCODING = "CP1251";
	private static final String PATH = "src/ua/nure/borisenko/practice3/";

	public static void main(String[] args) {
		System.out.println(readFile(PATH+ "part1.txt"));
	}

	public static String readFile(String path) {
		String res = null;
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(PATH+path));
			res = new String(bytes, ENCODING);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return res;
	}
}
