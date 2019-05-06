package company.snapchat;

import java.util.HashMap;
import java.util.Map;

public class PhoneInterview {

}


//Given u a 2d board, also a word, to find whether the word exists in the board
//4 directions, the same position cannot be used more than once
//board = {
//  {a, b, c},
//  {d, e, f},
//  {g, h, i}
//}
//List<String> words = {'ab', 'abc', 'bcf', 'efg', -----------------'}


Trie: convert the List of words to a Trie
   root
 a     b
/ \     \ 
b         c   
/           \ 
c             f 
//words.contains(string // O(N)
//words_set.contains(string) // O(1)
//return List<String> ab, abc, bcf

//word = abc => return true
//word = aei => return false

TrieNode root = new TrieNode();
for(String word : words) {
 root.insert(word, 0)
}


class TrieNode {
 Map<Character, TrieNode> children = null;
 boolean hasWord = false;
 String word = null;
 
 TrieNode() {
     this.children = new HashMap<Character, TrieNode>();
 }
 
 void insert(String word, int pos){
     if (pos == word.length()) {
         return;
     }
     
     char now = word.charAt(pos);
     
     if (this.children.containsKey(now)) {
         
     }
 }
 
 TrieNode search(String word, int pos) {
     
 }
}



//Input: char[][] board, String target
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
 public static void main(String args[] ) throws Exception {
     /* Enter your code here. Read input from STDIN. Print output to STDOUT */
     char[][] board0 = { // Super large, length of board is max int, height is max int
         {'a', 'b', 'c'},
         {'d', 'e', 'f'},
         {'g', 'h', 'i'}
     };
     
     char[][] board1 = {};
     char[][] board2 = null;
     
     String word0 = "cfe"; // true
     String word1 = "cfh"; // false
     String word2 = null;
     String word3 = "";
     String word4 = "aded"; // what if the char is used twice? false
     
     
     boolean res = exist(board0, word4);
     System.out.println(res);
 }
 
 static int m = -1;
 static int n = -1;
 static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
 
 public static boolean exist(char[][] board, String word) {
     // If board is not valid, cannot find anything
     if (board == null || board.length == 0) {
         return false;
     }
     
     // If word is empty or null, OK, we assume it is there
     if (word == null || word.length() == 0) {
         return true;
     }
     
     m = board.length;
     n = board[0].length; // null pointer?
     Set<Integer> visited = new HashSet<Integer>(); // id of the position. i * n + j. To explain
     // Set<int[]> check x && y. Map<Integer, Integer>
     // Check all positions in the board
     for(int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
             // check the first char in word
             if (word.charAt(0) == board[i][j]) { // If match
                 visited.add(i * n + j); // dont use this.  board[i][j] = 'a' 'A'
                 boolean res = helper(board, word, i, j, visited, 1); // Go deep
                 if (res) {
                     return true; // Return once found
                 }
                 visited.remove(i * n + j); // Clear
             }
         }
     }
     
     return false;
 }
 
 static boolean helper(char[][] board, String word, int i, int j, Set<Integer> visited, int pos) {
     if (pos == word.length()) {
         return true;
     }
     
     for(int[] dir : dirs) {
         int x = i + dir[0];
         int y = j + dir[1];
         int id = x * n + y;
         
         if (x >= 0 && x < m && y >= 0 && y < n && !visited.contains(id) && board[x][y] == word.charAt(pos)) {
             visited.add(id);
             boolean res = helper(board, word, x, y, visited, pos + 1);
             if(res) {
                 return true;
             }
             visited.remove(id);
         }
     }
     
     return false;
 }
}