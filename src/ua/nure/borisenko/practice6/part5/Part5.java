package ua.nure.borisenko.practice6.part5;

public class Part5 {

	public static void main(String[] args) {
		Tree a = new Tree();

		System.out.println(a.add(3));
		System.out.println(a.add(3));
		System.out.println("~~~~~~~");
		a.add(1);
		a.add(5);
		a.add(6);
		a.add(4);
		a.add(2);
		a.add(0);
		a.print();
		System.out.println("~~~~~~~");
		System.out.println(a.remove(5));
		System.out.println(a.remove(5));
		System.out.println("~~~~~~~");
		a.print();
	}

}
