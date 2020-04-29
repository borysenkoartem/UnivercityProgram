package ua.nure.borisenko.practice1;

public class Part3 {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			builder.append(args[i]);
			builder.append(" ");
		}
		System.out.println(builder.toString().trim());

	}
}
