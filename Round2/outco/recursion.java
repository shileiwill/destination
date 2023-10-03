recursion.java

/**

American Football Score

In a simplified game of American football, teams score points by either achieving a touchdown (7 points) or a field goal (3 points).

Given the score for a single team, please return how many different permutations of touchdowns and/or field goals exist in order to arrive at that score.

Input: Integer
Output: Integer

Examples:
Input:  10
Output: 2

Explanation: For a score of 10, the output would be 2. The 2 ways to arrive at
this score is to:

1) Score a field goal (3 points) and then touchdown (7 points)
2) Score a touchdown (7 points) and then field goal (3 points)


Input: 21
Output: 2

Explanation: For a score of 21, the output would be 2. The 2 ways to arrive at
this score is to:

1) Score 7 field goals (3 * 7 points)
2) Score 3 touchdowns (7 * 3 points)


Input:  16
Output: 4

Explanation: For a score of 16, the output would be 4. The 4 ways to arrive at
this score is to:

1) Score 1 touchdown (7 points) and 3 field goals (3 * 3 points)
2) Score 1 field goal (3 points), then 1 touchdown (7 points), and then 2 field goals (2 * 3 points)
3) Score 2 field goals (2 * 3 points), then 1 touchdown (7 points), and lastly 1 field goal (3 points)
4) Score 3 field goals (3 * 3 points) and then 1 touchdown (7 points)


Time Complexity: O(2^N)
Auxiliary Space Complexity: O(N)

https://github.com/OutcoSF/outcode_master/blob/master/whiteboarding/03_recursion/232_american_football_score.md
**/

public class HowManyWays {

    // Solution 1: Use a public variable, run recursion on the 2 scenarios
    static int res = 0;
    
    static void helper(int target) {
        if (target == 0) {
            res += 1;
            return;
        }
        
        if (target < 0) {
            return;
        }
        
        helper(target - 3);
        helper(target - 7);
    }
    
    // Solution 2: don't use public variable, use return instead.
    static int helper2(int target) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        
        int count1 = helper2(target - 3);
        int count2 = helper2(target - 7);
        
        return count1 + count2;

        // You can do memoization with this
        // if (map.containsKey(target)) {
        //     System.out.println("short circuited");
        //     return map.get(target);
        // }
    }

    // Solution 3: use stack to mock recursion
    static int count(int target) {
        Stack<Integer> stack = new Stack<>();
        stack.push(target);
        int res = 0;
        
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            
            if (cur == 0) {
                res++;
            }
            if (cur >= 3) {
                stack.push(cur - 3);
            }
            if (cur >= 7) {
                stack.push(cur - 7);
            }

            // Or just add a check on negative numbers, and skip the if checks on >=3 or >= 7
            // if (cur < 0) {
            //     continue;
            // }
            // stack.push(cur - 3);
            // stack.push(cur - 7);
        }
        return res;
    }
}


/**
 * 
784. Letter Case Permutation

Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.

Return a list of all possible strings we could create. Return the output in any order.

 
Example 1:

Input: s = "a1b2"
Output: ["a1b2","a1B2","A1b2","A1B2"]
Example 2:

Input: s = "3z4"
Output: ["3z4","3Z4"]
 * /

class LetterCasePermutation {

	// Solution 1: if it is only letters
	public static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }

    static void  helper(String s, int pos, String cur, List<String> res) {
        if (pos == s.length()) {
            res.add(cur);
            return;
        }
        char curChar = s.charAt(pos);
        helper(s, pos + 1, cur + Character.toUpperCase(curChar), res);
        helper(s, pos + 1, cur + Character.toLowerCase(curChar), res);
    }
    

	// Solution 2: Numbers included
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<String>();
        helper(s, 0, "", res);
        return res;
    }

    void helper(String s, int pos, String cur, List<String> res) {
        if (pos == s.length()) {
            res.add(cur);
            return;
        }

        char curChar = s.charAt(pos);
        if (Character.isLetter(curChar)) {
            helper(s, pos + 1, cur + Character.toUpperCase(curChar), res);
            helper(s, pos + 1, cur + Character.toLowerCase(curChar), res);
        } else {
            // is a number
            helper(s, pos + 1, cur + curChar, res);
        }
    }

    // Solution 3: Can we apply backtracking?
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        helper(s, new StringBuilder(), res);

        return res;
    }

    void helper(String originalStr, StringBuilder sb, List<String> res) {
        // when the pos reaches the end of original string, add current string to res
        if (sb.length() == originalStr.length()) {
            res.add(sb.toString());
            return;
        }
        
        int pos = sb.length();
        char curChar = originalStr.charAt(pos);
        char[] options = new char[]{Character.toLowerCase(curChar), Character.toUpperCase(curChar)};
        
        if (Character.isLetter(curChar)) {
            for (char curChar2 : options) {
                sb.append(curChar2);
                helper(originalStr, sb, res);
                sb.setLength(sb.length() - 1);
            }

            // If you dont want to use the options array, then just do
            // sb.append(Character.toLowerCase(curChar)); // try
            // helper(res, s, pos + 1, sb); // next recursion
            // sb.setLength(sb.length() - 1); // remove

            // sb.append(Character.toUpperCase(curChar)); // try
            // helper(res, s, pos + 1, sb); // next recursion
            // sb.setLength(sb.length() - 1); // remove
        } else {
                sb.append(curChar);
                helper(originalStr, sb, res);
                sb.setLength(sb.length() - 1);
        }
    }
}