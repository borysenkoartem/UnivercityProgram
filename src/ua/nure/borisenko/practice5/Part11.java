package ua.nure.borisenko.practice5;

public class Part11 implements Runnable {

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
}
