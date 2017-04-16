package leetcode6.divideandconquer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
/**
 * 218. A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

 Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
public class Skyline {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    // https://discuss.leetcode.com/topic/28482/once-for-all-explanation-with-clean-java-code-o-n-2-time-o-n-space
    public List<int[]> getSkylineHeap(int[][] buildings) {
        List<int[]> res = new ArrayList<int[]>();
        List<int[]> height = new ArrayList<int[]>();
        
        // Each building/block, leftX, rightX and height
        for (int[] bld : buildings) { 
            // To differentiate left and right, make the height of left negative
            height.add(new int[]{bld[0], -bld[2]});
            height.add(new int[]{bld[1], bld[2]});
        }
        
        Collections.sort(height, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                // First sort by X, from small to big
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                // As height of start point is negative, below will guarantee end point comes first
                return a[1] - b[1];
            }
        });
        
        // maxHeap : Poll will get the max
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });
        
        maxHeap.offer(0); // This will make life easier, no one will be smaller than this
        int prevMaxHeight = 0;
        
        // In every critical point, search for the highest point to from the [x, y]
        for (int[] h : height) {
            if (h[1] < 0) { // A start point
                maxHeap.offer(-h[1]);
            } else {
                maxHeap.remove(h[1]);
            }
            
            // Dont take out, only remove when it is end point
            int curMaxHeight = maxHeap.peek();
            
            if (curMaxHeight != prevMaxHeight) {
                // This means the newly added height is the highest
                res.add(new int[]{h[0], curMaxHeight});
                prevMaxHeight = curMaxHeight;
            }
        }
        
        return res;
    }
    
    //Reference: http://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return new LinkedList<int[]>();
        }
        
        return divide(buildings, 0, buildings.length - 1);
    }
    
    LinkedList<int[]> divide(int[][] buildings, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            return merge(divide(buildings, left, mid), divide(buildings, mid + 1, right));
        } else if (left == right) { // Only 1 building, add its left line and right line
            // Critical Points/Vertical Lines
            LinkedList<int[]> res = new LinkedList<int[]>();
            res.add(new int[]{buildings[left][0], buildings[left][2]});
            res.add(new int[]{buildings[left][1], 0});
            return res;
        }
        // Will never come here
        return null;
    }
    
    LinkedList<int[]> merge(LinkedList<int[]> b1, LinkedList<int[]> b2) {
        LinkedList<int[]> res = new LinkedList<int[]>();
        int h1 = 0, h2 = 0;
        
        while (!b1.isEmpty() && !b2.isEmpty()) {
            int x, h = 0;
            if (b1.getFirst()[0] < b2.getFirst()[0]) { // Find the smaller X
                x = b1.getFirst()[0];
                h1 = b1.getFirst()[1];
                h = Math.max(h1, h2);
                
                b1.removeFirst();
            } else if (b1.getFirst()[0] > b2.getFirst()[0]) {
                x = b2.getFirst()[0];
                h2 = b2.getFirst()[1];
                h = Math.max(h1, h2);
                
                b2.removeFirst();
            } else { // X equals
                x = b1.getFirst()[0];
                h1 = b1.getFirst()[1];
                h2 = b2.getFirst()[1];
                h = Math.max(Math.max(h, h1), h2);
                
                b1.removeFirst();
                b2.removeFirst();
            }
            
            if (res.isEmpty() || res.getLast()[1] != h) {
                res.add(new int[]{x, h});
            }
        }
        // One of them is empty
        res.addAll(b1);
        res.addAll(b2);
        return res;
    }
}