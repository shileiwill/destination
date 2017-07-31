package company.facebook.others;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by weixwu on 7/10/2017.
 * Binary search tree to circular linked list, using TreeNode in place
 */
public class BSTtoLinkedList {
	// This solution will make it circular, but hard to understand
    public TreeNode bstToLinkedList(TreeNode root){
        if (root == null) {
        	return null;
        }
        
        TreeNode left = bstToLinkedList(root.left);
        if (left != null){
            root.prev = left.prev;
            left.prev.next = root;
        }
        
        TreeNode right = bstToLinkedList(root.right);
        
        if (left == null && right == null) { // Leaf Node, No left or right node
            root.next = root;
            root.prev = root;
            
            return root;
        }
        
        if (left != null && right != null) { // Has both left and right child nodes
            left.prev = right.prev;
            right.prev.next = left;
        }
        
        if (left == null){
            root.prev = right;
            right.next = root;
        }
        
        if (right == null){
            left.prev = root;
            root.next = left;
        }
        
        if (right != null){
            root.next = right;
            right.prev = root;
        }
        
        return left != null ? left : root;
    }

    static class TreeNode{
        TreeNode left;
        TreeNode right;
        TreeNode prev;
        TreeNode next;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }
    
    // It is doubly linked list, not circular. You can just join the 2 ends to make it circular
    public TreeNode bstToDll(TreeNode node){
    	if (node == null) {
    		return null;
    	}
    	
    	TreeNode leftHead = bstToDll(node.left); // traverse down to left 
    	TreeNode rightHead = bstToDll(node.right); // traverse down to right
    	
        /*
         * lefthead represents head of link list created in left of node
         * righthead represents head of link list created in right
         * travel to end of left link list and add the current node in end
         */
        if(leftHead == null) {
            leftHead = node;
        } else {
        	TreeNode cur = leftHead;
        	while(cur.next != null){
        		cur = cur.next;
        	}
        	// Go to last node on the left side, connect
        	cur.next = node;
        	node.prev = cur;
        }
        
        /*
         *set the next node of current root to right head of right list
         */
        if(rightHead == null){ // Do we need to set this?
            rightHead = node;
        } else {
        	node.next = rightHead;
        	rightHead.prev = node;
        }
        
        return leftHead;// return left head as the head of the list added with current node
}

    public static void main(String args[]){
//        TreeNode root = new TreeNode(8);
//        root.left = new TreeNode(5);
//        root.right = new TreeNode(12);
//        root.left.left = new TreeNode(4);
//        root.left.right = new TreeNode(6);
//        root.right.left = new TreeNode(11);
//        //root.right.right = new TreeNode(14);
//        BSTtoLinkedList sl = new BSTtoLinkedList();
//        TreeNode res = sl.bstToDll(root);
//        
//        while (res != null) {
//        	System.out.println(res.val);
//        	res = res.next;
//        }
    	
    	/**
    	 * Write a program to find top two candidates winning the election.

You are the administrator of an election system. The back end is offline and election results are requested.
All votes are logged to vote.log(stream). We want to see results in real time.

Rules:

A single voter can vote for 3 candidates.
A voter cannot vote for the same candidate more than once.
Using the log find the top 5 candidates and detect fraud by printing the voter id and not counting the vote.

Fraud is candidate voting more than 3 times and/or a candidate more than once.

Need to use Node
Use a Map<Voter, Integer> to track how many candidates a voter voted. If < 3, accept this vote, otherwise, deny and print out this voter as fraud.
Use a Map<Candidate, Integer> to track how many tickets each candidate got.
Use a Heap<Map.Entry<Candidate, Integer>> to monitor the top 2 candidates.
    	 */
    	
    	Map<String, Node> map = new HashMap<String, Node>();
    	PriorityQueue<Node> heap = new PriorityQueue<Node>(10, new Comparator<Node>() {
    		public int compare(Node en1, Node en2) {
    			return en2.count - en1.count;
    		}
    	});
    	
    	map.put("Tom", new Node("Tom", 4));
    	map.put("Jack", new Node("Jack", 6));
    	
    	heap.addAll(map.values());
    	
    	if (map.containsKey("Tom")) {
    		Node node = map.get("Tom");
    		System.out.println(node.count);
    		node.count += 10;
    		
    		if (heap.contains(node)) {
    			System.out.println("Contains");
    			heap.remove(node);
    			heap.offer(node);
    		}
    	}
    	
    	System.out.println(heap.peek().count);
    }
    
    static class Node {
    	String name;
    	int count;
    	
    	public Node(String name, int count) {
    		this.name = name;
    		this.count = count;
    	}
    }
}
