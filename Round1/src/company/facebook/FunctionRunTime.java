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

636.
Given the running logs of n functions that are executed in a nonpreemptive single threaded CPU, find the exclusive time of these functions.

Each function has a unique id, start from 0 to n-1. A function may be called recursively or by another function.

A log is a string has this format : function_id:start_or_end:timestamp. For example, "0:start:0" means function 0 starts from the very beginning of time 0. "0:end:0" means function 0 ends to the very end of time 0.

Exclusive time of a function is defined as the time spent within this function, the time spent by calling other functions should not be considered as this function's exclusive time. You should return the exclusive time of each function sorted by their function id.

Example 1:
Input:
n = 2
logs = 
["0:start:0",
 "1:start:2",
 "1:end:5",
 "0:end:6"]
Output:[3, 4]
Explanation:
Function 0 starts at time 0, then it executes 2 units of time and reaches the end of time 1. 
Now function 0 calls function 1, function 1 starts at time 2, executes 4 units of time and end at time 5.
Function 0 is running again at time 6, and also end at the time 6, thus executes 1 unit of time. 
So function 0 totally execute 2 + 1 = 3 units of time, and function 1 totally execute 4 units of time.
Note:
Input logs will be sorted by timestamp, NOT log id.
Your output should be sorted by function id, which means the 0th element of your output corresponds to the exclusive time of function 0.
Two functions won't start or end at the same time.
Functions could be called recursively, and will always end.
1 <= n <= 100
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
	
	// The real question from leetcode
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        
        Stack<Integer> stack = new Stack<Integer>();
        int runningId = 0;
        int prevTime = 0;
        
        for (String s : logs) {
            String[] arr = s.split(":");
            int id = Integer.valueOf(arr[0]);
            boolean isStart = arr[1].equals("start");
            int time = Integer.valueOf(arr[2]);
            
            if (!isStart) {
                time++; // For the convenience of later
            }
            
            res[runningId] += (time - prevTime);
            
            if (isStart) {
                stack.push(runningId); // Push the running id? Yes, the old one
                runningId = id;
            } else {
                runningId = stack.pop();
            }
            
            prevTime = time;
        }
        
        return res;
    }
    
    // What if I care about only 1 specific task?
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
