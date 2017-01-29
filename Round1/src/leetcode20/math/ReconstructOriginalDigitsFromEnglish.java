package leetcode20.math;
/**
 * 423. Given a non-empty string containing an out-of-order English representation of digits 0-9, output the digits in ascending order.

Note:
Input contains only lowercase English letters.
Input is guaranteed to be valid and can be transformed to its original digits. That means invalid inputs such as "abc" or "zerone" are not permitted.
Input length is less than 50,000.
Example 1:
Input: "owoztneoer"

Output: "012"
Example 2:
Input: "fviefuro"

Output: "45"
 */
public class ReconstructOriginalDigitsFromEnglish {

    public String originalDigits(String s) {
        int[] count = new int[26];
        // Calculate how many 0, 1, 2, 
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        int[] num = new int[10];
        num[0] = count['z' - 'a'];
        num[2] = count['w' - 'a'];
        num[4] = count['u' - 'a'];
        num[6] = count['x' - 'a'];
        num[8] = count['g' - 'a'];
        
        num[1] = count['o' - 'a'] - num[0] - num[2] - num[4];
        num[3] = count['r' - 'a'] - num[0] - num[4];
        num[5] = count['f' - 'a'] - num[4];
        num[7] = count['v' - 'a'] - num[5];
        num[9] = count['i' - 'a'] - num[5] - num[6] - num[8];
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < num[i]; j++) {
                sb.append(i + "");
            }
        }
        
        return sb.toString();
    }

}
