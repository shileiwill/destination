package company.linkedin;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * '3: 实现两个函数: H() and O(), 这两个函数会被多线程调用。当一个线程调用H或O时
，如果当前已经有至少两个线程call H和一个线程call O。那么让两个call H和一个
call O的线程返回（产生一个水分子），其他的都block。'
 */
public class H2O {

	public static void main(String[] args) {

	}

	ReentrantLock lock = new ReentrantLock();
	Condition enoughH = lock.newCondition();
	Condition enoughO = lock.newCondition();
	int countH = 0;
	int countO = 0;
	
	void H() throws InterruptedException {
		lock.lock();
		try {
			countH++;
			
			if (!check(true)) {
				enoughH.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	void O() throws InterruptedException {
		lock.lock();
		try {
			countO++;
			
			if (!check(false)) {
				enoughO.await();
			}
		} finally {
			lock.unlock();
		}
	}
	
	boolean check(boolean isH) {
		if (countH >= 2 && countO >= 1) {
			countH -= 2;
			countO -= 1;
			System.out.println("H2O");
			
			enoughH.signal();
			if (isH) { // count is added already and we will return true here, so just signal O
				enoughO.signal();
			} else {
				enoughH.signal();
			}
			
			return true;
		}
		
		return false;
	}
}
