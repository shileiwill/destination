package company.yahoo;

import java.util.concurrent.locks.ReentrantLock;

import system.design.Elevator;

public class SingletonPattern {
	private static SingletonPattern instance = null;
	private static ReentrantLock lock = new ReentrantLock();

	public static SingletonPattern getInstance() {
		if (instance == null) {
			lock.lock();
			if (instance == null) {
				return new SingletonPattern();
			}
			lock.unlock();
		}
		return instance;
	}

	public static SingletonPattern getInstance2() {
		if (instance == null) {
			synchronized (Elevator.class) {
				if (instance == null) {
					instance = new SingletonPattern();
				}
			}
		}

		return instance;
	}

	private SingletonPattern() {

	}
}
