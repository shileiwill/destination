package company.amazon.new_03_16;

import java.util.ArrayList;
import java.util.List;

enum VehicleSize {
	LARGE, COMPACT, MINI
}

abstract class Vehicle {
	VehicleSize size;
	List<ParkingSpot> spots = new ArrayList<ParkingSpot>();
	int spotsNeeded = -1;
	
	abstract boolean parkInSpot(ParkingSpot spot);
	
	abstract boolean canSpotFit(ParkingSpot spot);
}

class Car extends Vehicle {
	Car() {
		this.size = VehicleSize.COMPACT;
		this.spotsNeeded = 1;
	}
	
	boolean parkInSpot(ParkingSpot spot) {
		boolean res = spot.parkVehicle(this);
		return res;
	}

	@Override
	boolean canSpotFit(ParkingSpot spot) {
		return false;
	}
}
class ParkingSpot {
	Vehicle curVehicle = null;
	int level = -1;
	VehicleSize size;
	
	ParkingSpot(int level, VehicleSize size) {
		this.level = level;
		this.size = size;
	}
	
	boolean isAvailable() {
		return curVehicle == null;
	}
	
	boolean parkVehicle(Vehicle vehicle) {
		if (vehicle.size > size) {
			return false;
		}
		this.curVehicle = vehicle;
	}
}

public class ParkingLot {
	Level[] levels = new Level[10];
	
	int availableSpots() {
		int count = 0;
		for (Level l : levels) {
			count += l.availableSpots;
		}
		return count;
	}
	
	boolean isFull() {
		return availableSpots() == 0;
	}
	
	boolean parkVehicle(Vehicle v) {
		//Find a spot on all Levels, if not, return false;
		return false;
	}
}

class Level {
	List<ParkingSpot> spots = new ArrayList<ParkingSpot>();
	int level = -1;
	
	Level(int level) {
		this.level = level;
	}
	
	boolean parkVehicle(Vehicle v) {
		// Find a spot or multiple spots to park the vehicle
	}
}