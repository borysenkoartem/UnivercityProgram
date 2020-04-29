package ua.nure.borisenko.practice1;

public class Part6 {
private static final int  FIRST_PRIME =2;
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);

		StringBuilder s = new StringBuilder();
		int num = FIRST_PRIME;
		for (int i = 0; i < n; i++) {
			s.append(num++ + " ");
			while (!nextPrime(num)) {
				num++;
			}
		}
		System.out.println(s.toString());
	}

	private static boolean nextPrime(int n) {
		
		for (int i = FIRST_PRIME; i < n; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}