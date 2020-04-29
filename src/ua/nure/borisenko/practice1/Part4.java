package ua.nure.borisenko.practice1;

public class Part4 {

	public static void main(String[] args) {

		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		System.out.println(gcf(a, b));
	}

	private static int gcf(int x, int y) {
		if (y == 0) {
			return Math.abs(x);
		}
		return gcf(y, x % y);
	}

}
