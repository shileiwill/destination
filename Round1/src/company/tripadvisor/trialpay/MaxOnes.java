package company.tripadvisor.trialpay;
// 题目是给一个元素为1或0的数组arr，对index i和j，数组arr1为arr把i到j之间(inclusive)的1变为0，0变为1，求对所有可能的i和j，arr1中1的数目的最大值。
public class MaxOnes {

	public static void main(String[] args) {
		int[] arr = {0};
		MaxOnes mo = new MaxOnes();
		mo.flip(arr);
	}

	// hash[i] is the count of 1 on left of i, inclusive. 
	// To every i and j, count = hash[i - 1] + (j - i + 1 - (hash[j] - hash[i - 1])) + hash[len - 1] - hash[j]
	int flip(int[] arr) {
		int len = arr.length;
		int[] hash = new int[len];
		
		// build hash array
		for (int i = 0; i < len; i++) {
			if (arr[i] == 1) {
				if (i == 0) {
					hash[i] = 1;
				} else {
					hash[i] = hash[i - 1] + 1;
				}
			} else {
				if (i == 0) {
					hash[i] = 0;
				} else {
					hash[i] = hash[i - 1];
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				res = Math.max(res, count(hash, i, j));
			}
		}
		
		System.out.println(res);
		return res;
	}
	
	int count(int[] hash, int i, int j) {
		int len = hash.length;
		
		if (i == 0) {
			return 0 + (j - i + 1 - (hash[j] - 0)) + hash[len - 1] - hash[j];
		} else {
			return hash[i - 1] + (j - i + 1 - (hash[j] - hash[i - 1])) + hash[len - 1] - hash[j];
		}
	}
}
