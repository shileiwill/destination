package company.linkedin;

public class EditDistance {

	public static void main(String[] args) {

	}
	// To count steps of edit distance, need to use DP
	public int minDistance(String word1, String word2) {
		int[][] hash = new int[word1.length() + 1][word2.length() + 1];
		
		for (int i = 0; i <= word1.length(); i++) {
			hash[i][0] = i;
		}
		
		for (int i = 0; i <= word2.length(); i++) {
			hash[0][i] = i;
		}
		
		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					hash[i][j] = hash[i - 1][j - 1];
				} else {
					hash[i][j] = Math.min(Math.min(hash[i - 1][j], hash[i][j - 1]), hash[i - 1][j - 1]) + 1;
				}
			}
		}
		
		return hash[word1.length()][word2.length()];
	}
	
	// This is one edit distance
	public boolean isOneEditDistance(String s, String t) {
		if (s == null || t == null) {
			return false;
		}

		if (s.length() == t.length()) {
			return change(s, t);
		} else {
			if (Math.abs(s.length() - t.length()) > 1) {
				return false;
			}
			return add(s, t);
		}
	}

	boolean add(String s, String t) {
		if (s.length() < t.length()) {
			return add(t, s);
		}

		int pos1 = 0, pos2 = 0;
		while (pos1 < s.length() && pos2 < t.length()) {
			if (s.charAt(pos1) != t.charAt(pos2)) {
				return s.substring(pos1 + 1).equals(t.substring(pos2));
			}
			pos1++;
			pos2++;
		}

		return true;
	}

	boolean change(String s, String t) {
		boolean found = false;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i)) {
				if (found) {
					return false;
				}
				found = true;
			}
		}

		return found;
	}
}