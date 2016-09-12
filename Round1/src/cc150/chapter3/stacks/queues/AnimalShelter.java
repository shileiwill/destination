package cc150.chapter3.stacks.queues;

import java.util.LinkedList;
import java.util.Queue;

// Firstly, we should not use Enum. Secondly, since we are using 2 queues, why not 1 for dogs, 1 for cats. Add a timestamp(order) in Animal
public class AnimalShelter {

	public static void main(String[] args) {
		Animal aDog = Animal.DOG;
		
	}
	
	Queue<Animal> queue1 = new LinkedList<Animal>();
	Queue<Animal> queue2 = new LinkedList<Animal>();
	
	void enqueue(Animal a) {
		queue1.offer(a);
	}
	
	boolean isEmpty() {
		return queue1.isEmpty() && queue2.isEmpty();
	}
	
	Animal dequeueAny() {
		if (isEmpty()) {
			return null;
		}
		return queue1.poll();
	}
	
	Animal dequeueDog() {
		if (isEmpty()) {
			return null;
		}
		while (!queue1.isEmpty() && queue1.peek().getValue() != 1) {
			queue2.offer(queue1.poll());
		}
		
		if (queue1.isEmpty()) {
			return null;
		} else {
			Animal res = queue1.poll();
			shift();
			return res;
		}
	}

	void shift() {
		while (!queue1.isEmpty()) {
			queue2.offer(queue1.poll());
		}
		Queue<Animal> tmp = queue1;
		queue1 = queue2;
		queue2 = queue1;
	}
	
	Animal dequeueCat() {
		if (isEmpty()) {
			return null;
		}
		while (!queue1.isEmpty() && queue1.peek().getValue() != 2) {
			queue2.offer(queue1.poll());
		}
		
		if (queue1.isEmpty()) {
			return null;
		} else {
			Animal res = queue1.poll();
			shift();
			return res;
		}
	}
}

enum Animal {
	DOG(1), CAT(2);
	
	private int value;
	private Animal(int value) {
		this.value = value;
	}
	
    public int getValue() {
        return value;
    }
}