package company.linkedin;

import java.util.ArrayList;
import java.util.List;
/**
 * Similar to 53. Maximum Subarray
 * what if the input is a stream
 */
public class MaxSubarray {

	public static void main(String[] args) {
		MaxSubarray ms = new MaxSubarray();
		ms.add(2);
		System.out.println("1 : " + ms.getMax());
		ms.add(-2);
		System.out.println("2 : " + ms.getMax());
		ms.add(-1);
		System.out.println("3 : " + ms.getMax());
		ms.add(5);
		System.out.println("4 : " + ms.getMax());
		ms.add(3);
		System.out.println("5 : " + ms.getMax());
		ms.add(1);
		System.out.println("6 : " + ms.getMax());
		ms.add(-7);
		System.out.println("7 : " + ms.getMax());
		ms.add(16);
		System.out.println("8 : " + ms.getMax());
	}

	List<Integer> sum = new ArrayList<Integer>();
	int min = 0;
	int max = 0;
	
	MaxSubarray() {
		sum.add(0);
	}
	
    public void add(int num) {
        sum.add(sum.get(sum.size() - 1) + num);
        
        max = Math.max(max, sum.get(sum.size() - 1) - min);
        min = Math.min(min, sum.get(sum.size() - 1));
    }
    
    int getMax() {
        return max;
    }
}
