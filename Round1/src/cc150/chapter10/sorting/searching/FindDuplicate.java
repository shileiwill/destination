package cc150.chapter10.sorting.searching;

import java.util.BitSet;

// With limited 4 kilobytes memory, use bit vector to check duplicate. Numbers are from 1 to N, where N is at most 32000. 
public class FindDuplicate {

	public static void main(String[] args) {
		FindDuplicate bubble = new FindDuplicate();
		int[] arr = {12, 4, 2, 17, 11, 28, 5, 2, 11};
		bubble.checkDuplicate(arr);
		for (int val : arr) {
			System.out.print(val + " - ");
		}
		System.out.println();
	}
	
	void checkDuplicate(int[] arr) {
		BitVector bv = new BitVector(32000);
		
		for (int val : arr) {
			int num = val - 1; // Number is from 1, while index in BitVector is from 0
			if (bv.get(num)) {
				System.out.println("Already exist : " + val);
			} else {
				bv.set(num);
			}
		}
	}
	
	// http://www.tutorialspoint.com/java/java_bitset_class.htm
	void checkDuplicateWithBitSetInJava(int[] arr) {
		BitSet bs = new BitSet(32000);
		
		// Set NUMth digit to true if it appears
		for (int val : arr) {
			int num = val - 1; 
			if (bs.get(num)) {
				System.out.println("Already exist : " + val);
			} else {
				bs.set(num);
			}
		}
	}
}

class BitVector {
	int[] vector = null;
	
	BitVector(int size) {
		vector = new int[size / 32 + 1];
	}
	
	boolean get(int num) {
		int bucket = num / 32;
		int position = num % 32;
		
		boolean exist = (vector[bucket] & (1 << position)) != 0;
		return exist;
	}
	
	void set(int num) {
		int bucket = num / 32;
		int position = num % 32;
		
		vector[bucket] |= (1 << position);
	}
}
