package company.facebook;

import java.util.Stack;
// Min Queue, 跟Min Stack类似， 实现一个Queue， 然后O（1）复杂度获得这个Queue里最小的元素
/**
 * 在九章面试题49《用栈实现队列》和面试题23《栈上实现Min函数》中，我们讲解到了如何用栈实现队列和在栈上实现Min函数。那么将两个题的解法结合起来，就是如何在队列中实现Min函
 * You only have 2 ways to get O(1) for a min/max operation:

if the structure is sorted and you know where the max / min is located
if the structure is not sorted and only allows insertion: you can recalculate the min / max every time you insert an item and store the value separately
if the structure is not sorted and allows insertions and removals: 
I don't think you can do better than O(n), unless you use more than one collection (but that solution does not support removal of any elements, 
only head / tail elements, which should be the case with a queue).
 */
public class MinQueue {

	public static void main(String[] args) {
		MinQueue mq = new MinQueue();
		mq.offer(3);
		System.out.println("1. " + mq.min());
		mq.offer(1);
		System.out.println("2. " + mq.min());
		mq.offer(7);
		System.out.println("3. " + mq.min());
		System.out.println(mq.poll());
		System.out.println("4. " + mq.min());
		mq.offer(13);
		System.out.println("5. " + mq.min());
		mq.offer(9);
		System.out.println(mq.poll());
		System.out.println("6. " + mq.min());
		mq.offer(5);
		System.out.println("7. " + mq.min());
	}
	
	MinStack minStack1 = new MinStack();
	MinStack minStack2 = new MinStack();
	
	void offer(int val) {
		minStack1.push(val);
	}
	
	int poll() {
		if(minStack2.isEmpty()) {
			 while (!minStack1.isEmpty()) {
				 minStack2.push(minStack1.pop());
			 }
		}
		return minStack2.pop();
	}
	
	int min() {
		if (!minStack1.isEmpty() && !minStack2.isEmpty()) {
			return Math.min(minStack1.getMin(), minStack2.getMin());
		} else if (!minStack1.isEmpty()) {
			return minStack1.getMin();
		} else if (!minStack2.isEmpty()) {
			return minStack2.getMin();
		} else {
			return -1;
		}
	}
}

// Implement a Min Stack
class MinStack {
    
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        stack.push(x);
        
        if (minStack.isEmpty() || x < minStack.peek()) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
    }
    
    public int pop() {
        minStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
    
    public boolean isEmpty() {
    	return stack.isEmpty();
    }
}

// Implement Queue with 2 Stacks
class MyQueue {
    Stack<Integer> in = new Stack<Integer>();
    Stack<Integer> out = new Stack<Integer>();

    /** Initialize your data structure here. */
    public MyQueue() {
        
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        in.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        
        return out.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        
        return out.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return (in.isEmpty()) && (out.isEmpty());
    }
}
