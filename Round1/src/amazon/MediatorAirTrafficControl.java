package amazon;

import java.util.LinkedList;
import java.util.Queue;
/**
 * Mediator pattern is used to reduce communication complexity between multiple objects or classes. 
 * This pattern provides a mediator class which normally handles all the communications between different classes 
 * and supports easy maintenance of the code by loose coupling.
 */
public class MediatorAirTrafficControl {

	public static void main(String[] args) {

	}
}

class Airplane {
	
	void registerToFly() {
		Mediator.ready(this);
	}
	
	void fly() {
		// Flying.
		Mediator.isGreen = true;
	}
}

class Mediator {
	static Queue<Airplane> readyToFly = new LinkedList<Airplane>();
	
	static void ready(Airplane plane) {
		readyToFly.offer(plane);
	}
	
	static boolean isGreen = true;
	
	void schedule() {
		while (true) {
			if (!readyToFly.isEmpty() && isGreen) {
				Airplane plane = readyToFly.poll();
				this.isGreen = false;
				plane.fly();
				
			}
		}
	}
}