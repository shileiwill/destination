package chapter4.linkedlist;

import java.util.HashMap;
/**
 * 138. A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
 * @author Lei
 *
 */
public class CopyListWithRandomPointer {
	public static void main(String[] args) {
		RandomListNode n1 = new RandomListNode(1);
		RandomListNode n2 = new RandomListNode(2);
		RandomListNode n3 = new RandomListNode(3);
		RandomListNode n4 = new RandomListNode(4);
		RandomListNode n5 = new RandomListNode(5);
		n1.next = n2;
		n1.random = n1;
		
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		
		n2.random = n5;
		n3.random = null;
		n4.random = n2;
		n5.random = n1;
		
		RandomListNode res = new CopyListWithRandomPointer().copyRandomList(n1);
		System.out.println(res.label);
	}

	// My new version
    public RandomListNode copyRandomList3(RandomListNode head) {
        RandomListNode cur = head;
        while (cur != null) {
           RandomListNode copy = new RandomListNode(cur.label);
           RandomListNode next = cur.next;
           cur.next = copy;
           copy.next = next;
           cur = next;
        }
        
        RandomListNode cur2 = head; // Head is double size now
        while (cur2 != null) {
            RandomListNode copy = cur2.next;
            RandomListNode next = cur2.next.next; // 保留现场
            if (copy.next != null) {
                copy.next = copy.next.next;
            }
            if (cur2.random != null) {
                copy.random = cur2.random.next;
            }
            
            cur2 = next;
        }
        
        return head.next;
    }
	public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return head;
        }
        copyNext(head);
        copyRandom(head);
        return split(head);
    }
    
    void copyNext(RandomListNode head) {
        // Seems we dont need to use cursor, just move head
        RandomListNode cursor = head;
        
        while (cursor != null) {
            RandomListNode aNode = new RandomListNode(cursor.label);
            RandomListNode next = cursor.next;
            cursor.next = aNode;
            aNode.next = next;
            
            cursor = cursor.next.next;
        }
    }
    
    void copyRandom(RandomListNode head) {
        RandomListNode cursor = head;
        
        while (cursor != null) {
            RandomListNode copy = cursor.next;
            RandomListNode ran = cursor.random;
            
            if (ran != null) {
                copy.random = ran.next;
            }
            
            cursor = cursor.next.next;
        }
    }
    
    RandomListNode split(RandomListNode head) {
        // Simply take a note a new head
        RandomListNode newHead = head.next;
        
        while (head != null) {
            RandomListNode temp = head.next;
            head.next = temp.next;
            head = head.next;
            if (temp.next != null) {
                temp.next = temp.next.next;
            }
        }
        
        return newHead;
    }
    
    
    public RandomListNode copyRandomListHashMap(RandomListNode head) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode cur = dummy;
        
        while (head != null) {
            // Next
            if (!map.containsKey(head)) {
                map.put(head, new RandomListNode(head.label));
            }
            RandomListNode aCopy = map.get(head);
            cur.next = aCopy;
            
            // Random
            if (head.random != null && !map.containsKey(head.random)) {
                map.put(head.random, new RandomListNode(head.random.label));
            }
            aCopy.random = map.get(head.random);
            
            // Move forward
            head = head.next; 
            cur = cur.next;
        }
        
        return dummy.next;
    }
}

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
};
