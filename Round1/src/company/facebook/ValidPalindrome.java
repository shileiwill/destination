package company.facebook;
/**
 * 125. Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            while (left < right && !Character.isLetterOrDigit(leftChar)) {
                left++;
                leftChar = s.charAt(left); // Remember to update value
            }
            while (left < right && !Character.isLetterOrDigit(rightChar)) {
                right--;
                rightChar = s.charAt(right);
            }
            
            if (Character.toLowerCase(leftChar) != Character.toLowerCase(rightChar)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
