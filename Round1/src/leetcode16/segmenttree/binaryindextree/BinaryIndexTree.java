package leetcode16.segmenttree.binaryindextree;
// https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/FenwickTree.java
// https://www.youtube.com/watch?v=CWDQJGaN1gY
// http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
public class BinaryIndexTree {

	// (index & -index) is to get the last digit of 1. How to get complement: 所有位取反， +1
	int getParent(int index) { // Remove the last 1
//		return index - (index & -index);
		return (index & (index - 1));
	}
	
	int getNext(int index) {
//		return index + (index & -index);
		return index + (index - (index & (index - 1))); //  the second half is to find only the last digit one, like0000100000
//		return index + (index & (index - 1));
	}
	
	int[] buildTree(int[] nums) {
		int[] tree = new int[nums.length + 1];
		
		for (int i = 1; i <= nums.length; i++) {
			updateTree(tree, nums[i - 1], i);
		}
		
		return tree;
	}
	
	void updateTree(int[] tree, int val, int pos) {
		while (pos < tree.length) {
			tree[pos] += val;
			pos = getNext(pos); // All these will be in the same level, with same number of 1s
		}
	}
	
	int getSum(int[] tree, int index) {
		index = index + 1;
		
		int sum = 0;
		while (index > 0) {
			sum += tree[index];
			index = getParent(index);
		}
		return sum;
	}
	
    public static void main(String args[]){
        int input[] = {1,2,3,4,5,6,7};
        BinaryIndexTree ft = new BinaryIndexTree();
        int binaryIndexedTree[] = ft.buildTree(input);
        int res1 = ft.getSum(binaryIndexedTree, 0);
        int res2 = ft.getSum(binaryIndexedTree, 1);
        int res3 = ft.getSum(binaryIndexedTree, 2);
        int res4 = ft.getSum(binaryIndexedTree, 5);
        
        System.out.println(res1 + " == " + res2 + " == " + res3 + " == " + res4);
    }
}
