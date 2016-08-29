package chapter1.pickOne;
/**
 * <pre>
 *      array  [ 1 3 0 2 4 7]
 *       index   0 1 2 3 4 5
 *      input:  dest-node: A[0]
 *      output: all the source nodes: (A[1], A[3], A[4])
 *
 *  the number of array is the allowed steps from that index to jump.
 *  it is allowed to jump back or forward
 *
 *
 *      ----------------
 *     |                |
 *     v                |
 *    A[0]--> A[1] --> A[4]
 *            ^ |       |
 *           /  |       v
 *          /   |--> A[2] <---
 *         /                  |
 *       A[3]-------------->A[5]
 *
 *
 * Create graph from the end A[0] and BFS from end A[0].
 * 
 * 一个数组 A: 1 3 0 2 4 7
input: dest-node: A0
output: all the source nodes: (A1, A3, A4)

数组中每个元素表示他能走的步数，既能向左走 又能向右走，能到A[0]的点有A[1]和A[4]，A[1]可以走3步到A[4] A[4]能走4步道A[0]。
 */
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindAllJumpSource {

	public static void main(String[] args) {

	}
	
	List<Integer> find_source(int[] A, int dest) {
		V root = createGraph(A, dest);
		
	}
	
	V createGraph(int[] A, int dest) {
		V root = new V(A[dest]);
		
	}

}

// Directed Graph
class V {
	int label;
	Set<V> incomings = new HashSet<V>();
	Set<V> outgoings = new HashSet<V>();
	V (int label) {
		this.label = label;
	}
}