package chapter5.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Backpack2 {

	public static void main(String[] args) {
		// 100, [77,22,29,50,99], [92,22,87,46,90]
		// Expect 133
	}

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
            if (m - item.size > 0) {
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