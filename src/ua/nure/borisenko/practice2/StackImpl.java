package ua.nure.borisenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

	private static final int DEFAULT_SIZE = 10;
	private static final int LAGER_SIZE = 2;
	private int size;
	private Object[] stack;

	public StackImpl() {
		size = 0;
		stack = new Object[DEFAULT_SIZE];
	}

	public int size() {
		return size;
	}

	public void clear() {
		size = 0;
	}

	public void push(Object element) {
		if (size >= stack.length) {
			addStackSize();
		}
		stack[size] = element;
		size++;
	}

	public Object pop() {
		return stack[--size];
	}

	public Object top() {
		return stack[size - 1];
	}

	public Object topAndPop() {
		pop();
		return stack[size];
	}

	private void addStackSize() {
		Object[] larger = new Object[stack.length * LAGER_SIZE];

		for (int i = 0; i < size; i++) {
			larger[i] = stack[i];
		}

		stack = larger;
	}

	public Iterator<Object> iterator() {
		return new IteratorImpl();
	}

	private class IteratorImpl implements Iterator<Object> {
		private int index;
		private boolean check;

		public IteratorImpl() {
			index = size();
		}

		@Override
		public boolean hasNext() {
			return index != 0;
		}

		@Override
		public Object next() {
			check = true;
			if (index == 0) {
				throw new NoSuchElementException("" + "There are no elements left.");
			}
			--index;
			return stack[index];
		}

		@Override
		public void remove() {
			if (check) {
				for (int i = index; i < size; i++) {
					stack[i] = stack[i + 1];
				}
				check = false;
				size--;
			}else {
				throw new IllegalStateException("ERROR");
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(stack[i]);
			if (i < size - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public static void main(String[] args) {
		Stack s = new StackImpl();
		s.push("s");
	}

}
