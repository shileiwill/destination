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

	// * can match any number from 0 to 9 好题
	public int decodeWays(String s) {
		int len = s.length();
		if (s == null || len == 0) {
			return 0;
		}

		int[] hash = new int[len + 1];
		hash[0] = s.charAt(0) == '0' ? 0 : 1;
		hash[1] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);

		for (int i = 2; i <= len; i++) {
			char cur = s.charAt(i - 1);
			char prev = s.charAt(i - 2);
			
			if (cur == '*') { // 0 - 9
				// single digit, 9 possibilities
				hash[i] += hash[i - 1] * 9;
				
				// Try 2 digits
				if (prev == '*') { // 2 consecutive stars could stand for 10 - 19, 20 - 26
					hash[i] += hash[i - 2] * 17; 
				} else if (prev == '1') {
					hash[i] += hash[i - 2] * 10; // 10 - 19
				} else if (prev == '2') { // 20 - 26
					hash[i] += hash[i - 2] * 7;
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

		return hash[len];
	}

	public static void main(String args[]) {
		DecodeWays dw = new DecodeWays();
		System.out.println(dw.numberOfWays("109"));
		System.out.print(dw.decodeWays("1*"));
	}
}
