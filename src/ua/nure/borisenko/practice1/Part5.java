package ua.nure.borisenko.practice1;

public class Part5 {

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		String[] s = Integer.toString(a).split("");
		int result = 0;
		for (String el : s) {
			result += Integer.parseInt(el);
		}
		System.out.println(result);
	}

}
