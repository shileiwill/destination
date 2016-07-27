package chapter5.dp;
/**
 * 72. Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Replace a character
 * @author Lei
 *
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        // It is necessary to add 1 extra digit
        int[][] hash = new int[word1.length() + 1][word2.length()+ 1];
        
        for (int i = 0; i <= word1.length(); i++) { // 0 is nothing, 1 is the first char
            hash[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            hash[0][i] = i;
        }
        
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    hash[i][j] = hash[i - 1][j - 1];
                } else {
                    hash[i][j] = Math.min(Math.min(hash[i - 1][j], hash[i][j - 1]), hash[i - 1][j - 1]) + 1;
                }
            }
        }
        
        return hash[word1.length()][word2.length()];
    }
}
