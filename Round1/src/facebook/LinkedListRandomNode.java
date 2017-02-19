package facebook;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import chapter4.linkedlist.ListNode;

public class LinkedListRandomNode {
    int len = 0;
    Random ran = new Random();
    Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    // public Solution(ListNode head) {
    //     while (head != null) {
    //         map.put(len, head);
    //         len++;
    //         head = head.next;
    //     }
    // }
    
    // /** Returns a random node's value. */
    // public int getRandom() {
    //     int ranIndex = ran.nextInt(len);
    //     return map.get(ranIndex).val;
    // }
    
    /*
    http://blog.jobbole.com/42550/
    When we read the first node head, if the stream ListNode stops here, we can just return the head.val. The possibility is 1/1.

When we read the second node, we can decide if we replace the result r or not. The possibility is 1/2. So we just generate a random number between 0 and 1, and check if it is equal to 1. If it is 1, replace r as the value of the current node, otherwise we don't touch r, so its value is still the value of head.

When we read the third node, now the result r is one of value in the head or second node. We just decide if we replace the value of r as the value of current node(third node). The possibility of replacing it is 1/3, namely the possibility of we don't touch r is 2/3. So we just generate a random number between 0 ~ 2, and if the result is 2 we replace r.

We can continue to do like this until the end of stream ListNode.
    */
    ListNode head = null;
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode now = head;
        ListNode res = head;
        int count = 0;
        
        while (now != null) {
            if (ran.nextInt(++count) == 0) { // Here can be (== count) as well
                res = now;
            }    
            now = now.next;
        }
        
        return res.val;
    }
}