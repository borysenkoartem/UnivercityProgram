package ua.nure.borisenko.practice6.part3;

public class Part3 {

	public static void main(String[] args) {

		Parking parking = new Parking(4);
		System.out.print(parking.arrive(2)+" ");
		parking.print();
		System.out.print(parking.arrive(3)+" ");
		parking.print();
		System.out.print(parking.arrive(2)+" ");
		parking.print();
		System.out.print(parking.arrive(2)+" ");
		parking.print();
		System.out.print(parking.arrive(2)+" ");
		parking.print();
		System.out.print(parking.depart(1)+" ");
		parking.print();
		System.out.print(parking.depart(1)+" ");
		parking.print();
		
		
	}

}
