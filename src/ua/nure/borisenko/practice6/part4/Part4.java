package ua.nure.borisenko.practice6.part4;

import java.util.Iterator;

public class Part4 {

	public static void main(String[] args) {
		Range range = new Range(-1, 5);
		for (Integer el : range) {
			System.out.printf("%d ", el);
		}
		System.out.println();
		
		range = new Range(3, 10, true);
		for (Integer el : range) {
			System.out.printf("%d ", el);
		}
		System.out.println();

		range = new Range(-1, 5);

		Iterator it = range.iterator();
		it.next();
		System.out.println(it.next());
		System.out.println(it.next());

		it = range.iterator();
		it.next();
		System.out.println(it.next());
		System.out.println(it.next());

	}

}
