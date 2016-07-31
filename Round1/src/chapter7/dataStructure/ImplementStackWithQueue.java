package chapter7.dataStructure;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackWithQueue {

	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		myStack.push(1);
		myStack.push(2);
		int rs = myStack.top();
		System.out.println(rs);
	}
	
	

}

class MyStack {
    // queue1 is the major one
    Queue<Integer> queue1 = new LinkedList<Integer>();
    Queue<Integer> queue2 = new LinkedList<Integer>();
    
    void swapQueues() {
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
    }
    
    // Move elements to queue2, leave only 1 in queue1
    void moveElements() {
        while (queue1.size() != 1) {
            queue2.offer(queue1.poll());
        }
    }
    
    // Push element x onto stack.
    public void push(int x) {
        queue1.offer(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        moveElements();
        queue1.poll();
        swapQueues();
    }

    // Get the top element.
    public int top() {
        moveElements();
        int item = queue1.poll(); // Must take out, then add back
        swapQueues();
        queue1.offer(item);
        
        return item;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue1.isEmpty();
    }
}