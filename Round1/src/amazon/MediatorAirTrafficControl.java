package amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
/**
 * Mediator pattern is used to reduce communication complexity between multiple objects or classes. 
 * This pattern provides a mediator class which normally handles all the communications between different classes 
 * and supports easy maintenance of the code by loose coupling.
 * 
 * 关键点是调度程序，不要用一个大的foreach loop, 然后switch on type.
用多态来解决问题。
对象有：调度，机场，跑道，飞机。做一个UML的concept diagram.
调度机场1:1. has-a关系。
跑道机场，n:1,has-a 关系。
飞机，抽象类，具体类，小飞机，大飞机，直升飞机,etc.isa关系。
对于机场调度，考虑起飞和降落两个use cases:
可以画interactive diagram,或者文字。
飞机： 1. 请求降落
调度： 2. 分配跑道
飞机： 3. 降落
飞机： 4. 释放跑道资源。
如果没有资源，进入queue排队。
这样，需要一个跑道资源manager,和飞机queue.
设计模式：
机场，调度，跑道manager, 飞机queue, singleton.
飞机降落过程template method.
飞机/调度广播通信：observer
调度：mediator

Airport, Airplane, Flight, Runway, RunwayManager, Mediator, Direction, Action
 */
public class MediatorAirTrafficControl {

	public static void main(String[] args) {

	}
}

abstract class Airplane {

}

class BigPlane extends Airplane {}

enum Action {
	TAKEOFF, LAND
}

class Runway {
	boolean isAvailable;
	Direction direction;
}

class Airport {
	Mediator mediator = new Mediator();
	List<Runway> runways = new ArrayList<Runway>();
	
	List<Flight> comingFlights = new ArrayList<Flight>(); // Once flight takes off from original airport, register to the destination's comingFlight to receive messages
	
	void broadcast(String message) {
		for (Flight flight : comingFlights) {
			flight.newMessage(message);
		}
	}
}

class Flight {
	Airplane plane;
	Action action;
	Airport from;
	Airport to;
	
	void requestToTakeOff() {
		from.getMediator().ready(this, Action.TAKEOFF);
	}
	
	void requestToLand() {
		Mediator.land(this, Action.LAND);
	}
	
	Flight(Airplane plane, Action action) {
		this.plane = plane;
		this.action = action;
	}
}

class Mediator {
//	static Queue<Airplane> readyToFly = new LinkedList<Airplane>();
	ConcurrentLinkedQueue<Flight> readyTo = new ConcurrentLinkedQueue<Flight>();
//	static ConcurrentLinkedQueue<Action> action = new ConcurrentLinkedQueue<Action>();
//	static ConcurrentLinkedQueue<Airplane> readyToLand = new ConcurrentLinkedQueue<Airplane>();
	
	RunwayManager runwayManager = RunwayManager.getInstance();
	
	public Mediator(){}
	
	static void ready(Flight plane, Action action) {
		readyTo.offer(flight);
	}
	
	void schedule() { // Stream Processing
		while (true) {
			if (!readyTo.isEmpty() && isGreen) {
				Flight flight = readyTo.poll();
				boolean gotRanway = runwayManager.assignRunway(flight); // This will block the thread, until a runway is available and set to flight
				
				if (gotRanway) {
					takeOff(flight);
				}
			}
		}
	}
}