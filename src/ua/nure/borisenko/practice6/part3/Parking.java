package ua.nure.borisenko.practice6.part3;

import java.util.ArrayList;
import java.util.List;

public class Parking {

	private List<Integer> parkingSpot;

	public Parking(int n) {
		parkingSpot = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			parkingSpot.add(0);
		}
	}

	boolean arrive(int k) {
		if (k > parkingSpot.size() - 1) {
			throw new IllegalArgumentException();
		}
		for (int i = k; i < parkingSpot.size(); i++) {
			if (parkingSpot.get(i) == 0) {
				parkingSpot.set(i, 1);
				return true;
			} 
		}
				for (int j = 0; j < k; j++) {
					if (parkingSpot.get(j) == 0) {
						parkingSpot.set(j, 1);
						return true;
					}
				}
		return false;
	}

	boolean depart(int k) {
		if (k > parkingSpot.size() - 1) {
			throw new IllegalArgumentException();
		}
		if (parkingSpot.get(k) == 1) {
			parkingSpot.set(k, 0);
			return true;
		}
		return false;
	}

	void print() {
		for (Integer i : parkingSpot) {
			System.out.print(i);
		}
		System.out.println();
	}
}
