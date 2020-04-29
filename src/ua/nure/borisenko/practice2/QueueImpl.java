package ua.nure.borisenko.practice2;

import java.util.Iterator;

import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

	private Object[] array = new Object[SIZE_ARRAY];
	private int arraySize;
	public static final int SIZE_ARRAY = 5;
	public static final int LAGER_SIZE_ARRAY = 5;
	private int start;

	@Override
	public void clear() {
		arraySize = 0;
		array = new Object[SIZE_ARRAY];
	}

	@Override
	public int size() {
		return arraySize;
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		private boolean isNext;
		private boolean isRemove;
		protected int step;
		private int index;

		@Override
		public boolean hasNext() {
			return index != arraySize;
		}

		@Override
		public Object next() {
			isNext = true;
			isRemove = false;
			if (index == arraySize) {
				throw new NoSuchElementException();
			}
			if (arraySize > step) {
				index++;
			}
			return array[index - 1];

		}

		@Override
		public void remove() {
			if (!isNext) {
				throw new IllegalStateException();
			}
			if (isRemove) {
				throw new IllegalStateException();
			}
			if (index <= 0) {
				throw new IllegalStateException();
			} else {
				isRemove = true;
				for (int j = index; j < arraySize; j=j+2) {
					array[j - 1] = array[j];
				}
				arraySize -= 1;
			}
		}
	}

	public void enqueue(Object element) {

		if (arraySize < array.length) {
			array[arraySize] = element;
			arraySize++;
		} else {
			Object[] bigArray = new Object[array.length + LAGER_SIZE_ARRAY];
			System.arraycopy(array, 0, bigArray, 0, array.length);
			array = bigArray;
		}
	}

	public Object dequeue() {
		if (arraySize == 0) {

			throw new IllegalStateException();
		} else {
			arraySize--;
			Object element = array[start];
			if (start == array.length - 1) {
				start = 0;
			} else {
				start++;
			}
			return element;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int j = 0; j < arraySize; j++) {
			sb.append(array[j]);
			if (j < arraySize - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public Object top() {
		if (arraySize == 0) {
			throw new NoSuchElementException();
		}
		return array[0];

	}

	public static void main(String[] args) {
		Queue q = new QueueImpl();
		q.enqueue("a");
		q.enqueue("Sb");
		q.dequeue();
		q.enqueue("S");
	}
}