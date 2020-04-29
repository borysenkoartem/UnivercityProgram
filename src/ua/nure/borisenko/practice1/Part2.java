package ua.nure.borisenko.practice1;

public class Part2 {

	public static void main(String[] args) {
		int result = 0;
		for (String a : args) {
			result += Integer.parseInt(a);
		}
		System.out.println(result);
	}

}
