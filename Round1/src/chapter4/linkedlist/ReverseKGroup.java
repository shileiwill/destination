package chapter4.linkedlist;

public class ReverseKGroup {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);

		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		
		ReverseKGroup t = new ReverseKGroup();
		ListNode res = t.reverseKGroup(node1, 3);
		
		while (res != null) {
			System.out.print(res.val + "--");
			res = res.next;
		}
	}
	
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        head = dummy;
        
        while (head != null) {
            head = reverse(head, k);
        }
        
        return dummy.next;
    }
    
    // head is the super previous one
    ListNode reverse(ListNode head, int k) {
        // If >= k
        ListNode cur = head;
        for (int i = 1; i <= k; i++) {
            cur = cur.next;
            if (cur == null) {
                return cur; // nodes are not enough, no need to reverse, just return
            }
        }
        
        // Cur is after the end of current section
        ListNode superPre = head;
        
        ListNode lastNode = head.next; // Used to be the first element in current section, will be the last in current section, and pre for next section. So will return this node
        ListNode cur2 = head.next;
        for (int i = 1; i <= k; i++) {
            ListNode temp = cur2.next;
            cur2.next = superPre;
            superPre = cur2;
            cur2 = temp; //Cur2 will be the first element in next section
        }
        
        head.next = superPre;
        lastNode.next = cur2;
        
        return lastNode;
    }
}
