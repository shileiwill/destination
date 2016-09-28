package cc150.chapter10.sorting.searching;
/**
 * BST is good at keeping relative ordering, as well as updating when we insert new elements
 */
public class RankedBinarySearchTree {

	public static void main(String[] args) {

	}

	RankNode root = null;
	
	void track(int num) {
		if (root == null) {
			root = new RankNode(num);
		} else {
			root.insert(num);
		}
	}
	
	int getRankOfNumber(int num) {
		return root.getRank(num);
	}
	
	class RankNode {
		int val;
		RankNode left, right;
		int leftCount;
		
		RankNode(int val) {
			this.val = val;
		}
		
		void insert(int num) {
			if (num <= val) { // include duplicate on the left. Actually doesnt matter
				if (left == null) {
					left = new RankNode(num);
				} else {
					left.insert(num);
				}
				leftCount++;
			} else {
				if (right == null) {
					right = new RankNode(num);
				} else {
					right.insert(num);
				}
			}
		}
		
		int getRank(int num) {
			if (num == val) {
				return leftCount;
			} else if (num < val) { // Go to left
				if (left == null) {
					return -1;
				} else {
					left.getRank(num);
				}
			} else {
				if (right == null) {
					return -1;
				} else {
					int rightCount = right.getRank(num);
					if (rightCount == -1) { // This element doesnt exist at all
						return -1;
					} else {
						return leftCount + 1 + rightCount;
					}
				}
			}
			return -1;
		}
	}
}
