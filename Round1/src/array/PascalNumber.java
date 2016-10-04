package array;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
 */
public class PascalNumber {
	public static void main(String[] args) {
		System.out.println(10%10);
	}
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows < 1) {
            return res;
        }
        
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        res.add(list);
        
        for (int row = 2; row <= numRows; row++) {
            list = new ArrayList<Integer>();
            list.add(1);
            
            List<Integer> last = res.get(res.size() - 1);
            for (int i = 1; i < last.size(); i++) {
                int sum = last.get(i - 1) + last.get(i);
                list.add(sum);
            }
            
            list.add(1);
            
            res.add(list);
        }
        
        return res;
    }
    
    /**
     * 119.
     * Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].

Note:
Could you optimize your algorithm to use only O(k) extra space?
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return list;
        }
        
        list.add(1);
        
        for (int row = 1; row <= rowIndex; row++) {
            List<Integer> list2 = new ArrayList<Integer>();
            list2.add(1);
            
            for (int i = 1; i < list.size(); i++) {
                int sum = list.get(i - 1) + list.get(i);
                list2.add(sum);
            }
            
            list2.add(1);
            
            list = list2;
        }
        
        return list;
    }
    
    // Avoid using the second list
    public List<Integer> getRowOneList(int rowIndex) {
        List<Integer> list = new ArrayList<Integer>();
        if (rowIndex < 0) {
            return list;
        }
        
        list.add(1);
        
        for (int row = 1; row <= rowIndex; row++) {
            list.add(0, 1); // Add the val in specific position
            
            for (int i = 1; i < list.size() - 1; i++) { // Avoid the first and the last element
                int sum = list.get(i) + list.get(i + 1);
                list.set(i, sum);
            }
        }
        
        return list;
    }
}
