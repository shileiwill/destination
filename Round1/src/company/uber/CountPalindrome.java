package company.uber;
/**
 * Given a string, the task is to count all palindrome substring in a given string. Length of palindrome substring is greater then or equal to 2.
Examples:

Input : str = "abaab"
Output: 3
Explanation : All palindrome substring are : "aba" , "aa" , "baab" 

Input : str = "abbaeae"
Output: 4
Explanation : All palindrome substring are : "bb" , "abba" ,"aea","eae"
 */
public class CountPalindrome {

	public static void main(String[] args) {
		CountPalindrome cp = new CountPalindrome();
		cp.dp("abbaeae");
	}

	void dp(String s) {
		int N = s.length();
		boolean[][] hash = new boolean[N][N]; // Good, you got the idea to use a 2D boolean array
		
		for (int i = 0; i < N; i++) {
			hash[i][i] = true;
		}
		
		for (int i = 0; i < N - 1; i++) { // 把length为2的也算出来
			if (s.charAt(i) == s.charAt(i + 1)) {
				hash[i][i + 1] = true;
			}
		}
		
		for (int len = 3; len <= N; len++) { //用length!
			for (int i = 0; i + len <= N; i++) {
				char left = s.charAt(i);
				char right = s.charAt(i + len - 1);
				
				if (left == right) {
					hash[i][i + len - 1] = hash[i + 1][i + len - 2]; // Go inside
				} else {
					hash[i][i + len - 1] = false;
				}
			}
		}
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i != j && hash[i][j]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
}
