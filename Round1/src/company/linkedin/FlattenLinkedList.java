package company.linkedin;
/**
 * 2. 双向链表，但是每一个点还可以有up，down pointer， 已知一个链表里没有环，要求把这个链表变成标准双向链表，
每个点的具体位置排列无所谓。楼主开始反应是递归，写好后面试官说优化一下，，空间要求是constant space，
然后尽管面试官一直在提示tail recursion，还是没想出来（据说地里有原题，可惜当时楼主没看到。。。跪了= =！）

4.四向链表（上下左右）转双向链表（左右）。输入是一头一尾。放平链表后（中间可以乱序），保证头尾元素还是所给的指针。
先说了BFS的想法。然后要求用O（1）做空间。就是不管一切的放平了。然后把头尾挑出来，放到列首和列尾
 */
public class FlattenLinkedList {

	public static void main(String[] args) {

	}

	/**
	 * every time we meet a node check if it has up/down node
	 * if it has, connect it to the tail of the list
	 * and keep traversing
	 */
	MetaNode flatten(MetaNode root) {
		if (root == null) {
			return null;
		}
		
		MetaNode head = getHead(root);
		MetaNode tail = getTail(root);
		MetaNode cur = head;
		
		while (cur != null) {
			if (cur.up != null) {
				MetaNode up = cur.up;
				MetaNode headOfUp = getHead(up);
//				MetaNode tailOfUp = getTail(up);
				
				tail.next = headOfUp;
				headOfUp.prev = tail;
//				tail = tailOfUp;
				tail = getTail(tail);
				
				cur.up = null;
			}
			
			if (cur.down != null) {
				MetaNode down = cur.down;
				MetaNode headOfDown = getHead(down);
//				MetaNode tailOfDown = getTail(down);
				
				tail.next = headOfDown;
				headOfDown.prev = tail;
//				tail = tailOfDown;
				tail = getTail(tail);
				
				cur.down = null;
			}
			
			cur = cur.next;
		}
		
		return head;
	}
	
	MetaNode getHead(MetaNode node) {
		while (node.prev != null) {
			node = node.prev;
		}
		
		return node;
	}
	
	MetaNode getTail(MetaNode node) {
		while (node.next != null) {
			node = node.next;
		}
		
		return node;
	}
}

class MetaNode {
	MetaNode up;
	MetaNode down;
	MetaNode next;
	MetaNode prev;
	int val;
}