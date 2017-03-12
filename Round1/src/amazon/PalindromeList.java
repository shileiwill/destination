package amazon;

import chapter4.linkedlist.ListNode;

public class PalindromeList {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		
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
    
    void helper(ListNode A) {
        if (A == null) {
            return;
        }
        
        helper(A.next);
        
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
