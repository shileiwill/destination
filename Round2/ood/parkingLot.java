parkingLot.java

/**
1. Enums
2. Abstract/Interface
3. public, static, final
4. Exception
5. Design pattern
*/


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

public class Car extends Vehicle {
	public Car() {
		super(VehicleType.MEDIUM);
	}
}

public class Motobike extends Vehicle {
	public Motobike() {
		super(VehicleType.SMALL);
	}
}

public class Bus extends Vehicle {
	public Bus() {
		super(VehicleType.LARGE);
	}
}

// Let's talk about parking lot, parking spot, and fee
// If it is a multi-level building?

public enum ParkingSpotType {
	SMALL,
	MEDIUM,
	LARGE,
	HANDICAPPED
}

// design from small to big: spot -> level -> parking lot
public class ParkingSpot {
	private final ParkingSpotType type;
	private boolean available;
	private Vehicle vehicle;
	private int spotNo;
	private Level level;

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public boolean canVehicleFit(Vehicle v) {
		return true;
	}

	// park
	public void takeSpot(Vehicle v) {
		this.available = false;
	}

	public void leaveSpot(Vehicle v) {
		this.available = true;
	}
}

public class Level {
	private List<ParkingSpot> spotList;
	private int availableCount; // if it is full
	private boolean available;

	//  can future have smallAvailable, mediumAvailable etc

	public Level() {
		this.availableCount = NUM_SPOTS;
	}

	public void updateAvailableCount() {

	}
}

public class ParkingLot {
	private String name;
	private boolean available;
	private List<Level> levels;

	public Ticket park(Vehicle vehicle) {
		if (checkLotFull()) {
			throw new Exception("Lot is full");
		}

		
		// Find spot and park in that spot
		// match vihicle type is here, upgrade size is also here
		ParkingSpot spot = findAvailableSpot(vehicle);
		spot.park(vehicle); // takeSpot

		// create ticket
		Ticket ticket = new Ticket();
		ticket.setVehicle(vehicle);
		ticket.setEnterTime(now());
		ticket.setParkingSpot(spot);

		// update availability on Level and Lot
		decreaseAvailability(spot);

		spotToTicketMapping.put(spot, ticket);
		return ticket;

	}

	public Level findAvailableLevel() {
		return null;
	}

	public int getAvailableCount() {
		return 100;
	}

	public Ticket exit(Vehicle v) {
		// create a ticket
		return null;
	}

	public boolean canVehicleFit(Vehicle v) {
		// this parking lot can take only small cars
		return false;
	}

	public float calculate(Ticket t) {
		// get the final price
	}
}

public class Constants {
	public static final int NUM_SPOTS = 100;
	public static final int NUM_SMALL = 200;
}

public class Ticket {
	private String id;
	private float amount;
	private Vehicle vehicle;
	private DateTime enterTime;
	private DateTime exitTime;

	private int status; // can be enum, paid, unpaid

	void pay(Payment payment) {

	}
}

// 1. enter
// 2. find
// 3. update
// 4. exit
// 5. calculate


public interface PaymentStrategy {
	public void pay(int amount);
}

public class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	public CreditCardStrategy(String nm, String ccNum, String cvv, String expiryDate){
		...
	}

	@Override
	public void pay(int amount) {
		System.out.println(amount +" paid with credit card");
	}

}

public class PaypalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;
	
	public PaypalStrategy(String email, String pwd){
		...
	}
	
	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal.");
	}

}

public class Ticket {
	Vehicle vehicle;
	public void pay(PaymentStrategy paymentMethod){
		// calculate rate can also use strategy pattern
		int amount = calculateTotal();
		paymentMethod.pay(amount);
	}
}

ticket.pay(new PaypalStrategy("myemail@example.com", "mypwd"));
ticket.pay(new CreditCardStrategy(...));
