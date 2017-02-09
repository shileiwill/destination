package amazon;

import cc150.chapter2.linkedlist.DeleteMidNode;
import chapter4.linkedlist.ListNode;
/**
 * 就是一圈zombies,给你一 个starting point,然后每隔k步你shoot一个zombie,完后依次下去直到剩下一个 zombie,
 * 让你输出最后剩下的zombie。我用linkedlist做的,然后while loop每次shoot一个zombie,num-1删除那个listnode,直到最后num变成1跳出循环。
 */
public class Zombies {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node1;
		
		Zombies rd = new Zombies();
		ListNode res = rd.shotZombies(node1, 2); 
		System.out.println(res.val);
	}

	ListNode shotZombies(ListNode node, int k) {
		while (node.next != node) {
			ListNode preK = find(node, k);
			preK.next = preK.next.next;
			node = preK.next;
		}
		return node;
	}
	
	ListNode find(ListNode node, int k) {
		while (k > 1) {
			node = node.next;
			k--;
		}
		return node;
	}
}
