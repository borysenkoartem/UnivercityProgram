package ua.nure.borisenko.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private int[] array;


	public Range(int n, int m) {
        if (n > m) {
            throw new IllegalArgumentException();
        }
        array = new int[m - n + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = n++;
        }
    }

    public Range(int n, int m, boolean reverse) {
        if (n > m) {
            throw new IllegalArgumentException();
        }
        array = new int[m - n + 1];
        if (reverse) {
            for (int i = 0; i < array.length; i++) {
                array[i] = m--;
            }
        } else {
            new Range(n, m);
        }
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int index;

            public boolean hasNext() {
                return index < array.length;
            }

            public Integer next() {
                if (hasNext()) {
                    return array[index++];
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
