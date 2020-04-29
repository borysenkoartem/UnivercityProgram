package ua.nure.borisenko.practice6.part5;

import java.util.Comparator;

public class Tree<E extends Comparable<E>> {

    private Node<E> root;
    private Comparator<E> comparator;

    public Tree() {
        root = null;
        comparator = null;
    }

    private int compare(E x, E y) {
        if (comparator == null) {
            return x.compareTo(y);
        } else {
            return comparator.compare(x, y);
        }
    }

    boolean add(E data) {
        boolean tmp = !search(root, data);
        root = add(root, data);
        return tmp;
    }

    void add(E[] data) {
        for (E obj : data) {
            add(obj);
        }
    }

    private Node<E> add(Node<E> p, E toInsert) {
        if (p == null) {
            return new Node<>(toInsert);
        }
        if (compare(toInsert, p.data) == 0) {
            return p;
        }
        if (compare(toInsert, p.data) < 0) {
            p.leftChild = add(p.leftChild, toInsert);
        } else {
            p.rightChild = add(p.rightChild, toInsert);
        }
        return p;
    }

    private boolean search(Node<E> p, E toSearch) {
        if (p == null) {
            return false;
        } else if (compare(toSearch, p.data) == 0) {
            return true;
        } else if (compare(toSearch, p.data) < 0) {
            return search(p.leftChild, toSearch);
        } else {
            return search(p.rightChild, toSearch);
        }
    }

    boolean remove(E toDelete) {
        boolean tmp = search(root, toDelete);
        root = remove(root, toDelete);
        return tmp;
    }

    private Node<E> remove(Node<E> p, E toDelete) {
        if (p == null) {
            search(root, toDelete);
        } else if (compare(toDelete, p.data) < 0) {
            p.leftChild = remove(p.leftChild, toDelete);
        } else if (compare(toDelete, p.data) > 0) {
            p.rightChild = remove(p.rightChild, toDelete);
        } else {
            if (p.leftChild == null) {
                return p.rightChild;
            } else if (p.rightChild == null) {
                return p.leftChild;
            } else {
                p.data = retriveData(p.rightChild);
                p.rightChild = remove(p.rightChild, p.data);
            }
        }
        return p;
    }

    private E retriveData(Node<E> p) {
        while (p.rightChild != null) {
            p = p.leftChild;
        }
        return p.data;
    }

    void print() {
        root.print("", root);
    }

    public static class Node<E> {
        private E data;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E data, Node<E> leftChild, Node<E> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Node(E data) {
            this(data, null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }

        void print(String prefix, Node<E> n) {
            if (n != null) {
                print(prefix + "  ", n.leftChild);
                System.out.println(prefix + "" + n.data);
                print(prefix + "  ", n.rightChild);
            }
        }
    }

}
