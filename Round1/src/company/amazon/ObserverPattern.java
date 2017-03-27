package company.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ObserverPattern {

	public static void main(String[] args) {
//		MessageBoard observable = new MessageBoard();
//		Student s1 = new Student();
//		Student s2 = new Student();
//		observable.addObserver(s1);
//		observable.addObserver(s2);
//		
//		observable.changeMessage("Hello");
		
		Subject sub = new Subject();
		Listener l1 = new Listener();
		Listener l2 = new Listener();
		
		sub.add(l1);
		sub.add(l2);
		
		sub.update();
		
		l1.unsubscribe();
		sub.update();
	}

}

// Using Java APIs
/*
 * 1) Observable is a class and Observer is an interface.
   2) Observable class maintain a list of Observers.
   3) When an Observable object is updated, it invokes the update() method of each of its Observers to notify that, it is changed.
   Think Twitter. When you say you want to follow someone, Twitter adds you to their follower list. 
   When they sent a new tweet in, you see it in your input. Same idea. In that case, you Twitter account is the Observer and 
   the person you're following is the Observable.
 */
class MessageBoard extends Observable {
	String message;
	
	void changeMessage(String message) {
		this.message = message;
		setChanged();
		notifyObservers(message);
	}
}

class Student implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Get this new message from Observable: " + arg1);
	}
	
}

// Implement by yourself
class Subject {
	List<Listener> observers = new ArrayList<Listener>();
	
	void add(Listener l) {
		observers.add(l);
		
		// Add control to Listener as well
		l.sub = this;
	}
	
	void update() {
		for (Listener l : observers) {
			l.update("This is a new Message");
		}
	}
}

class Listener {
	Subject sub;
	
	void update(String message) {
		System.out.println(message + " : from Observable");
	}
	
	void unsubscribe() {
		sub.observers.remove(this);
	}
}
