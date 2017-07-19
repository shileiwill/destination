package company.facebook;

import java.util.List;
import java.util.Stack;
/**
 * 341. Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].

New
 */
public class FlatternNestedInteger {

	public static void main(String[] args) {

	}

	Stack<NestedInteger> stack = new Stack<NestedInteger>();
	
	FlatternNestedInteger(List<NestedInteger> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			stack.push(list.get(i));
		}
	}
	
	int next() {
		return stack.pop().getInteger();
	}
	
	//  把逻辑放到hasNext()中，很好的解决了[] 空的问题
	boolean hasNext() {
		if (stack.isEmpty()) {
			return false;
		}
		
		while (!stack.isEmpty()) {
			NestedInteger ni = stack.peek();
			
			if (ni.isInteger()) {
				return true;
			}
			
			ni = stack.pop();
			List<NestedInteger> list = ni.getList();
			for (int i = list.size() - 1; i >= 0; i--) {
				stack.push(list.get(i));
			}
		}
		
		return false;
	}
}

class NestedInteger {
	Integer intValue = null;
	List<NestedInteger> listValue = null;
	
	int getInteger() {
		return intValue;
	}
	
	List<NestedInteger> getList() {
		return listValue;
	}
	
	boolean isInteger() {
		return intValue != null;
	}
}
