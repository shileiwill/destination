package chapter5.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Given n items with size Ai and value Vi, and a backpack with size m. What's the maximum value can you put into the backpack?

 Notice

You cannot divide item into small pieces and the total size of items you choose should smaller or equal to m.

Have you met this question in a real interview? Yes
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4], and a backpack with size 10. The maximum value is 9.
 * @author Lei
 *
 */
public class Backpack2 {

	public static void main(String[] args) {
		// 100, [77,22,29,50,99], [92,22,87,46,90]
		// Expect 133
		int m = 100;
		int[] A = {77,22,29,50,99};
		int[] V = {92,22,87,46,90};
		
		Backpack2 bp2 = new Backpack2();
		int res = bp2.backPackII(m, A, V);
		
		System.out.println(res);
	}
	
    public int backPackIIWorkingCorrectly(int m, int[] A, int V[]) {
        int[] hash = new int[m + 1]; 
        
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j >= A[i]; j--) {
                hash[j] = Math.max(hash[j], hash[j - A[i]] + V[i]);
            }
        }
        
        return hash[m];
    }

	// This solution is not working.
    public int backPackII(int m, int[] A, int V[]) {
        List<Item> list = new ArrayList<Item>();
        for (int i = 0; i < A.length; i++) {
            Item item = new Item(A[i], V[i]);
            list.add(item);
        }
        
        Collections.sort(list, new SVComparator());
        
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            if (m - item.size >= 0) {
                res += item.value;
                m -= item.size;
            }
        }
        
        return res;
    }
    

    

}

class SVComparator implements Comparator<Item> {
    public int compare(Item item1, Item item2) {
        return (int) ((item2.ratio) - (item1.ratio));
    }
}

class Item {
    public double size;
    public double value;
    public double ratio;
    
    Item(int size, int value) {
        this.size = size;
        this.value = value;
        this.ratio = this.value / (double)this.size;
    }
}