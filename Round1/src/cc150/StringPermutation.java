package cc150;
/**
 * What is an elegant way to find all the permutations of a string.
 * E.g. ba, would be ba and ab, but what about abcdefgh? Is there any example Java implementation?
 */
import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

	public static void main(String[] args) {
		StringPermutation sp = new StringPermutation();
		List<String> res = sp.permutate("abcde");
		
		for (String s : res) {
			System.out.println(s);
		}
	}
	
	List<String> permutate(String str) {
		List<String> res = new ArrayList<String>();
		helper(res, "", str);
		return res;
	}
	
	void helper(List<String> res, String prefix, String remainder) {
		if (remainder.length() == 0) {
			res.add(prefix);
			return;
		}
		
		for (int i = 0; i < remainder.length(); i++) { // remainder的每一位都可以往prefix中放
			helper(res, prefix + remainder.charAt(i), remainder.substring(0, i) + remainder.substring(i + 1, remainder.length()));
		}
	}
}
