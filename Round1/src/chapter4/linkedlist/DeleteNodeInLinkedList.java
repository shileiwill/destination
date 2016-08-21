package chapter4.linkedlist;
/**
 * 237. Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.

Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 */
public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {
        while (node.next != null) {
            int temp = node.val; // Switch values
            ListNode next = node.next; // Keep original state
            node.val = next.val;
            next.val = temp;
            
            if (node.next.next == null) { // See if to the end
                node.next = null;
                return;
            }
                        
            node = next; // Go next
        }
    }
}
