package ua.nure.borisenko.practice5;


import java.io.IOException;


public class Spam {

	private Thread[] threads;

	public Spam(String[] massage, int[] sleepTime) {

		if (massage.length != sleepTime.length) {
			throw new IllegalArgumentException("Numbers of interval less then numbers of messages");
		}
		threads = new Thread[massage.length];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Worker(massage[i], sleepTime[i]);
		}
	}

	public void start() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}

	public void stop() {
		for (int i = 0; i < threads.length; i++) {
			threads[i].interrupt();
		}

	}

	private static class Worker extends Thread {
		private String name;
		private int time;

		public Worker(String workerMassege, int workerTime) {
			name = workerMassege;
			time = workerTime;
		}

		public void run() {
			while (!Thread.interrupted()) {
				System.out.println(name);
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		String[] massages = new String[] { "@@@", "bbb" };
		int[] times = new int[] { 333, 222 };

		Spam p = new Spam(massages, times);
		p.start();
		System.in.read();
		p.stop();
	
		
	}

}
