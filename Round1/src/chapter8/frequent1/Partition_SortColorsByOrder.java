package chapter8.frequent1;
/**
 * Given a string which contains only letters. Sort it by lower case first and upper case second.

 Notice

It's NOT necessary to keep the original order of lower-case letters and upper case letters.

Have you met this question in a real interview? Yes
Example
For "abAcD", a reasonable answer is "acbAD"
 * @author Lei
 *
 */
public class Partition_SortColorsByOrder {
    public void sortLetters(char[] chars) {
	    //write your code here
	    if (chars == null || chars.length == 0) {
	        return;
	    }
	    partition(chars, 0, chars.length - 1);
    }
    
    void partition(char[] chars, int left, int right) {
        while (left < right) {
            while (left < right && Character.isLowerCase(chars[left])) {
                left++;
            }
            
            while (left < right && Character.isUpperCase(chars[right])) {
                right--;
            }
            
            swap(chars, left, right);
        }
    }
    
    void swap(char[] nums, int left, int right) {
        char temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
