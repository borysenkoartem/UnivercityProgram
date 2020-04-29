package ua.nure.borisenko.practice5;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Part4 {

	public void oneThreadFind() throws InterruptedException {
		long start = System.currentTimeMillis();
		FindMax t = new FindMax(readText());
		t.start();
		t.join();
		System.out.println(t.maxValue);
		long finish = System.currentTimeMillis();
		long time = finish - start;
		System.out.println(time);
	}

	public void multiThreadFind() throws InterruptedException {
		long start = System.currentTimeMillis();
		int result = 0;
		String[] text = readText().split(System.lineSeparator());
		FindMax[] t = new FindMax[text.length];
		for (int i = 0; i < t.length; i++) {
			t[i] = new FindMax(text[i]);
			t[i].start();
		}
		for (FindMax j : t) {
			j.join();
		}
		for (int i = 0; i < t.length; i++) {
			if (t[i].maxValue > result) {
				result = t[i].maxValue;
			}
		}
		System.out.println(result);
		long finish = System.currentTimeMillis();
		long time = finish - start;
		System.out.println(time);
	}

	private class FindMax extends Thread {
		private String text;
		private int maxValue;

		public FindMax(String text) {
			this.text = text;
		}

		public void run() {

			maxValue = maxValue(text);
		}
	}

	public int maxValue(String text) {
		int result = 0;
		String[] numbers = text.split("\\s");
		for (String number : numbers) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			
				e.printStackTrace();
			}
			if (result < Integer.parseInt(number)) {
				result = Integer.parseInt(number);
			}
		}
		return result;
	}

	private static String readText() {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = Files.newBufferedReader(Paths.get("part4.txt"))) {
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

	public static void main(String[] args) throws InterruptedException {
		Part4 p = new Part4();
		p.multiThreadFind();
		p.oneThreadFind();
	}
}
