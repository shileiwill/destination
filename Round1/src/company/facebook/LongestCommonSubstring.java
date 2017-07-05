package company.facebook;
/**
 * 给两个string, leetcode, codyabc和一个数字k = 3,
 * 问两个string里面存不存在连续的common substring大于等于k.
 * 比如这个例子，两个string都有cod,所以返回true。
 * 楼主用dp建了一个m*n的table秒了，然后写test case,发现有个小corner case,改了,pass
Longest common substring

找两个字符串中长度为N以上的共同子串
这么经典的DP要记得啊
 */
public class LongestCommonSubstring {

	public static void main(String[] args) {
		String s1 = "leetcode";
		String s2 = "codyabc";
		commonString(s1, s2, 3);
	}

	static boolean commonString(String s1, String s2, int k) {
		int m = s1.length();
		int n = s2.length();
		
		int[][] hash = new int[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(j);
				
				if (c1 == c2) {
					hash[i][j] = ((i == 0 || j == 0) ? 0 : hash[i - 1][j - 1]) + 1;
				} else {
					hash[i][j] = 0;
				}
				
				if (hash[i][j] >= k) {
					System.out.println("found one!");
					return true;
				}
			}
		}
		
		return false;
	}
}
