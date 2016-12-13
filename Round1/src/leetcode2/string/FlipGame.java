package leetcode2.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 293. You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves2(String s) {
        List<String> res = new ArrayList<String>();
        
        char[] arr = s.toCharArray();
        boolean prevPlus = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '+') {
                if (prevPlus) { // Find one
                    String one = buildString2(arr, i);
                    res.add(one);                    
                }
                prevPlus = true;
            } else {
                prevPlus = false;
                
            }
        }
        
        return res;
    }
    
    // The difference is that Arrays.copyOf does not only copy elements, it also creates a new array. System.arrayCopy copies into an existing array.
    String buildString2(char[] arr, int i) {
        // Must be deep copy.
        char[] copy = Arrays.copyOf(arr, arr.length);
        copy[i - 1] = '-';
        copy[i] = '-';
        return new String(copy);        
    }
    
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<String>();
        
        boolean prevPlus = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '+') {
                if (prevPlus) { // Find one
                    String str0 = s.substring(0, i - 1) + "--" + s.substring(i + 1);
                    res.add(str0);                    
                }
                prevPlus = true;
            } else {
                prevPlus = false;
                
            }
        }
        
        return res;
    }
}
