package company.facebook;
/**
 * 给两个string, leetcode, codyabc和一个数字k = 3,
 * 问两个string里面存不存在连续的common substring大于等于k.
 * 比如这个例子，两个string都有cod,所以返回true。
 * 楼主用dp建了一个m*n的table秒了，然后写test case,发现有个小corner case,改了,pass
Longest common substring

找两个字符串中长度为N以上的共同子串
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		String s1 = "leetcodabcde";
		String s2 = "codyabcdef";
		commonString(s1, s2, 3);
	}

	static boolean commonString(String s1, String s2, int k) {
		int m = s1.length();
		int n = s2.length();
		
		int[][] hash = new int[m + 1][n + 1];
		hash[0][0] = 1;
		
		for (int i = 1; i <= n; i++) {
			hash[0][i - 1] = 0;
		}
		
		for (int j = 1; j <= m; j++) {
			hash[j - 1][0] = 0;
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				char c1 = s1.charAt(i - 1);
				char c2 = s2.charAt(j - 1);
				
				if (c1 == c2) {
					hash[i][j] = hash[i - 1][j - 1] + 1;
				} else {
					hash[i][j] = Math.max(hash[i - 1][j], hash[i][j - 1]);
				}
				
				System.out.println("i : " + i + " : j : " + j + " : "+ hash[i][j]);
				if (hash[i][j] >= k) {
					System.out.println("found one!");
					int len = hash[i][j];
					String sub = s1.substring(i - len, i);
					System.out.println(sub);
					//return true;
				}
			}
		}
		
		return false;
	}
}
