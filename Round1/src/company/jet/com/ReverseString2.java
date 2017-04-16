package company.jet.com;
/**
 * 541. Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 */
public class ReverseString2 {

    public String reverseStr(String s, int k) {
        int left = 0, right = 0;
        boolean isFirstK = true;
        StringBuilder sb = new StringBuilder();
        
        while (right < s.length()) {
            while (right < s.length() && right - left < k) {
                right++;
            }    
            
            if (isFirstK) {
                String str = reverse(s, left, right - 1);
                sb.append(str);        
            } else {
                sb.append(s.substring(left, right));
            }
            
            isFirstK = !isFirstK;
            left = right;
        }
        
        return sb.toString();
    }
    
    String reverse(String s, int left, int right) {
        char[] arr = new char[right - left + 1];
        for (int i = left; i <= right; i++) {
            arr[i - left] = s.charAt(i);
        }
        
        left = 0; right = arr.length - 1;
        while (left < right) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            
            left++;
            right--;
        }
        
        return String.valueOf(arr);
    }

}
