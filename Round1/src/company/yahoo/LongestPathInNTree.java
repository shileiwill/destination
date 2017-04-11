package company.yahoo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
/**
 * 一个n叉树，树中元素不重复，找树中从上到下的longest increasing path，不一定从root开始。要自己定义TreeNode。Follow up 是树中元素有重复怎么办
 */
public class LongestPathInNTree {

	public static void main(String[] args) {
		NodeN nn1 = new NodeN(1);
		NodeN nn2 = new NodeN(2);
		NodeN nn3 = new NodeN(31);
		NodeN nn4 = new NodeN(4);
		NodeN nn5 = new NodeN(5);
		NodeN nn6 = new NodeN(6);
		NodeN nn7 = new NodeN(7);
		NodeN nn8 = new NodeN(8);
		NodeN nn9 = new NodeN(9);
		NodeN nn10 = new NodeN(10);
		NodeN nn11 = new NodeN(11);
		NodeN nn12 = new NodeN(12);
		
		Set<NodeN> ch1 = new HashSet<NodeN>();
		ch1.add(nn2);
		ch1.add(nn3);
		ch1.add(nn4);
		nn1.children = ch1;
		
		Set<NodeN> ch2 = new HashSet<NodeN>();
		ch2.add(nn5);
		nn3.children = ch2;
		
		LongestPathInNTree l = new LongestPathInNTree();
		l.findLongest(nn1);
		
		for (NodeN nn : l.max) {
			System.out.println(nn.val);
		}
	}

	LinkedList<NodeN> max = new LinkedList<NodeN>();
	
	LinkedList<NodeN> findLongest(NodeN node) {
		LinkedList<NodeN> res = new LinkedList<NodeN>();
		if (node == null) {
			return res;
		}
		
		for (NodeN n : node.children) {
			LinkedList<NodeN> list = findLongest(n);
			if (list.size() > res.size()) {
				res = list;
			}
		}
		
		if (res.isEmpty() || node.val < res.getLast().val) {
			res.addLast(node);
		}
		
		if (res.size() > max.size()) {
			max = res;
		}
		
		return res;
	}
}

class NodeN {
	int val;
	Set<NodeN> children = new HashSet<NodeN>();
	
	NodeN(int val) {
		this.val = val;
	}
}
