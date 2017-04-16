package company.jet.com;
/**
 * 557. Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */
public class ReverseString3 {

    public String reverseWords(String s) {
        int left = 0, right = 0;
        StringBuilder sb = new StringBuilder();
        
        while (left < s.length()) {
            while (left < s.length() && s.charAt(left) == ' ') {
                sb.append(" ");
                left++; // left will be the first real letter
            }
            
            right = left;
            while (right < s.length() && s.charAt(right) != ' ') {
                right++; // right will be the first whitespace after left
            }
            
            if (left < right) {
                String r = reverse(s, left, right - 1);
                sb.append(r);
            }
            
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
