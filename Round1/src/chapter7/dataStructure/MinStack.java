package chapter7.dataStructure;

import java.util.Stack;
/**
 * 155. Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 * @author Lei
 *
 */
public class MinStack {

    Stack<Integer> regularStack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    /** initialize your data structure here. */
    public MinStack() {
        
    }
    
    public void push(int x) {
        regularStack.push(x);
        
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }
    
    public void pop() {
        if (!regularStack.isEmpty()) {
            int val = regularStack.pop();
            // Be careful here. Pop minStack only when the min is popped
            // However, this is not easy to remember.
            if (minStack.peek() == val) {
                minStack.pop();
            }
        }
    }
    
    public int top() {
        return regularStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

class MinStackBetter {

    // Make the 2 stacks have the same height
    Stack<Integer> regularStack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();
    
    public void push(int x) {
        regularStack.push(x);
        
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        } else { // Always the same height. Push myself
            minStack.push(minStack.peek());
        }
    }
    
    public void pop() {
        minStack.pop();
        regularStack.pop();
    }
    
    public int top() {
        return regularStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}