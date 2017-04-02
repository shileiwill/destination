package company.yahoo;

import java.util.Stack;
// LC 84
public class Histogram {

	public static void main(String[] args) {

	}

	int largestRectangleInHistogram(int[] heights) {
		int max = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i <= heights.length; i++) {
			int curH = -1;
			if (i == heights.length) {
				curH = -1;
			} else {
				curH = heights[i];
			}
			//stack is increasing sequence
			while (!stack.isEmpty() && curH <= heights[stack.peek()]) {
				int index = stack.pop();
				int h = heights[index];
				int w = stack.isEmpty() ? i : i - stack.peek() - 1;
				
				max = Math.max(max, h * w);
			}
		}
		
		return max;
	}
}
