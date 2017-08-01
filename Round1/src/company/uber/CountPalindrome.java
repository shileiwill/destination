package company.uber;
/**
 * Given a string, the task is to count all palindrome substring in a given string. Length of palindrome substring is greater than or equal to 2.
Examples:

Input : str = "abaab"
Output: 3
Explanation : All palindrome substring are : "aba" , "aa" , "baab" 

Input : str = "abbaeae"
Output: 4
Explanation : All palindrome substring are : "bb" , "abba" ,"aea","eae"

647 Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.
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
	
	// My new version, no difference
    public int countSubstrings(String s) {
        if (s == null) {
            return -1;
        }
        
        int len = s.length();
        int count = 0;
        
        boolean[][] hash = new boolean[len][len];
        
        for (int i = 0; i < len; i++) {
            hash[i][i] = true;
            count++;
        }
        
        for (int i = 1; i < len; i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                hash[i - 1][i] = true;
                count++;
            }
        }
        
        for (int l = 2; l < len; l++) {
            for (int i = 0; i + l < len; i++) {
                char c1 = s.charAt(i);
                char c2 = s.charAt(i + l);
                
                if (c1 == c2) {
                    if (hash[i + 1][i + l - 1]) {
                        hash[i][i + l] = true;
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
}
