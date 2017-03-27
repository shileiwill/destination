package company.expedia;

public class SmallerThanK {

	public static void main(String[] args) {
		SmallerThanK stk = new SmallerThanK();
		int[] arr = {1, 2, 5, 8, 3, 9, 4, 5};
		int res = stk.smallerThanK(arr, 18);
		System.out.println(res);
	}

	int smallerThanK(int[] arr, int k) {
		int max = 0;
		
		for (int i = 0; i < arr.length; i++) {
			int sum = 0;
			for (int j = i; j < arr.length; j++) {
				sum += arr[j];
				
				if (sum <= k) {
					max = Math.max(max, j - i + 1);
				} else {
					break;
				}
			}
		}
		
		return max;
	}
}
