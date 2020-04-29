package ua.nure.borisenko.practice5;

public class Part1 extends Thread {

	private static final int SLEEP_TIME = 330;
	private static final int WORK_TIME = 1000;

	public void run() {
		for (int i = 0; i < WORK_TIME / SLEEP_TIME; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void run2() {
		for (int i = 0; i < WORK_TIME / SLEEP_TIME; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Part1 thread1 = new Part1();
		Thread thread2 = new Thread(new Part11());
		thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		Part1.run2();
	}
}
