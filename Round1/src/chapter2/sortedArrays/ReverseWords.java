package chapter2.sortedArrays;
/**
 * 151. Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
 * @author Lei
 *
 */
public class ReverseWords {

	public static void main(String[] args) {
		ReverseWords r = new ReverseWords();
		String s = "   1  2 3    	34";
		r.reverseWords(s);
	}
	
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        // If no trim, the first element will be "" in above case.
        s = s.trim();
        // Use ReExp to filter all spaces. User double \
        String[] arr = s.split("\\s+");
        reverse(arr, 0, arr.length - 1);
        
        StringBuilder builder = new StringBuilder();

        for (String string : arr) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(string);
        }
        
        return builder.toString();
    }
    
    private void reverse(String[] str, int start, int end) {
    	while (start < end) {
    		String temp = str[start];
    		str[start] = str[end];
    		str[end] = temp;
    		start++;
    		end--;
    	}
    }

}
