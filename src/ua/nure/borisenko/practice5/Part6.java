package ua.nure.borisenko.practice5;

public class Part6 {

	private static final Object M = new Object();

	public static void main(String[] args) throws InterruptedException {

		Thread t = new Thread() {
			public void run() {
				while (!interrupted()) {
					synchronized (M) {
						try {
							M.wait();
						} catch (InterruptedException e) {
							return;
						}
					}
				}
			}
		};

		synchronized (M) {
			t.start();
			t.join(200);
			System.out.println(t.getState());
		}
		t.join(200);

		System.out.println(t.getState());

		t.interrupt();

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(t.getState());
	}
}
