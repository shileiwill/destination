package leetcode16.segmenttree.binaryindextree;
// https://mp.weixin.qq.com/s?__biz=MzA5MzE4MjgyMw==&mid=2649456390&idx=1&sn=4f8f435c0eeae9cb8979b661328a17a1&chksm=887e110ebf099818a07f08feefc89b4bc8bae04226bde297a536915989a7026409a7b0fabc60&mpshare=1&scene=1&srcid=02081HVAdJAoYyuF282ujk1d&pass_ticket=5KbuZh1EUpN1bPNkbu7WyLd0wtkztobWef2o19epVo2bXXUD7S%2Bz030lkmT8W6%2Bc#rd
public class SegmentTreeEasier {
	SegmentTreeNode build(int[] A) {
		return build(A, 0, A.length - 1);
	}
	
	SegmentTreeNode build(int[] A, int left, int right) {
		if (left > right) {
			return null;
		}
		// Make A[left] as the default max
		SegmentTreeNode root = new SegmentTreeNode(left, right, A[left]);
		if (left == right) {
			return root;
		}
		
		int mid = (left + right) / 2;
		root.left = build(A, left, mid);
		root.right = build(A, mid + 1, right);
		root.max = Math.max(root.left.max, root.right.max); // Update max. root.left can't be null
		// The same applies to: Math.min(left.min, right.min), left.sum + right.sum
		return root;
	}
	
	// User provides a range (start, end), and we return the max in this range
	int query(SegmentTreeNode node, int start, int end) { 
		if (node.start >= start && node.end <= end) {
			return node.max; // If node is inside the range, just the node.max is effective, just return
		}
		
		int mid = (node.start + node.end) / 2; // This is the cut line for the node in next level
		int max = Integer.MIN_VALUE;
		
		if (mid >= start) { // ( , mid) (mid + 1, ), see if (start, end) can jump across the 2 children range
			max = Math.max(max, query(node.left, start, end));
		}
		if (mid + 1 <= end) {
			max = Math.max(max, query(node.right, start, end));
		}
		
		return max;
	}
	
	/**
	 * 改动一个节点,与这个节点对应的叶子节点需要变动。因为叶子节点的值的改变可能影响到父亲节点，然后叶子节点的父亲节点也可能需要变动。
		所以需要从叶子节点一路走到根节点，去更新线段树上的值。因为线段树的高度为log(n)，所以更新序列中一个节点的复杂度为log(n)
	 */
	void update(SegmentTreeNode node, int index, int value) {
		if (node.start == node.end && node.start == index) {
			node.max = value;
			return;
		}
		
		int mid = (node.start + node.end) / 2;
		if (index <= mid) { //如果index在左子节点
			update(node.left, index, value);
			node.max = Math.max(node.left.max, node.right.max); // 对当前节点的影响
		}
		
		if (index >= mid + 1) {
			update(node.right, index, value);
			node.max = Math.max(node.left.max, node.right.max);
		}
	}
}

class SegmentTreeNode {
	SegmentTreeNode left, right;
	int start, end, max;
	
	SegmentTreeNode(int start, int end, int max) {
		this.start = start;
		this.end = end;
		this.max = max;
		this.left = this.right = null;
	}
}