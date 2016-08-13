package chapter2.sortedArrays;

import java.util.ArrayList;
import java.util.List;

/**
 * 160. Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
 * @author Lulu
 *
 */
public class IntersectionOf2LinkedList {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int stepsA = 0;
        int stepsB = 0;
        
        ListNode posA = headA;
        while (posA != null) {
            stepsA++;
            posA = posA.next;
        }
        
        ListNode posB = headB;
        while (posB != null) {
            stepsB++;
            posB = posB.next;
        }
        
        boolean isALonger = stepsA > stepsB;
        int diff = Math.abs(stepsA - stepsB);
        
        if (isALonger) {
            while (diff > 0) {
                headA = headA.next;
                diff--;
            }    
        } else {
            while (diff > 0) {
                headB = headB.next;
                diff--;
            }
        }
        
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        
        return null;
        
    }
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        List<ListNode> list = new ArrayList<ListNode>();
        
        ListNode posA = headA;
        while (posA != null) {
            list.add(posA);
            posA = posA.next;
        }
        
        ListNode posB = headB;
        while (posB != null) {
            if (list.contains(posB)) {
                return posB;
            }
            posB = posB.next;
        }
        
        return null;
    }
}