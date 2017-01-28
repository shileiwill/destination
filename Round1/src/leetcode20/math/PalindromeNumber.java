package leetcode20.math;
/**
 * 9. Determine whether an integer is a palindrome. Do this without extra space.

click to show spoilers.

Some hints:
Could negative integers be palindromes? (ie, -1)

If you are thinking of converting the integer to string, note the restriction of using extra space.

You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
 */
public class PalindromeNumber {

    public boolean isPalindrome2(int x) {
        char[] arr = (x + "").toCharArray();
        int i = 0, j = arr.length - 1;
        
        while (i < j) {
            if (arr[i] != arr[j]) {
                return false;
            }
            i++;
            j--;
        }
        
        return true;
    }
    
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int y = x;
        int reversed = 0;
        
        while (y != 0) {
            reversed = reversed * 10 + y % 10;
            y = y / 10;
        }
        
        return reversed == x;
    }

}
