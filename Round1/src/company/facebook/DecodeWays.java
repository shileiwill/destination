package company.facebook;
// LC 91 很重要
public class DecodeWays {

	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		int res1 = dw.numberOfWaysRecursive("123");
		int res2 = dw.numberOfWays3("123");
		
		System.out.println(res1 + "==" + res2);
	}

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
	
	// 2^N
	int numberOfWaysRecursive(String s) {
		return helperMyStyle(s, 0);
	}
	
	private int helper(String s, int pos) {
		if (pos == s.length()) {
			return 1;
		}
		
		if (s.charAt(pos) == '0') {
			return 0;
		}
		
		// one digit
		int res = helper(s, pos + 1);
		
		// two digits
		if (pos < s.length() - 1 && (s.charAt(pos) == '1' || (s.charAt(pos) == '2' && s.charAt(pos + 1) <= '6'))) {
			res += helper(s, pos + 2);
		}
		
		return res;
	}
	
	// Better
	private int helperMyStyle(String s, int pos) {
		if (pos == s.length()) {
			return 1; // 总得有个地方不是0
		}
		
		int count = 0;
		
		int val1 = Integer.valueOf(s.substring(pos, pos + 1));
		if (val1 >= 1 && val1 <= 9) {
			count += helperMyStyle(s, pos + 1);
		}
		
		if (pos + 2 <= s.length()) {
			int val2 = Integer.valueOf(s.substring(pos, pos + 2));
			if (val2 >= 10 && val2 <= 26) {
				count += helperMyStyle(s, pos + 2);
			}
		}
		
		return count;
	}

	// 优化成三个变量
	int numberOfWays3(String s) {
		int num1 = 1;
		int num2 = 1;
		
		for (int i = 2; i <= s.length(); i++) {
			int num3 = 0;

			int digit1 = Integer.valueOf(s.substring(i - 1, i));
			int digit2 = Integer.valueOf(s.substring(i - 2, i));
			
			if (digit1 >= 1 && digit1 <= 9) {
				num3 += num2;
			}
			
			if (digit2 >= 10 && digit2 <= 26) {
				num3 += num1;
			}
			
			num1 = num2;
			num2 = num3;
		}
		
		return num2;
	}
	
	/*
	 * 1. 如果现在字符跟数字之间的map不是连续的怎么办，复杂度是多少。例如：1 -> 36， 2 -> 200之类的。我说如果我们有reverse map的话，还是可以o n，这里好虚
	 * 2. 见过一个follow up，但是限制最大是两位，可以用map来存一下，解法还是一样的。但是如果你这个不知道多少位的，就麻烦了
	 * 
	 * 3. 但是input String可以包含 *
	比如 1*2，可以有  102 （1），112 （3）， 122（3），132(2), 142(2), 152(2), 162(2), 172(2) 182(2) 192 (2)  一共 21种解法。
	返回多少解, 刚开始想还挺简单的，写起来并不简单，比如 1**1 ，两个*都要判断
	 */
	int numberOfWaysWithStar(String s) {
		int[] hash = new int[s.length() + 1];
		
		hash[0] = 1;
		hash[1] = s.charAt(0) == '*' ? 9 : 1; // 单独自己一位的时候不能选0
		
		for (int i = 2; i <= s.length(); i++) {
			if (s.charAt(i - 1) != '*' && s.charAt(i - 2) != '*') {
				int digit1 = Integer.valueOf(s.substring(i - 1, i));
				int digit2 = Integer.valueOf(s.substring(i - 2, i));
				
				if (digit1 >= 1 && digit1 <= 9) {
					hash[i] += hash[i - 1];
				}
				
				if (digit2 >= 10 && digit2 <= 26) {
					hash[i] += hash[i - 2];
				}
			} else if (s.charAt(i - 1) == '*') {
				int digit1 = 9;
				int digit2 = 0;
				
				if (s.charAt(i - 2) == 1) {
					digit2 = 10;
				} else if (s.charAt(i - 2) == 2) {
					digit2 = 7;
				}
				
				hash[i] += 
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
					hash[i] += hash[i - 1]; // 不要加一啥的
				}
				
				// Try 2 digits
				if (prev == '*') { // 前边一位是*
					if (cur >= '0' && cur <= '6') { // 前边一位是*， 当前位是 0 - 6， 能组队
						hash[i] += hash[i - 2] * 2; // * can stand for 2 cases, 1 or 2
					} else {
						hash[i] += hash[i - 2]; // * can stand for only 1 case, 1
					}
				} else { // 也可以分开讨论， prev==1, prev == 2
					boolean lessThan26 = prev != '0' && (prev - '0') * 10 + (cur - '0') <= 26;
					if (lessThan26) {
						hash[i] += hash[i - 2];
					}
				}

			}
		}

		return hash[len];
	}
}
