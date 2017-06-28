package company.facebook;

import java.util.Arrays;
import java.util.List;

public class SortColor {
	public static void main(String[] args) {
		Integer[] arr = {0, 1, 2, 1, 2, 1, 0, 1, 1, 0, 0, 0, 2};
		List<Integer> a = Arrays.asList(arr);
		
		SortColor sc = new SortColor();
//		sc.sortColorsBetter(a);
		
		Integer[] arr2 = {-31013930, -31013930, 9784175, 21229755};
		
		for (int val : a) {
			System.out.print(val + "==");
		}
	}
	
	// Since we dont care which 0 and which 1, just point to left and right
	public void sortColorsBetter(List<Integer> a) {
		int left = 0, right = a.size() - 1;
		
		while (left < right) {
			while (left < right && a.get(left) == 0) {
				left++;
			}
			while (left < right && a.get(right) != 0) {
				right--;
			}
			
			if (left < right) {
				swap(a, left, right);
			}
		}
		
		right = a.size() - 1;
		while (left < right) {
			while (left < right && a.get(left) == 1) {
				left++;
			}
			while (left < right && a.get(right) != 1) {
				right--;
			}
			
			if (left < right) {
				swap(a, left, right);
			}
		}
	}
	
	public void sortColors(List<Integer> a) {
	    int pos1 = 0, pos2 = 0;
	    while (pos1 < a.size() && pos2 < a.size()) {
	        while (pos1 < a.size() && a.get(pos1) == 0) {
	            pos1++;
	        }
	        
	        pos2 = Math.max(pos2, pos1 + 1);
	        while (pos2 < a.size() && a.get(pos2) != 0) {
	            pos2++;
	        }
	        
	        if (pos1 < a.size() && pos2 < a.size()) {
	            swap(a, pos1, pos2);
	        }
	        
	        pos1++;
	        pos2++;
	    }
	    
	    int pos3 = pos1 - 1, pos4 = pos1 - 1;
	    while (pos3 < a.size() && pos4 < a.size()) {
	        while (pos3 < a.size() && a.get(pos3) == 1) {
	            pos3++;
	        }
	        
	        pos4 = Math.max(pos4, pos3 + 1);
	        while (pos4 < a.size() && a.get(pos4) != 1) {
	            pos4++;
	        }
	        
	        if (pos3 < a.size() && pos4 < a.size()) {
	            swap(a, pos3, pos4);
	        }
	        
	        pos3++;
	        pos4++;
	    }
	    
	}
	
	void swap(List<Integer> a, int pos1, int pos2) {
	    int tmp = a.get(pos1);
	    a.set(pos1, a.get(pos2));
	    a.set(pos2, tmp);
	}
}
