package ua.nure.borisenko.practice5;

import java.io.IOException;

public class Part2 {
	public static final MyStreamRead YOUR_OWN_INPUT_STREAM = new MyStreamRead();

	public static void main(String[] args) throws InterruptedException {

		System.setIn(YOUR_OWN_INPUT_STREAM);

		Thread t = new Thread() {
			public void run() {
				try {
					Spam.main(null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
		t.join();
		System.setIn(System.in);
	}
}
