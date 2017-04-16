package company.yahoo;

public class LongestConsecutive {

	public static void main(String[] args) {
		LongestConsecutive lc = new LongestConsecutive();
		int[] arr = {3, 1, 4, 5, 6, 8, 9, 10, 11};
		int res = lc.findLongestConsecutive(arr);
		System.out.println(res);
	}
	
	int findLongestConsecutive(int[] arr) {
		int max = 1;
		int len = 1;
		
		for (int i = 1; i < arr.length;) {
			while (i < arr.length && arr[i] == arr[i - 1] + 1) {
				len++;
				i++;
			}
			max = Math.max(len, max);
			
			if (i < arr.length) { // Still have other possibilities
				len = 1; // Save current char, reset
				i++; // Go to next char
			}
		}
		
		return max;
	}
}
