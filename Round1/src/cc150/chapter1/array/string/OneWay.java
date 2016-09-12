package cc150.chapter1.array.string;

public class OneWay {

	public static void main(String[] args) {
		OneWay oa = new OneWay();
		String str1 = "pale";
		String str2 = "bake";
		
		boolean res = oa.oneAway(str1, str2);
		System.out.println(res);
	}

	boolean oneAway(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		
		String shorter = (len1 <= len2) ? str1 : str2;
		String longer =  (len1 <= len2) ? str2 : str1;
		
		int diff = Math.abs(len1 - len2);
		if (diff > 1) {
			return false;
		}
		
		boolean found = false;
		if (diff == 0) {
			for (int i = 0; i < len1; i++) {
				if (str1.charAt(i) != str2.charAt(i)) {
					if (found) {
						return false;
					}
					found = true;
				}
			}
		} else {
			int pos1 = 0;
			int pos2 = 0;
			
			while (pos1 != shorter.length() && pos2 != longer.length()) {
				if (shorter.charAt(pos1) == longer.charAt(pos2)) {
					pos1++;
					pos2++;
				} else {
					if (found) { // We can also use pos1 == pos2 to see if already found
						return false;
					}
					found = true;
					pos2++;
				}
			}
		}
		
		return true;
	}
}
