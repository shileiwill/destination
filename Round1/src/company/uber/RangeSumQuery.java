package company.uber;
/**
 * This big class contains Segment Tree to query, update sum in a range
 * 1D binary indexed tree and 2D
 */
public class RangeSumQuery {

	public static void main(String[] args) {

	}

	Node root = null;
	void buildTree(int[] arr) {
		root = helper(arr, 0, arr.length - 1);
	}
	
	Node helper(int[] arr, int left, int right) {
		if (left == right) {
			Node node = new Node(left, right, arr[left]);
			return node;
		}
		
		if (left > right) {
			return null;
		}
		
		Node root = new Node(left, right);
		
		int mid = (left + right) / 2;
		root.left = helper(arr, left, mid);
		root.right = helper(arr, mid + 1, right);
		
		// it will never be null
		root.sum = root.left.sum + root.right.sum; // What about yourself?
//		root.sum = (root.left != null ? root.left.sum : 0) + (root.right != null ? root.right.sum : 0);
		
		return root;
	}
	
	void update(int index, int value) {
		int currentValue = query(index, index);
		updateHelper(root, index, value - currentValue);
	}
	
	private int query(int start, int end) {
		return queryHelper(root, start, end);
	}

	private int queryHelper(Node node, int start, int end) {
		if (node == null) {
			return 0;
		}
		
		if (start <= node.start && end >= node.end) { // 完全包括
			return node.sum;
		}
		
		int sum = 0;
		int mid = (node.start + node.end) / 2;
		
		if (start <= mid) {
			sum += queryHelper(node.left, start, end);
		}
		
		if (end >= mid + 1) {
			sum += queryHelper(node.right, start, end);
		}
		
		return sum;
	}

	void updateHelper(Node node, int index, int value) {
		if (node.start == index && node.end == index) {
			node.sum += value;
			return;
		}
		
		int mid = (node.start + node.end) / 2;
		if (index <= mid) { // left
			updateHelper(node.left, index, value);
			node.sum += value; 
		}
		
		if (index >= mid + 1) { // right
			updateHelper(node.right, index, value);
			node.sum += value;
		}
	}
}

class Node {
	Node left;
	Node right;
	int start;
	int end;
	int sum;
	
	Node(int start, int end, int sum) {
		this.start = start;
		this.end = end;
		this.sum = sum;
	}
	
	Node(int start, int end) {
		this.start = start;
		this.end = end;
	}
}

class RangeSumQueryBinaryIndexedTree {
	
	int[] nums = null;
	int[] helper = null;
	
	RangeSumQueryBinaryIndexedTree(int[] nums) {
		this.nums = nums;
		this.helper = new int[nums.length + 1];
		
		for (int i = 0; i < nums.length; i++) {
			build(i + 1, nums[i]); // Index in helper array, Value
		}
	}
	
	void build(int index, int value) {
		while (index < helper.length) {
			helper[index] += value;
			index = getNext(index);
		}
	}
	
	private int getNext(int index) {
		int next = index + (index - (index & (index - 1)));
		return next;
	}
	
	int getParent(int index) {
		return (index & (index - 1));
	}

	public int sumRange(int i, int j) {
		int sum1 = 0;
		while (i > 0) {
			sum1 += helper[i];
			i = getParent(i);
		}
		
		int sum2 = 0;
		j = j + 1;
		while (j > 0) {
			sum2 += helper[j];
			j = getParent(j);
		}
		
		return sum2 - sum1;
	}
	
	void update(int i, int val) {
		int diff = val - nums[i];
		nums[i] = val;
		i = i + 1;
		
		while (i < helper.length) {
			helper[i] += diff;
			i = getNext(i);
		}
	}
}

/**
 * 2D using binary indexed tree
 */
class NumMatrix {

    int[][] matrix = null;
    int[][] helper = null;
    int m = 0;
    int n = 0;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        this.m = matrix.length;
        this.n = matrix[0].length;
        
        this.matrix = new int[m][n];
        this.helper = new int[m + 1][n + 1];
        
        for (int i = 0; i < m; i++) { // Original index
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }
    
    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        
        for (int i = row + 1; i <= m; i = getNext(i)) { // index in helper
            for (int j = col + 1; j <= n; j = getNext(j)) {
                helper[i][j] += diff;
            }
        }
    }
    
    int getNext(int index) {
        int next = index + (index - (index & (index - 1)));
		return next;
    }
    
    int getParent(int index) {
        return (index & (index - 1));
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2 + 1, col2 + 1) - sum(row2 + 1, col1) - sum(row1, col2 + 1) + sum(row1, col1);
    }
    
    int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i = getParent(i)) {
            for (int j = col; j > 0; j = getParent(j)) {
                sum += helper[i][j];
            }
        }
        
        return sum;
    }
}
