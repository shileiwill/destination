package chapter0.pickOne;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
/**
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/atomicvars.html
 * http://stackoverflow.com/questions/29883719/java-multithreading-threadsafe-counter
 * 
 * The phenomenon you are seeing is called thread starvation. Upon entering the guarded portion of your code (sorry I missed this earlier), other threads will need to block until the thread holding the monitor is done (i.e. when the monitor is released). Whilst one may expect the current thread pass the monitor to the next thread waiting in line, for synchronized blocks, java does not guarantee any fairness or ordering policy to which thread next recieves the monitor. It is entirely possible (and even likely) for a thread that releases and attempts to reacquire the monitor to get hold of it over another thread that has been waiting for a while.

From Oracle:

Starvation describes a situation where a thread is unable to gain regular access to shared resources and is unable to make progress. This happens when shared resources are made unavailable for long periods by "greedy" threads. For example, suppose an object provides a synchronized method that often takes a long time to return. If one thread invokes this method frequently, other threads that also need frequent synchronized access to the same object will often be blocked.
Whilst both of your threads are examples of "greedy" threads (since they repeatedly release and reacquire the monitor), thread-0 is technically started first, thus starving thread-1.

The solution is to use a concurrent synchronization method that supports fairness (e.g. ReentrantLock) as shown below:
 * @author Lei
 *
 */
/**
 * 3 ways to have a thread-safe counter
 * 1. Synchronized
 * 2. AtomicInteger
 * 3. ReentrantLock
 * @author Lei
 *
 */
class Counter implements Runnable {
	
	private int value = 0;
	private ReentrantLock lock = new ReentrantLock();
	
	public void increment() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " : " + value++);
		} finally {
			lock.unlock();
		}
	}
	
	@Override
	public void run() {
		while (value <= 1000) {
			increment();
		}
	}

	public static void main(String[] args) {
		Counter counter1 = new Counter();
		
		Thread t1 = new Thread(counter1);
		Thread t2 = new Thread(counter1);
		
		t1.start();
		t2.start();
	}
}

class CounterAtomicInteger implements Runnable {
	
	private AtomicInteger value = new AtomicInteger(0);
	
	public void increment() {
		System.out.println(Thread.currentThread().getName() + " : " + value.incrementAndGet());
	}
	
	@Override
	public void run() {
		while (value.intValue() <= 1000) {
			increment();
		}
	}

	public static void main(String[] args) {
		Counter counter1 = new Counter();
		
		Thread t1 = new Thread(counter1);
		Thread t2 = new Thread(counter1);
		
		t1.start();
		t2.start();
	}
}

class CounterSynchronized implements Runnable {
	
	private int value;
	
	public synchronized void increment() {
		System.out.println(Thread.currentThread().getName() + " : " + value++);
	}
	
	public void decrement() {
		value--;
	}
	
	public int value() {
		return value;
	}
	
	@Override
	public void run() {
		while (value <= 1000) {
			increment();
		}
	}

	public static void main(String[] args) {
		Counter counter1 = new Counter();
		
		Thread t1 = new Thread(counter1);
		Thread t2 = new Thread(counter1);
		
		t1.start();
		t2.start();
	}
}

class CounterNotSafe implements Runnable {
	
	private int value;
	
	public void increment() {
		System.out.println(Thread.currentThread().getName() + " : " + value++);
	}
	
	public void decrement() {
		value--;
	}
	
	public int value() {
		return value;
	}
	
	@Override
	public void run() {
		while (value <= 1000) {
			increment();
		}
	}

	public static void main(String[] args) {
		Counter counter1 = new Counter();
		
		Thread t1 = new Thread(counter1);
		Thread t2 = new Thread(counter1);
		
		t1.start();
		t2.start();
	}
}