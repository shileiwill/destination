package leetcode7.binarysearch;
/**
 * 374. We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number is higher or lower.

You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):

-1 : My number is lower
 1 : My number is higher
 0 : Congrats! You got it!
Example:
n = 10, I pick 6.

Return 6.
 */
public class GuessNumberHigherOrLower {
    public int guessNumber(int n) {
        return helper(1, n);
    }
    
    int helper(int left, int right) {
        int mid = left + (right - left) / 2;
        int res = guess(mid);
        if (res == 0) {
            return mid;
        } else if (res == -1) {
            return helper(left, mid - 1);
        } else {
            return helper(mid + 1, right);
        }
    }
    
    int guess(int a){
    	return 1;
    }
}
