package ua.nure.borisenko.practice5;

import java.io.IOException;

class MyStreamRead extends java.io.InputStream {
	private int i;
	private byte[] text = System.lineSeparator().getBytes();
	private boolean first = true;

	public int read() throws IOException {
		if (first) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (i >= text.length) {
			return -1;
		}
		return text[i++];
	}
}