package amazon;

import chapter4.linkedlist.ListNode;

public class PalindromeList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(3);
		ListNode n5 = new ListNode(2);
		ListNode n6 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
//		n6.next = n7;
//		n2.next = n3;
		
		PalindromeList pl = new PalindromeList();
		int res = pl.lPalin(n1);
		System.out.println(res);
	}

    ListNode left = null;
    int res = 1;
    
    public int lPalin(ListNode A) {
        left = A;
        helper(A);
        return res;
    }
    
    int len = 0;
    int half = 0;
//    boolean leftToRight = true;
    
    void helper(ListNode A) {
        if (A == null) {
//            leftToRight = false;
            half = len / 2;
        	return;
        }
        len++;
        helper(A.next);
        
        len--;
        if (len < half) {
        	return;
        }
//        if (res == 0) {
//            return;
//        }
        
        if (left.val != A.val) {
            res = 0;
            return;
        } else {
            left = left.next;
        }
    }
}
