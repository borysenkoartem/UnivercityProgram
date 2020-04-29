package ua.nure.borisenko.practice5;

public class Part3 {

	private int counter;
	private int counter2;
	private Thread[] threads;

	private boolean flag;

	private int n;
	private int k;
	private int t;

	public Part3(int n, int k, int t) {
		this.n = n;
		this.k = k;
		this.t = t;
	}

	public void test() {
		threads = new Thread[n];
		flag = false;
		for (int i = 0; i < n; i++) {
			threads[i] = new Thread(new OneThread(k, t, flag));
			threads[i].start();
		}
		join();
	}

	private class OneThread implements Runnable {
		private int iterations;
		private int sleepTime;
		private boolean sync;

		public OneThread(int k, int t, boolean f) {
			iterations = k;
			sleepTime = t;
			sync = f;
		}

		public void run() {
			for (int i = 0; i < iterations; i++) {
				if (sync) {
					synchronized (Part3.this) {
						System.out.printf("%s %s%n", counter, counter2);
						counter++;
						try {
							Thread.sleep(sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						counter2++;
					}
				} else {
					System.out.printf("%s %s%n", counter, counter2);
					counter++;
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					counter2++;
				}
			}
		}
	}

	public void reset() {
		counter = 0;
		counter2 = 0;
	}

	public void testSync() {

		threads = new Thread[n];
		flag = true;
		for (int i = 0; i < n; i++) {
			threads[i] = new Thread(new OneThread(k, t, flag));
			threads[i].start();
		}
		join();
	}

	private void join() {
		for (Thread oneThread : threads) {
			try {
				oneThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Part3 p = new Part3(5, 3, 100);
		p.test();
		p.reset();
		p.testSync();

	}

}
