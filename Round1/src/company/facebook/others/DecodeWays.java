package company.facebook.others;

/**
 * Created by weixwu on 7/2/2017.
 */
public class DecodeWays {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;
		int[] dp = new int[s.length() + 1];
		dp[0] = s.charAt(0) == '0' ? 0 : 1;
		dp[1] = dp[0];
		for (int i = 2; i <= s.length(); i++) {
			dp[i] += s.charAt(i - 1) - '0' != 0 ? dp[i - 1] : 0;
			dp[i] += s.charAt(i - 2) - '0' > 0 && (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26
					? dp[i - 2] : 0;
		}
		return dp[s.length()];
	}

	// My way is better
	int numberOfWays(String s) {
		int[] hash = new int[s.length() + 1];

		hash[0] = 1;
		hash[1] = 1;

		for (int i = 2; i <= s.length(); i++) {
			int digit1 = Integer.valueOf(s.substring(i - 1, i));
			int digit2 = Integer.valueOf(s.substring(i - 2, i));

			if (digit1 >= 1 && digit1 <= 9) {
				hash[i] += hash[i - 1];
			}

			if (digit2 >= 10 && digit2 <= 26) {
				hash[i] += hash[i - 2];
			}
		}

		return hash[s.length()];
	}

	/**
	 * 639. A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.
	 */
	// * can match any number from 0 to 9 好题 Leetcode上是match 1 - 9
	public int decodeWays(String s) {
		int len = s.length();
		if (s == null || len == 0) {
			return 0;
		}

		int[] hash = new int[len + 1];
		hash[0] = s.charAt(0) == '0' ? 0 : 1;
		hash[1] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);

		for (int i = 2; i <= len; i++) {
			char prev = s.charAt(i - 2);
			char cur = s.charAt(i - 1);
			
			if (cur == '*') { // 0 - 9
				// single digit, 9 possibilities
				hash[i] += hash[i - 1] * 9;
				
				// Try 2 digits
				if (prev == '*') { // 2 consecutive stars could stand for 11 - 19, 21 - 26
					hash[i] += hash[i - 2] * 15; // * is [1-9], so 11 - 26 except 20 
				} else if (prev == '1') {
					hash[i] += hash[i - 2] * 9; // 11 - 19
				} else if (prev == '2') { // 21 - 26
					hash[i] += hash[i - 2] * 6;
				}
			} else {
				// single digit
				if (cur != '0') { // 只要当前一位不是0
					hash[i] += hash[i - 1];
				}
				
				// Try 2 digits
				if (prev == '*') { // 前边一位是*
					if (cur >= '0' && cur <= '6') { // 前边一位是*， 当前位是 0 - 6， 能组队
						hash[i] += hash[i - 2] * 2; // * can stand for 2 cases, 1 or 2
					} else {
						hash[i] += hash[i - 2]; // * can stand for only 1 case, 1
					}
				} else {
					boolean lessThan26 = prev != '0' && (prev - '0') * 10 + (cur - '0') <= 26;
					if (lessThan26) {
						hash[i] += hash[i - 2];
					}
				}

			}
		}

		return hash[len] % (int)(Math.pow(10, 9) + 7);
	}

	public static void main(String args[]) {
		DecodeWays dw = new DecodeWays();
		System.out.println(dw.numberOfWays("109"));
		System.out.println(dw.decodeWays("1*"));
		System.out.println(dw.decodeWays("**********1111111111"));
		
		System.out.println("abc".substring(3)); // it doesnt throw exception if the parameter is length
	}
}
