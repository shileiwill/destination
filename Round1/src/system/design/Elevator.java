package system.design;

import java.util.ArrayList;
import java.util.List;
/**
 * 跟Uber Pool有点类似，如何能接到更多的顺路者
 * 参考九章上的State Machine
 */
public class Elevator {
	private int currentFloor;
	private int targetFloor;
	private int status; // 0 standby, 1 up, 2 down
	private static volatile Elevator instance = null;

	private Elevator() {
		this.currentFloor = 0;
		this.targetFloor = 0;
		this.status = 0;
	}

	public static Elevator getInstance() {
		if (instance == null) {
			synchronized (Elevator.class) {
				if (instance == null) {
					instance = new Elevator();
				}
			}
		}

		return instance;
	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public int getStatus() {
		return status;
	}

	public void moveToFloor(int targetFloor) {
		while (currentFloor < targetFloor) {
			moveUp();
		}
		while (currentFloor > targetFloor) {
			moveDown();
		}

		status = 0;
	}

	private void moveUp() {
		status = 1;
		currentFloor += 1;
	}

	private void moveDown() {
		status = -1;
		currentFloor -= 1;
	}
}

class RequestHandler {
	List<Request> requests;
	private static volatile RequestHandler instance = null;

	public static RequestHandler getInstance() {
		if (instance == null) {
			synchronized (RequestHandler.class) {
				if (instance == null) {
					instance = new RequestHandler();
				}
			}
		}

		return instance;
	}

	private RequestHandler() {
		requests = new ArrayList<>();
	}

	public void addRequest(Request req) {
		synchronized (requests) {
			requests.add(req);
		}
	}

	// 这里可以替换为一个Strategy Pattern
	private Request getNextRequest() {
		int currentFloor = Elevator.getInstance().getCurrentFloor();
		int shortestdistance = Integer.MAX_VALUE;
		Request next = null;

		for (Request req : requests) { // Only shortest distance? dont care
										// about direction?
			if (Math.abs(req.getFromFloor() - currentFloor) < shortestdistance) {
				next = req;
			}
		}

		return next;
	}

	public void processRequest() {
		while (true) {
			Request req = getNextRequest(); // strategy.getNextRequest();
			if (req != null) {
				while (Elevator.getInstance().getStatus() != 0) {} // 等到Elevator停下， 再接受下一个请求 ：)
				Elevator.getInstance().moveToFloor(req.getFromFloor()); // 先到用户standby的那一层
				Elevator.getInstance().moveToFloor(req.getTargetFloor()); // 再带用户到target层
				requests.remove(req);
			}
		}

	}
}

class Request {
	// 还得有个fromFloor吧
	private int targetFloor;
	private int fromFloor;

	Request(int targetFloor, int fromFloor) {
		this.targetFloor = targetFloor;
		this.fromFloor = fromFloor;
	}

	public int getTargetFloor() {
		return targetFloor;
	}
	
	public int getFromFloor() {
		return fromFloor;
	}
}

class User {
	public void generateRequset(int targetFloor, int fromFloor) {
		RequestHandler.getInstance().addRequest(new Request(targetFloor, fromFloor));
	}
}
