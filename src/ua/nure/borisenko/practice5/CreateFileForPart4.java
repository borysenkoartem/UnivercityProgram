package ua.nure.borisenko.practice5;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.SecureRandom;

public class CreateFileForPart4 {

	public static void main(String[] args) {
		
		writeFile("part4.txt", 5, 20);
	}

	private static void writeFile(String outPath, int m, int n) {

		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), "utf-8"))) {
			// создаем рандомом цифры для файла
			SecureRandom rand = new SecureRandom();
			StringBuilder stringBilder = new StringBuilder();

			for (int j = 0; j < m; j++) {
				for (int i = 0; i < n; i++) {
					stringBilder.append(rand.nextInt(899) + 100 + " ");
				}
				stringBilder.deleteCharAt(stringBilder.length()-1);
				stringBilder.append(System.lineSeparator());
			}
			stringBilder.deleteCharAt(stringBilder.length()-1);
			// записываем его в файл
			writer.write(stringBilder.toString());
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}
}
