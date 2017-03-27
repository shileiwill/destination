package company.expedia;

public class EqualNumberInArray {

	public static void main(String[] args) {
		EqualNumberInArray equalNum = new EqualNumberInArray();
		int[] arr = {1, 2, 5};
		int count = equalNum.move(arr);
		
		System.out.println(count);
	}

	int moveWrong(int[] arr) {
		int sum = 0;
		for (int val : arr) {
			sum += val;
		}
		
		int avg = sum / arr.length;
		
		int count = 0;
		for (int val : arr) {
			count += Math.abs(val - avg);
		}
		
		return count;
	}
	
	int move(int[] arr) {
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min,  arr[i]);
		}
		
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			count += arr[i] - min;
		}
		
		return count;
	}
}
