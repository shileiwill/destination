package ebay;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeStack {

	public static void main(String[] args) {

	}

	int[] arr = null;
	int size = 0;
	int pos = 0;
	ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	ThreadSafeStack(int size) {
		this.size = size;
		arr = new int[size];
	}
	
	void push(int val) {
		try {
			lock.writeLock().lock();
			
			if (isFull()) {
				throw new RuntimeException("Full Stack");
			}
			
			arr[pos++] = val;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		
		// Use Synchronize block is also fine
		synchronized (this) {
			if (isFull()) {
				throw new RuntimeException("Full Stack");
			}
			
			arr[pos++] = val;
		}
	}
	
	int pop() {
		int val = -1;
		try {
			lock.readLock().lock();
			
			if (isEmpty()) {
				throw new RuntimeException("Empty Stack");
			}
			val = arr[--pos];
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.readLock().unlock();
		}
		return val;
	}

	// Use Synchronize block is also fine
	void pushSynchronized(int val) {
		synchronized (this) {
			if (isFull()) {
				throw new RuntimeException("Full Stack");
			}
			
			arr[pos++] = val;
		}
	}
	
	int popSynchronized() {
		synchronized (this) {
			int val = -1;
			if (isEmpty()) {
				throw new RuntimeException("Empty Stack");
			}
			val = arr[--pos];
			return val;
		}
	}
	
	// Do we need to synchronize this as well?
	boolean isFull() {
		return size == pos;
	}
	
	boolean isEmpty() {
		return pos == 0;
	}
}
