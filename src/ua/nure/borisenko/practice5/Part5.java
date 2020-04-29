package ua.nure.borisenko.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Part5 {

	private static final Object FILEPATH = "part5.txt";

	private Thread[] threads;

	public Part5(int threadQuantity) {
		threads = new Thread[threadQuantity];
		for (int i = 0; i < threadQuantity; i++) {
			threads[i] = new Thread(new T(i));
		}
	}

	private static class T implements Runnable {
		private int num;

		public T(int number) {
			num = number;
		}

		public void run() {
			synchronized (FILEPATH) {
				for (int i = (num * 20 + num); i < (num * 20 + num + 20); i++) {
					try {
						write(num, i);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		private static void write(Integer n, int position) throws IOException {

			try (RandomAccessFile file = new RandomAccessFile(String.valueOf(FILEPATH), "rw")) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				file.seek(position);
				file.write(n.toString().getBytes());
				String lineSeparator = System.getProperty("line.separator", "\n");
				file.write(lineSeparator.getBytes());
			}
		}
	}

	public void start() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();

		}
	}

	public void join() {
		for (int i = 0; i < threads.length; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	

	public static void main(String[] args) {
		Part5 p = new Part5(10);
		p.start();
		p.join();
	}
}
