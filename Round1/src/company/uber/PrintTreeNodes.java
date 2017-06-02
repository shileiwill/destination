package company.uber;

import java.util.HashMap;
import java.util.Map;

public class PrintTreeNodes {

	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		
		n5.parent = n4;
		n4.parent = n2;
		n2.parent = n1;
		n6.parent = n3;
		n7.parent = n3;
		n3.parent = n1;
		
//		printAllNodes(n4);
		
		PrintTreeNodes p = new PrintTreeNodes();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		System.out.println(p.fib(5, map));
	}

	static class Node {
		Node parent;
		int val;
		
		Node(int val) {
			this.val = val;
		}
	}
	
	/**
	 * 只有母节点和本节点，没有子节点；告诉你一个节点，打印出所有节点，然后题目是面试官自己出的，因为看起来很不完善。
	 * @param node Still dont understand this question
	 */
	static void printAllNodes(Node node) {
		
	}
	
	// 斐波那契的话，构造个列表，存储之前得到的结果，降低平均时间复杂度
	int fib(int N, Map<Integer, Integer> map) {
		if (N == 0 || N == 1) {
			return 1;
		}
		
		if (map.containsKey(N)) {
			System.out.println("Come with " + N);
			return map.get(N);
		}
		
		int N_1 = fib(N - 1, map);
		map.put(N - 1, N_1);
		
		int N_2 = fib(N - 2, map);
		map.put(N - 2, N_2);
		
		map.put(N, N_1 + N_2);
		return N_1 + N_2;
	}
}
