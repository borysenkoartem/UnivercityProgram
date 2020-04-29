package ua.nure.borisenko.practice1;

public class Part7 {
	private static final int START = 64;
	private static final int NOTATION = 26;
	private static  final String Q =" ==> ";
	
	private static final int A =1;
	private static final int B = 2;
	private static final int Z =26;
	private static final int AA=27;
	private static final int AZ = 52;
	private static final int BA=53;
	private static final int ZZ = 702;
	private static final int AAA =703;
	
	

	public static void main(String[] args) {
		System.out.println("A ==> " + str2int("A") + Q+ int2str(A));
		System.out.println("B ==> " + str2int("B") + Q + int2str(B));
		System.out.println("Z ==> " + str2int("Z") + Q + int2str(Z));
		System.out.println("AA ==> " + str2int("AA") + Q + int2str(AA));
		System.out.println("AZ ==> " + str2int("AZ") + Q + int2str(AZ));
		System.out.println("BA ==> " + str2int("BA") + Q + int2str(BA));
		System.out.println("ZZ ==> " + str2int("ZZ") + Q + int2str(ZZ));
		System.out.println("AAA ==> " + str2int("AAA") + Q + int2str(AAA));
	}

	public static int str2int(String str) {
		char[] numbers = new StringBuilder(str).reverse().toString().toCharArray();
		int digit = 0;

		for (int i = 0; i < numbers.length; i++) {
			digit += Math.pow(NOTATION, i) * (numbers[i] - START);
		}
		return digit;
	}

	public static String int2str(int num) {
		StringBuilder sb = new StringBuilder();
		int temp = num;

		while (temp != 0) {
			temp /= NOTATION;
			if (num % NOTATION == 0) {
				sb.append((char) (START + NOTATION));
				temp--;
			} else {
				sb.append((char) (START + num % NOTATION));
			}
			num = temp;
		}

		return sb.reverse().toString();
	}

	public static String rightColumn(String num) {
		return int2str(1 + str2int(num));
	}
}