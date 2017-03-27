package company.amazon.new_03_16;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
/**
 * isLock(): Check isLocked of the given node.
Lock(): If isLocked is set, then the node cannot be locked. 
Else check all descendants and ancestors of the node and if any of them is locked, then also this node cannot be locked. 
If none of the above conditions is true, then lock this node by setting isLocked as true.
unLock(): If isLocked of given node is false, nothing to do. Else set isLocked as false.
 */
public class ReversePolishNotation {
	public int evalRPN(String[] tokens) {
		// Stack is used to hold numbers
		Stack<String> stack = new Stack<>();
		
		Set<String> operators = new HashSet<String>();
		operators.add("+");
		
		for (String token : tokens) {
			
		}
	}
}

class LockAndUnlockTreeNode {
	boolean isLocked(NodeWithLock node) {
		return node.isLocked;
	}
	
	boolean lock(NodeWithLock root, NodeWithLock node) {
		if (node.isLocked) {
			return false;
		}
		
		List<NodeWithLock> ancestors = getAllAncestors(root, node.val);
		for (NodeWithLock an : ancestors) {
			if (an.isLocked) {
				return false;
			}
		}
		
		List<NodeWithLock> descendents = getAllDescendents(node);
		for (NodeWithLock de : descendents) {
			if (de.isLocked) {
				return false;
			}
		}
		
		node.isLocked = true;
		return true;
	}
	
	boolean unlock(NodeWithLock node) {
		if (node.isLocked) {
			node.isLocked = false;
			return true;
		} else {
			return false;
		}
	}
	
	List<NodeWithLock> getAllDescendents(NodeWithLock node) {
		List<NodeWithLock> list = new ArrayList<NodeWithLock>();
		helper2(list, node);
		return list;
	}
	
	List<NodeWithLock> getAllAncestors(NodeWithLock root, int target) {
		List<NodeWithLock> list = new ArrayList<NodeWithLock>();
		helper(list, root, target);
		return list;
	}
	
	void helper2(List<NodeWithLock> list, NodeWithLock node) {
		if (node == null) {
			return;
		}
		
		if (node.left != null) {
			list.add(node.left);
			helper2(list, node.left);
		}
		
		if (node.right != null) {
			list.add(node.right);
			helper2(list, node.right);
		}
	}
	
	boolean helper(List<NodeWithLock> list, NodeWithLock node, int target) {
		if (node == null) {
			return false;
		}
		
		if (node.val == target) {
			return true;
		}
		
		if (helper(list, node.left, target) || helper(list, node.right, target)) {
			list.add(node);
			return true;
		}
		
		return false;
	}
}


class NodeWithLock{
	NodeWithLock left, right;
	int val;
	boolean isLocked;
}

class NodeWithLockParent{
	NodeWithLockParent left, right, parent;
	int val;
	int countOfLockedInDescendents;
	boolean isLocked;
}

class LockAndUnlockTreeNodeWithParent {
	boolean isLocked(NodeWithLockParent node) {
		return node.isLocked;
	}
	
	boolean lock(NodeWithLockParent node) {
		if (node.isLocked) {
			return false;
		}
		
		if (node.countOfLockedInDescendents != 0) {
			return false;
		}
		
		List<NodeWithLockParent> ancestors = getAllAncestors(node);
		for(NodeWithLockParent an : ancestors) {
			if (an.isLocked) {
				return false;
			}
		}
		
		for(NodeWithLockParent an : ancestors) {
			an.countOfLockedInDescendents += 1;
		}
		
		node.isLocked = true;
		return true;
	}
	
	boolean unlock(NodeWithLockParent node) {
		if (node.isLocked) {
			node.isLocked = false;
			return true;
		} else {
			return false;
		}
	}
	
	List<NodeWithLockParent> getAllAncestors(NodeWithLockParent node) {
		List<NodeWithLockParent> list = new ArrayList<NodeWithLockParent>();
		node = node.parent;
		while (node != null) {
			list.add(node);
			node = node.parent;
		}
		return list;
	}
	
	boolean helper(List<NodeWithLock> list, NodeWithLock node, int target) {
		if (node == null) {
			return false;
		}
		
		if (node.val == target) {
			return true;
		}
		
		if (helper(list, node.left, target) || helper(list, node.right, target)) {
			list.add(node);
			return true;
		}
		
		return false;
	}
}