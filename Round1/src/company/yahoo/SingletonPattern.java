package company.yahoo;

import java.util.concurrent.locks.ReentrantLock;

public class SingletonPattern {
	private SingletonPattern instance = null;
	private ReentrantLock lock = new ReentrantLock();
	
	public SingletonPattern getInstance() {
		if (instance == null) {
			lock.lock();
			if (instance == null) {
				return new SingletonPattern();
			}
			lock.unlock();
		}
		return instance;
	}
	
	private SingletonPattern() {
		
	}
}
