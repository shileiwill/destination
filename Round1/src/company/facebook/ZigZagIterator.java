package company.facebook;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * 281. Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
 */
public class ZigZagIterator {

    List<Integer> v1 = null;
    List<Integer> v2 = null;
    int pos1 = 0;
    int pos2 = 0;
    boolean isFirst = true;
    
    public ZigZagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = v1;
        this.v2 = v2;
        this.pos1 = 0;
        this.pos2 = 0;
    }

    public int next() {
        int res = -1;
        if (isFirst) {
            if (pos1 < v1.size()) {
                res = v1.get(pos1++);
            } else {
                res = v2.get(pos2++);
            }
        } else {
            if (pos2 < v2.size()) {
                res = v2.get(pos2++);
            } else {
                res = v1.get(pos1++);
            }
        }
        
        isFirst = !isFirst;
        return res;
    }

    public boolean hasNext() {
        if (pos1 < v1.size() || pos2 < v2.size()) {
            return true;
        }
        return false;
    }
}

//Extend to K
class ZigzagIteratorK {

    LinkedList<Iterator> list = null;
    public ZigzagIteratorK(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<Iterator>();
        if (!v1.isEmpty()) {
            list.addLast(v1.iterator());
        }
        if (!v2.isEmpty()) {
            list.addLast(v2.iterator());
        }
    }

    // Remove and then add if there are numbers left
    public int next() {
        Iterator it = list.removeFirst();
        Integer val = (Integer)it.next();
        if (it.hasNext()) {
            list.addLast(it);
        }
        
        return val;
    }

    public boolean hasNext() {
        return !list.isEmpty();
    }
}
/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */