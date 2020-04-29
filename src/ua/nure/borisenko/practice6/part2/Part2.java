package ua.nure.borisenko.practice6.part2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Part2 {
	private List<Integer> arrayList;
	private List<Integer> linkedList;

	public Part2(Integer n) {
		arrayList = new ArrayList<>(n);
		linkedList = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			arrayList.add(i);
			linkedList.add(i);
		}

	}

	public List<Integer> getArrayList() {
		return new ArrayList<>(arrayList);
	}

	public List<Integer> getLinkedList() {
		return new LinkedList<>(linkedList);
	}

	public static void main(String[] args){
		Part2 p = new Part2(10000);
		Part2 p2 = new Part2(10000);
		System.out.println("ArrayList#Index: " + removeByIndex(p.getArrayList(), 4) + " ms");
		System.out.println("LinkedList#Index: " + removeByIndex(p.getLinkedList(), 4) + " ms");
		System.out.println("ArrayList#Iterator: " + removeByIterator(p2.getArrayList(), 4) + " ms");
		System.out.println("LinkedList#Iterator: " + removeByIterator(p2.getLinkedList(), 4) + " ms");
	}

	private static long removeByIndex(List<Integer> list, int k)  {
		long start = System.currentTimeMillis();

		int lastRemove = k - 1;
		while (list.size() != 1) {
			if (lastRemove >= list.size()) {
				lastRemove = lastRemove - list.size();
			} else {
				list.remove(lastRemove);
				lastRemove += (k - 1);

			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

	private static long removeByIterator(List<Integer> list, int k) {
		long start = System.currentTimeMillis();

		Iterator<Integer> iterator = list.iterator();

		while (list.size() != 1) {
			for (int i = 0; i < k; i++) {
				if (iterator.hasNext()) {
					iterator.next();
					if (i == k - 1) {
						iterator.remove();
					}
				} else {
					iterator = list.iterator();
					if (iterator.hasNext()) {
						iterator.next();
						if (i == k - 1) {
							iterator.remove();
						}
					}
				}
			}
		}
		long end = System.currentTimeMillis();
		return end - start;
	}

}
