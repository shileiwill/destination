package company.tripadvisor.trialpay;

public class WildMatch {

	public static void main(String[] args) {
		String s = "Wildcard";
		String p = "c*d";
		
		WildMatch wm = new WildMatch();
		wm.partialMatch(s, p);
	}

	void partialMatch(String s, String p) {
		for (int i = 0; i < s.length(); i++) {
			boolean res = partialMatchByRecursion(s, p, i, 0);
			if (res) {
				System.out.println(i);
				return;
			}
		}
	}
	
	boolean partialMatchByRecursion(String s, String p, int sIndex, int pIndex) {
		if (pIndex == p.length()) {
			return true;
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
					hash[i][j] = hash[i - 1][j - 1];
				} else if (p.charAt(j - 1) == '*') {
					int cur = i;
					while (cur >= 0) { // Changes are made here
						if (hash[cur][j - 1]) {
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
	
	public boolean isMatchFaster(final String s, final String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                        match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                        match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
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
