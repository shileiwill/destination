package cc150.chapter15.multithread;
/**
 * https://www.javacodegeeks.com/2011/09/java-concurrency-tutorial-reentrant.html
 * The general classes of locks are nicely laid out as interfaces:
Lock – the simplest case of a lock which can be acquired and released
ReadWriteLock – a lock implementation that has both read and write lock types – multiple read locks can be held at a time unless the exclusive write lock is held
Java provides two implementations of these locks that we care about – both of which are reentrant (this just means a thread can reacquire the same lock multiple times without any issue).
ReentrantLock – as you’d expect, a reentrant Lock implementation
ReentrantReadWriteLock – a reentrant ReadWriteLock implementation
 */
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Calculator {
    private int calculatedValue;
    private int value;

    public synchronized void calculate(int value) {
        this.value = value;
        this.calculatedValue = doMySlowCalculation(value);
    }

    public synchronized int getCalculatedValue() {
        return calculatedValue;
    }

    public synchronized int getValue() {
        return value;
    }
}

class CalculatorLock {
    private int calculatedValue;
    private int value;
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void calculate(int value) {
        lock.writeLock().lock();
        try {
            this.value = value;
            this.calculatedValue = doMySlowCalculation(value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int getCalculatedValue() {
        lock.readLock().lock();
        try {
            return calculatedValue;
        } finally {
            lock.readLock().unlock();
        }
    }

    public int getValue() {
        lock.readLock().lock();
        try {
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }
}

/**
 * Release all locks in finally block. This is rule 1 for a reason.
Beware of thread starvation! The fair setting in ReentrantLocks may be useful if you have many readers and occasional writers that you don’t want waiting forever. It’s possible a writer could wait a very long time (maybe forever) if there are constantly read locks held by other threads.
Use synchronized where possible. You will avoid bugs and keep your code cleaner.
Use tryLock() if you don’t want a thread waiting indefinitely to acquire a lock – this is similar to wait lock timeouts that databases have.
**/
