package company.uber;

import java.util.Scanner;

import chapter4.linkedlist.ListNode;

/**
 * 第一题让打印一个10*10 multiplication table. 她在code pair 里面有了两行output, 
 * 1 2 3 4 5 6 7 8 9 10 
 * 2 4 6 8 10 12 14 16 18 20 
 * 刚开始没怎么注意她写的这两行。心想怎么出这么简单的题，就写了两个for loop 打印。 
 * 然后她问我what do you think the most challenging part for this question? 
 * 我没懂她什么 意思，就说换行啊什么的，但也很简单，她不满意。然后电话里面也不是很清楚她到底 想干什么。
 * 后来突然发现space的问题，每列数字都是右对齐的。然后就用了个fixedLength， 然后每个数字的后面跟fixedLength - 本数字占的字符数 长度的space. Run了下代码过了。
 */
public class PrintMultiplicationTable {

	public static void main(String[] args) {
//		printMatrix();
		printLinkedList();
	}

	static void printMatrix() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				int curVal = i * j;
				int curLen = String.valueOf(curVal).length();
				
				int space = 4 - curLen; // The max number is 100, which has length 3, so use 4 is good enough here
				while (space > 0) {
					System.out.print(" ");
					space--;
				}
				System.out.print(curVal);
			}
			System.out.println();
		}
	}
	
	// Given a set of numbers, build a linked list and print it reversely
	static void printLinkedList() {
		Scanner sc = new Scanner(System.in);
		ListNode dummy = new ListNode(-1);
		ListNode prev = dummy;
		
		for (int i = 0; i < 10; i++) {
			ListNode node = new ListNode(sc.nextInt());
			prev.next = node;
			prev = prev.next;
		}
		
		printReversely(dummy.next);
	}

	private static void printReversely(ListNode node) {
		if (node == null) {
			return;
		}
		
		printReversely(node.next);
		System.out.print(node.val + "--");
	}
	
	
}
