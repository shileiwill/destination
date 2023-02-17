board_game.java

// Start with vehicles
public enum VehicleType {
	SMALL,
	MEDIUM,
	LARGE
}

public abstract class Vehicle {
	protected String plateNo; // if we use private, then subclass cant use it. use protected instead
	private final VehicleType type; // when the car enters, we know the size, we cant change it

	public Vehicle(VehicleType type) {
		this.type = type;
	}
}

public class Plane extends Vehicle {
	
}

public class Motorcycle extends Vehicle {
	
}

public class Island {
	private String name;

}

class Game {
	Player player1;
	Player player2;

	
}