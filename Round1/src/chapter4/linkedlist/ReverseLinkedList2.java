package chapter4.linkedlist;
/**
 * 92. Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 * @author Lei
 *
 */
public class ReverseLinkedList2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m >= n) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            head = head.next;
        }
        
        ListNode prevMNode = head;
        ListNode MNode = head.next;
        ListNode NNode = head.next; // Will keep moving
        ListNode postNNode = NNode.next; // Will keep moving
        
        for (int i = m; i < n; i++) {
            if (NNode == null) {
                return null;
            }
            
            ListNode temp = postNNode.next;
            postNNode.next = NNode;
            NNode = postNNode;
            postNNode = temp;
        }
        
        prevMNode.next = NNode;
        MNode.next = postNNode;
                
        return dummy.next;
    }
}
