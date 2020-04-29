package ua.nure.borisenko.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListImpl implements List {

	private int size;
	private Node head;
	private Node tail;

	public void clear() {
		head = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Object element) {
		size++;
		if (head == null) {
			head = new Node(element, null, null);
			tail = head;
		} else {
			Node node = new Node(element, null, head);
			this.head.setPrev(node);
			this.head = node;
		}
	}

	public void addLast(Object element) {
		size++;
		if (head == null) {
			head = new Node(element, null, null);
			tail = head;
		} else {
			Node node = new Node(element, tail, null);
			this.tail.setNext(node);
			this.tail = node;
		}
	}

	public void removeFirst() {
		if (head != null) {
			size--;
			if (head.next != null) {
				head.next.setPrev(null);
				head = head.getNext();
			} else {
				head = null;
				tail = null;
			}
		}
	}

	public void removeLast() {
		if (head != null) {
			size--;
			if (tail.getPrev() != null) {
				tail.prev.setNext(null);
				tail = tail.getPrev();
			} else {
				head = null;
				tail = null;
			}
		}
	}

	public Object getFirst() {
		return head.getData();
	}

	public Object getLast() {
		return tail.getData();
	}

	public Object search(Object element) {

		Node last = tail;
		Node first = head;
		while (!last.equals(first)) {
			if (element == null) {
				return first.getData();
			}
			if (element.equals(last.getData())) {
				return last.getData();
			}
			if (element.equals(first.data)) {
				return first.data;
			}
			last = last.prev;
			first = first.next;
		}
		if (element.equals(first.data)) {
			return first.data;
		}

		return null;

	}

	public boolean remove(Object element) {
		if (size == 0) {
			return false;
		}

		Node tempNode;
		tail = head;
		tempNode = tail;

		if (tail.getData().equals(element)) {
			head = tail.getNext();
			size--;
			return true;
		}

		while (tail != null) {
			tail = tail.getNext();
			if (tempNode.getData().equals(element)) {
				tempNode.setNext(tail.getNext());
				size--;
				return true;
			}
			tempNode = tempNode.getNext();
		}
		return false;

	}

	public Iterator<Object> iterator() {

		return new IteratorImpl(head);
	}

	private class IteratorImpl implements Iterator<Object> {

		private Node currentNod;
		private Node node;
		private Node nodeForDelet;

		public IteratorImpl(Node firstNode) {
			this.node = firstNode;
		}

		public boolean hasNext() {

			if (Objects.isNull(currentNod) && node != null) {
				return true;
			}
			return (currentNod != null && (currentNod.next != null));
		}

		@Override
		public Object next() {
			if (currentNod == null && node != null) {
				currentNod = node;
				nodeForDelet = currentNod;
				return currentNod.getData();
			}
			if (hasNext()) {
				currentNod = currentNod.next;
				if (currentNod == null) {
					throw new NoSuchElementException();
				}
				nodeForDelet = currentNod;
			}
			return currentNod.data;
		}

		@Override
		public void remove() {

			if (nodeForDelet != null) {
				// if element is last
				if (Objects.isNull(currentNod.getNext())) {
					removeLast();
				}
				// if element is first
				if (Objects.isNull(currentNod.getPrev())) {
					removeFirst();
				}
				// if there is single element
				if (Objects.isNull(currentNod.getPrev()) && Objects.isNull(currentNod.getNext())) {
					removeFirst();
				}

				if (!Objects.isNull(currentNod.prev) && !Objects.isNull(currentNod.getNext())) {
					Node next = currentNod.getNext();
					Node prev = currentNod.getPrev();
					prev.setNext(next);
					next.setPrev(prev);

				}
				nodeForDelet = null;
			} else {
				throw new IllegalStateException();
			}
		}

	}

	public String toString() {
		Iterator<Object> it = iterator();
		if (!it.hasNext()) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder();
		sb.append('[');
		for (;;) {
			Object e = it.next();
			sb.append(e);
			if (!it.hasNext()) {
				return sb.append(']').toString();
			}
			sb.append(", ");
		}
	}

	private static class Node {
		private Object data;
		private Node prev;
		private Node next;

		public Node(Object data, Node prev, Node next) {

			this.data = data;
			this.prev = prev;
			this.next = next;
		}

		public Object getData() {
			return data;
		}

		public Node getPrev() {
			return prev;
		}

		public void setPrev(Node prev) {
			this.prev = prev;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	public static void main(String[] args) {
		List a = new ListImpl();
		a.addFirst("a");
		a.addLast("z");
		a.addFirst("a");
		a.addLast("z");
		System.out.println(a);
		a.clear();
		a.addFirst("a");
		a.addLast("z");
		a.addFirst("a");
		a.addLast("z");
		System.out.println(a.getFirst());
		System.out.println(a.getLast());
		a.removeFirst();
		a.removeLast();
		System.out.println(a.size());
		System.out.println(a);

	}
}
