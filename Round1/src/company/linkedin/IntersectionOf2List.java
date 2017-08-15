package company.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import chapter4.linkedlist.ListNode;

public class IntersectionOf2List {
	public ListNode getIntersectionNodeSet(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<ListNode>();
        
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        
        return null;
    }
	
	public ListNode getIntersectionNodeSetWithCycle(ListNode headA, ListNode headB) {
        Set<ListNode> setA = new HashSet<ListNode>();
        Set<ListNode> setB = new HashSet<ListNode>();
        
        boolean hasCycle = false;
        while (headA != null) {
        	if (setA.contains(headA)) {
        		hasCycle = true;
        		break;
        	}
            setA.add(headA);
            headA = headA.next;
        }
        
        while (headB != null) {
            if (setA.contains(headB)) {
                return headB;
            }
            if (setB.contains(headB)) { // Has cycle
            	break;
            }
            
            setB.add(headB);
            headB = headB.next;
        }
        
        return null;
    }
    
	// Without cycle, easy
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        
        if (lenA < lenB) {
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }
        
        int diff = Math.abs(lenA - lenB);
        
        while (diff > 0) {
            headA = headA.next;
            diff--;
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
    
    int getLength(ListNode node) {
        int len = 0;
        
        while (node != null) {
            len++;
            node = node.next;
        }
        
        return len;
    }
    
    int getLength(ListNode node, ListNode end) {
        int len = 0;
        
        while (node != end) {
            len++;
            node = node.next;
        }
        
        return len;
    }
    
    // With cycle, hard. 
    // Meeting point could be in and out of cycle.
    public ListNode getIntersectionNodeWithCycle(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        
        ListNode cycleA = checkCycle(headA); // If there is no cycle, return value is null
        ListNode cycleB = checkCycle(headB);
        
        if ((cycleA == null && cycleB != null) || (cycleA != null && cycleB == null)) {
        	return null; // only one of them has cycle, so definitely no meet point
        }
        
        // From, to
        int lenA = getLength(headA, cycleA);
        int lenB = getLength(headB, cycleB);
        
        if (lenA < lenB) {
            ListNode tmp = headA;
            headA = headB;
            headB = tmp;
        }
        
        int diff = Math.abs(lenA - lenB);
        
        while (diff > 0) {
            headA = headA.next;
            diff--;
        }
        
        while (headA != cycleA && headB != cycleB) { // Check if the meet point is before the cycle, dont jump inside the cycle
            if (headA == headB) {
                return headA;
            }
            
            headA = headA.next;
            headB = headB.next;
        }
        
        if (cycleA == null && cycleB == null) {
        	return null; // If there is no cycle at all, everything is done
        }
        
        // If it comes here, meet point is in cycle, but need to check if it's the same cycle
        if (cycleA == cycleB) {
        	return cycleA; // They meet at the start of cycle
        }
        
        ListNode mover = cycleA.next;
        while (mover != cycleA) { // Check if they meet in the same cycle
        	if (mover == cycleB) {
        		return mover;
        	}
        	mover = mover.next;
        }
        
        // Both of them have cycle, but they are different cycles
        return null;
    }
    
    // Find the node where cycle starts
    ListNode checkCycle(ListNode node) {
    	ListNode fast = node;
    	ListNode slow = node;
    	
    	while (fast != null && fast.next != null) {
    		fast = fast.next.next;
    		slow = slow.next;
    		
    		// There is cycle
    		if (fast == slow) {
    			ListNode start = node;
    			while (start != slow) {
    				start = start.next;
    				slow = slow.next;
    			}
    			return start;
    		}
    	}
    	
    	return null;
    }
}

// Question:Get the intersection of two sorted array. Input interface:
class IntersectionWithIterator {
		Iterable<Integer> intersection(Iterator<Integer> a, Iterator<Integer> b) {
			List<Integer> res = new ArrayList<Integer>();
			Integer val1 = null;
			Integer val2 = null;    
			
			while ((val1 != null || a.hasNext()) && (val2 != null || b.hasNext())) {
				if (val1 == null) {
					val1 = a.next();
				}
				
				if (val2 == null) {
					val2 = b.next();
				}
				
				if (val1 == val2) {
					res.add(val1);
					val1 = null;
					val2 = null;
				} else if (val1 < val2) {
					val1 = null;
				} else {
					val2 = null;
				}
			}
			
			return res;
		}
		
		public static void main(String[] args) {
			Integer[] arr1 = {3, 4, 8, 9, 12, 14, 18};
			Integer[] arr2 = {1, 4, 7, 8, 10, 14, 19};
			List<Integer> list1 = Arrays.asList(arr1);
			List<Integer> list2 = Arrays.asList(arr2);
			Iterator<Integer> a = list1.iterator();
			Iterator<Integer> b = list2.iterator();
			
			IntersectionWithIterator in = new IntersectionWithIterator();
			Iterable<Integer> res = in.intersection(a, b);
			
			for (int val : res) {
				System.out.println(val);
			}
		}
}