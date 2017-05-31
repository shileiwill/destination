package company.linkedin;

import java.util.ArrayList;
import java.util.List;

public class MinWordDistance {

	public static void main(String[] args) {
		String[] arr = {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "makes";
		String word2 = "coding";
		
		MinWordDistance md = new MinWordDistance();
		System.out.println(md.shortestDistance(arr, word1, word2));
	}

	// What if the 2 words could be the same
	int shortestDistance(String[] arr, String word1, String word2) {
		int index1 = -1;
		int index2 = -1;
		int res = Integer.MAX_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			String word = arr[i];
			
			if (word1.equals(word)) {
				index1 = i;
				
				if (index2 != -1 && index2 != index1) {
					res = Math.min(res, index1 - index2);
				}
			}
			
			if (word2.equals(word)) {
				index2 = i;
				
				if (index1 != -1 && index2 != index1) {
					res = Math.min(res, index2 - index1);
				}
			}
		}
		
		return res;
	}
	
	// What if we want to calculate the shortest distance of 3 words
	int shortestDistance(String[] arr, String word1, String word2, String word3) {
		// First get the 3 lists which contains words
		List<Integer> list1 = new ArrayList<Integer>();
		List<Integer> list2 = new ArrayList<Integer>();
		List<Integer> list3 = new ArrayList<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			String word = arr[i];
			
			if (word.equals(word1)) {
				list1.add(i);
			} else if (word.equals(word2)) { // Use else since the 3 words are distinct
				list2.add(i);
			} else if (word.equals(word3)) {
				list3.add(i);
			}
		}
		
		int res = Integer.MAX_VALUE;
		int pos1 = 0, pos2 = 0, pos3 = 0;
		while (pos1 < list1.size() && pos2 < list2.size() && pos3 < list3.size()) {
			int index1 = list1.get(pos1);
			int index2 = list2.get(pos2);
			int index3 = list3.get(pos3);
			
			if (index1 < index2 && index1 < index3) {
				int manhattan = index2 - index1 + index3 - index1 + Math.abs(index2 - index3);
				res = Math.min(res, manhattan);
				pos1++;
			} else if (index2 < index1 && index2 < index3) {
				int manhattan = index1 - index2 + index3 - index2 + Math.abs(index1 - index3);
				res = Math.min(res, manhattan);
				pos2++;
			} else { // index3 is the smallest
				int manhattan = index2 - index3 + index1 - index3 + Math.abs(index2 - index1);
				res = Math.min(res, manhattan);
				pos3++;
			}
		}
		
		return res;
	}
	
}
