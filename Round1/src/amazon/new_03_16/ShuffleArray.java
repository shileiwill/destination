package amazon.new_03_16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleArray {

	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 5, 4, 9, 7, 34, 54, 32};
		
		// Implement Fisher-Yates shuffle algorithm
		Random ran = new Random();
		for (int i = arr.length - 1; i >= 0; i--) {
			int ranIndex = ran.nextInt(i + 1);
			
			int ranVal = arr[ranIndex];
			arr[ranIndex] = arr[i];
			arr[i] = ranVal;
		}
		
//		for (int val : arr) {
//			System.out.print(val + "--");
//		}
		
		ShuffleArray.shuffle();
	}
	
	static void shuffle() {
		int[] arr = {1, 2, 3, 5, 4, 9, 7, 34, 54, 32};
		List<Integer> list = new ArrayList<Integer>();
		
		for (int val : arr) {
			list.add(val);
		}
		
		Collections.shuffle(list);
		
		for (int val : list) {
			System.out.print(val + "--");
		}
	}

}
