package leetcode16.segmenttree.binaryindextree;
/**
 * For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)

Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

 Notice

It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.

Have you met this question in a real interview? Yes
Example
For array [0, 2, 3], the corresponding value Segment Tree is:

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
query(1, 1), return 0

query(1, 2), return 1

query(2, 3), return 2

query(0, 2), return 2
 */
public class SegmentTreeQuery2 {
    public int query(SegmentTreeNode root, int start, int end) {
        if (root == null) {
            return 0;
        }
        if (start <= root.start && end >= root.end) {
            return root.count;
        }
        
        int mid = (root.start + root.end) / 2;
        int count = 0;
        
        if (mid >= start) {
            count += query(root.left, start, end);
        }
        if (mid + 1 <= end ) {
            count += query(root.right, start, end);
        }
        
        return count;
    }
}
