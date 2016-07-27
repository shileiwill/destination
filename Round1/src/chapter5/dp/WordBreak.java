package chapter5.dp;

import java.util.Set;

public class WordBreak {

	public static void main(String[] args) {

	}
	// As there is max length for words, we can optimize this by first get the max length, and then ignore any longer ones
    public boolean wordBreak(String s, Set<String> wordDict) {
        if (wordDict.size() == 0) {
            return false;
        }
        boolean[] can = new boolean[s.length() + 1];
        can[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) { // add if <= maxLen here
                String sub = s.substring(j, i);
                if (can[j] && wordDict.contains(sub)) {
                    can[i] = true;
                    break;
                }
            }
        }
        
        return can[s.length()];
    }
}
