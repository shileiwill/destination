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
	public static void main(String[] args) {
		int res = minDistance("ab", "a");
		System.out.println(res);
	}
	
	// Still not working. using length causes a lot of trouble
    public static int minDistance(String word1, String word2) {
        if (word1.length() == 0 && word2.length() == 0) {
            return 0;
        } else if (word1.length() == 0) {
            return word2.length();
        } else if (word2.length() == 0) {
            return word1.length();
        }
        // write your code here
        int[][] hash = new int[word1.length()][word2.length()];
        hash[0][0] = (word1.charAt(0) == word2.charAt(0)) ? 0 : 1;
        
        for (int i = 1; i < word1.length(); i++) {
            hash[i][0] = (word1.charAt(i) == word2.charAt(0)) ? i : i + 1;
            System.out.print(hash[i][0] + "--");
        }
        System.out.println();
        for (int i = 1; i < word2.length(); i++) {
            hash[0][i] = (word1.charAt(0) == word2.charAt(i)) ? i : i + 1;
            System.out.print(hash[0][i] + "--");
        }
        System.out.println();
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    hash[i][j] = Math.min(hash[i - 1][j - 1], Math.min(hash[i - 1][j], hash[i][j - 1]) + 1);
                } else {
                    hash[i][j] = Math.min(hash[i - 1][j - 1], Math.min(hash[i - 1][j], hash[i][j - 1])) + 1;
                }
                System.out.print(hash[i][j] + "--");
            }
            System.out.println();
        }
        
        return hash[word1.length() - 1][word2.length() - 1];
    }
    
    public int minDistance2(String word1, String word2) {
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
