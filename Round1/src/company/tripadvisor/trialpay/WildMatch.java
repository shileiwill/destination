package company.tripadvisor.trialpay;

public class WildMatch {

	public static void main(String[] args) {

	}

	// http://shmilyaw-hotmail-com.iteye.com/blog/2154716
	boolean matchByRecursion(String s, String p, int sIndex, int pIndex) {
		if (pIndex == p.length()) {
			return sIndex == s.length();
		}
		
		if (p.charAt(pIndex) == '*') {
			while (pIndex < p.length() && p.charAt(pIndex) == '*') {
				pIndex++;
			}
			
			while (sIndex < s.length()) {
				if (matchByRecursion(s, p, sIndex, pIndex)) {
					return true;
				}
				sIndex++;
			}
			
			return matchByRecursion(s, p, sIndex, pIndex);
		} else if (sIndex < s.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
			return matchByRecursion(s, p, sIndex + 1, pIndex + 1);
		} else {
			return false;
		}
	}
	
	boolean matchByDP(String s, String p) {
		int m = s.length();
		int n = p.length();
		
		boolean[][] hash = new boolean[m + 1][n + 1];
		hash[0][0] = true;
		
		for (int i = 1; i <= n; i++) {
			hash[0][i] = hash[0][i - 1] && p.charAt(i - 1) == '*';
		}
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
					hash[i][j] = true;
				} else if (p.charAt(j - 1) == '*') {
					int cur = i;
					while (cur > 0) {
						if (hash[cur - 1][j - 1]) {
							hash[i][j] = true;
							break;
						}
						cur--;
					}
				}
			}
		}
		
		return hash[m][n];
	}
	
	// Greedy
	public boolean isMatch(String s, String p) {
		int sIndex = 0;
		int pIndex = 0;
		int star = -1;
		int match = 0;
		
		while (sIndex < s.length()) {
			if (pIndex < p.length() && (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '?')) {
				pIndex++;
				sIndex++;
			} else if (pIndex < p.length() && p.charAt(pIndex) == '*') {
				star = pIndex;
				match = sIndex;
				pIndex++;
			} else if (star != -1) {
				match++;
				sIndex = match;
				pIndex = star + 1;
			} else {
				return false;
			}
		}
		
		while (pIndex < p.length()) {
			if (p.charAt(pIndex) != '*') {
				return false;
			}
			pIndex++;
		}
		
		return true;
	}
}
