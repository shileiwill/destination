package company.yahoo;
// LC 91
public class DecodeWays {

	public static void main(String[] args) {

	}

	int numberOfWays(String s) {
		int[] hash = new int[s.length() + 1];
		
		hash[0] = 1;
		hash[1] = (s.charAt(0) == '0') ? 0 : 1;
		
		for (int i = 2; i <= s.length(); i++) {
			int digit1 = Integer.valueOf(s.substring(i - 1, i));
			int digit2 = Integer.valueOf(s.substring(i - 2, i));
			
			if (digit1 >= 1 && digit1 <= 9) {
				hash[i] += hash[i - 1];
			}
			
			if (digit2 >= 10 && digit2 <= 26) {
				hash[i] += hash[i - 2];
			}
		}
		
		return hash[s.length()];
	}
}
