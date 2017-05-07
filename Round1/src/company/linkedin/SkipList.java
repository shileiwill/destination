package company.linkedin;
/**
 * All of the values are integers, larger than 0.
 * Values for each layer are guaranteed to be in ascending order and be integers, larger than 0.
 *
 * H --------> 8 ------------------------------------------------------------> null
 * |           |
 * H --------> 8 ----------> 28 ---------> 52 -------------------------------> null
 * |           |             |             |
 * H --------> 8 --> 25  --> 28 ---------> 52 ---------------->  81----------> NULL
 * |           |     |       |             |                     |
 * H ----> 5-> 8 --> 25  --> 28 --> 33 --> 52 --> 55 --> 70 -->  81 --> 83 --> NULL
 *
 * Grid List :
 *   go from home toward right or down side.
 *   value of each layer is ascending
 *   each node's below points to the node on the blow layer with the same value
 * Benefit:
 *   search quickly
 */
public class SkipList {

	public static void main(String[] args) {

	}

	static int hops = 0;
	static int searchNodes(Node start, int target) {
		if (start == null || start.val >= target) {
			return hops; // Either to the end, or next is >= target
		}
		
		// Go right
		if (start.right != null && start.right.val <= target) {
			hops++;
			return searchNodes(start.right, target);
		}
		
		if (start.below != null && start.below.val <= target) {
			hops++;
			return searchNodes(start.below, target);
		}
		
		return hops;
	}
	
	static class Node {
		int val;
		Node right;
		Node below;
	}
}
