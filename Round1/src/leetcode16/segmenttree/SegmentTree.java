package leetcode16.segmenttree;

public class SegmentTree {

	public static void main(String[] args) {
//		int[] arr = {-1, 2, 4, 0};
		int[] arr = {-1, 2, 4, 0, 5, 3, 8};
		SegmentTree st = new SegmentTree();
		
		int[] tree = st.constructTree(arr);
		for (int t : tree) {
			System.out.print(t + "**");
		}
		int min = st.rangeMinQuery(tree, 4, 6, 0, arr.length - 1, 0);
		System.out.println();
		System.out.println(min);
	}
	
	
	int[] constructTree(int[] arr) {
		int[] tree = new int[2 * NextPowerOfTwo.nextPowerOf2(arr.length) - 1];
		helper(tree, arr, 0, arr.length - 1, 0);
		return tree;
	}
	
	int rangeMinQuery(int[] tree, int qLow, int qHigh, int low, int high, int pos) {
		if (qLow <= low && qHigh >= high) { // Totally overlap
			return tree[pos];
		}
		if (qLow > high || qHigh < low) { // No overlap
			return Integer.MAX_VALUE;
		}
		int mid = (low + high) / 2;
		
		int a = rangeMinQuery(tree, qLow, qHigh, low, mid, 2 * pos + 1);
		int b = rangeMinQuery(tree, qLow, qHigh, mid + 1, high, 2 * pos + 2);
		
		return Math.min(a, b);
	}
	
	void helper(int[] tree, int[] arr, int left, int right, int pos) {
		if (left == right) {
			tree[pos] = arr[left];
			return;
		}
		
		int mid = (left + right) / 2;
		
		helper(tree, arr, left, mid, 2 * pos + 1);
		helper(tree, arr, mid + 1, right, 2 * pos + 2);
		
		tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
	}
	
}
