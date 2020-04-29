package ua.nure.borisenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class ArrayImpl implements Array {

	private Object[] arrayList = new Object[1];
	private int size;

	@Override
	public void clear() {
		size = 0;
		arrayList = new Object[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	@Override
	public void add(Object element) {

		if (size < arrayList.length) {
			arrayList[size] = element;
			size++;
		} else {
			Object[] temp = new Object[arrayList.length + 1];
			System.arraycopy(arrayList, 0, temp, 0, arrayList.length);
			arrayList = temp;
			arrayList[size] = element;
			size++;
		}
	}

	@Override
	public void set(int index, Object element) {
		arrayList[index] = element;

	}

	@Override
	public Object get(int index) {
		return arrayList[index];
	}

	@Override
	public int indexOf(Object element) {
		if (element == null) {
			for (int k = 0; k < arrayList.length; k++) {
				if (arrayList[k] == null) {
					return k;
				}
			}
		} else {
			for (int j = 0; j < arrayList.length; j++) {
				if (element.equals(arrayList[j])) {
					return j;
				}
			}
		}
		return -1;
	}

	@Override
	public void remove(int index) {
		for (int j = index; j < arrayList.length; j++) {
			if (j + 1 < arrayList.length) {
				arrayList[j] = arrayList[j + 1];
			}
		}
		size--;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");

		for (int j = 0; j < size; j++) {
			sb.append(arrayList[j]);
			if (j < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	class IteratorImpl implements Iterator<Object> {
		private int index;

		public boolean hasNext() {
			return index != size;
		}

		public Object next() {
			if (index == size) {
				throw new NoSuchElementException();
			}
			index += 1;
			return arrayList[index - 1];
		}

		@Override
		public void remove() {
			if (index <= 0) {
				throw new IllegalStateException();
			}
			for (int j = index; j < size; j++) {
				arrayList[j - 1] = arrayList[j];
			}
			size -= 1;
			arrayList[size] = null;
			index--;
		}
	}

	public static void main(String[] args) {
		Array a = new ArrayImpl();
		a.add("Z");
		a.add("x");
		a.add("w");
		System.out.println(a);
		a.clear();
		System.out.println(a);
		a.add("Z");
		a.add("x");
		a.add("w");
		System.out.println(a.size());
		a.set(1, "X");
		System.out.println(a);
		System.out.println(a.get(1));
		System.out.println(a.indexOf("X"));
		System.out.println(a.indexOf("p"));
	}
}
