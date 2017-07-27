package company.facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 3. Coding：给一些函数运行的log，计算每个函数的inclusive time（函数总运行时间）和exclusive time（函数总运行时间-子函数运行时间）。
例如
fun_name     enter/exit     time
f1             enter         1
f2             enter         2
f2             exit          3
f1             exit          4

f1: inclusive time=4-1=3 exclusive time=3-1=2
f2: inclusive time=3-2=1 exclusive time=1
看见题有点懵，思考了几分钟觉得用栈做行。把sub function的时间记录在栈顶。函数exit时，inclusive time好算，直接减。exclusive time就用inclusive time-栈顶时间

如果有嵌套的话，要找到最后的一个start task A, 再找到最近的end task A. 之后只关心这一个section
 */
public class FunctionRunTime {

	public static void main(String[] args) {
		Node f1Start = new Node("f1", 1, true);
		Node f2Start = new Node("f2", 2, true);
		Node f2End = new Node("f2", 3, false);
		Node f1End = new Node("f1", 4, false);
		
		List<Node> list = new ArrayList<Node>();
		list.add(f1Start);
		list.add(f2Start);
		list.add(f2End);
		list.add(f1End);
		
		getRunTime(list, "f2");
	}
	
	static void getRunTime(List<Node> list, String task) {
		int inclusive = 0;
		int exclusive = 0;
		int subTotal = 0;
		
		int inStart = -1;
		int inEnd = -1;
		
		Stack<Node> stack = new Stack<Node>();
		
		for (Node node : list) {
			if (node.task.equals(task) && node.isStart) {
				inStart = node.time;
			} else if (node.task.equals(task) && !node.isStart) {
				inEnd = node.time;
				break;
			}
			
			if (inStart != -1) {
				stack.push(node);
			}
		}
		
		inclusive = inEnd - inStart;
		
		while (stack.size() > 1) { // 1 is the start of this task
			Node curEnd = stack.pop();
			
			while (!stack.peek().task.equals(curEnd.task)) { // As long as input is valid, stack will not be empty
				stack.pop();
			} // Until find the start of cur task
			
			subTotal += curEnd.time - stack.pop().time;
		}
		
		exclusive = inclusive - subTotal;
		System.out.println(inclusive + "====" + exclusive);
	}

	static class Node{
		String task;
		int time;
		boolean isStart;
		
		Node(String task, int time, boolean isStart) {
			this.task = task;
			this.time = time;
			this.isStart = isStart;
		}
	}
}
