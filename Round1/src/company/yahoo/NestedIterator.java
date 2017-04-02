package company.yahoo;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class NestedIterator implements Iterator<Integer> {

	Stack<NestedInteger> stack = new Stack<NestedInteger>();
	
	NestedIterator(List<NestedInteger> list) {
		for (int i = list.size() - 1; i >= 0; i--) {
			stack.push(list.get(i));
		}
	}
	
	@Override
	public boolean hasNext() {
		if (stack.isEmpty()) {
			return false;
		}
		
		while (!stack.isEmpty()) {
			if (stack.peek().isInteger()) {
				return true;
			} else {
				NestedInteger now = stack.pop();
				for (int i = now.getList().size() - 1; i >= 0; i--) {
					stack.push(now.getList().get(i));
				}
			}
		}
		
		return false;
	}

	@Override
	public Integer next() {
		return stack.pop().getInteger();
	}

}
