package leetcode10.sort;

import java.util.Arrays;
import java.util.Comparator;
/**
 * 179. Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber {
	public static void main(String[] args) {
		// How to make 9 > 99...
		// This will not work
		Comparator<String> com = new Comparator<String>() {
			public int compare(String s1, String s2) {
				int i = 0, j = 0;
				while (i < s1.length() && j < s2.length()) {
					char c1 = s1.charAt(i);
					char c2 = s2.charAt(j);
					
					if (c1 != c2) {
						return c1 - c2;
					}
					
					i++;
					j++;
				}
				
				return (i < s1.length()) ? -1 : 1;
			}
		};
		String[] arr = {"3", "30", "34", "5", "9"};
		Arrays.sort(arr, com);
		
		for (String s : arr) {
			System.out.print(s + " == ");
		}
		
	}
	
    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        
        Comparator<String> com = new Comparator<String>() {
            public int compare(String s1, String s2) {
                String sum1 = s1 + s2;
                String sum2 = s2 + s1;
                return sum2.compareTo(sum1); // Descending order. The role of sum2 is same as s2, think about this comparasion
            }
        };
        
        Arrays.sort(strs, com);
        
        if (strs[0].equals("0")) { // If the biggest is 0, then result will definitely be 0
            return "0";
        }
        
        StringBuilder res = new StringBuilder();
        for (String s : strs) {
            res.append(s);
        }
        
        return res.toString();
    }
}
