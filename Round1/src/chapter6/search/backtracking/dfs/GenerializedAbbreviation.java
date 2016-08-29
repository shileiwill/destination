package chapter6.search.backtracking.dfs;
/**
 * 320. Write a function to generate the generalized abbreviations of a word.
Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */
import java.util.ArrayList;
import java.util.List;

public class GenerializedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<String>();
        backtrack(res, word, 0, "", 0);
        return res;
    }
    
    void backtrack(List<String> res, String word, int pos, String curStr, int count) {
        if (pos == word.length()) {
            if (count == 0) {
                res.add(curStr);
            } else {
                res.add(curStr + count);
            }
            return;
        }
        
        backtrack(res, word, pos + 1, curStr, count + 1); // Use numbers
        backtrack(res, word, pos + 1, curStr + (count > 0 ? count : "") + word.charAt(pos), 0); 
    }
}
