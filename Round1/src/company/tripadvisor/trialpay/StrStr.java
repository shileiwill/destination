package company.tripadvisor.trialpay;

public class StrStr {

	public static void main(String[] args) {
		StrStr str = new StrStr();
		String s1 = "helloword";
		String s2 = "lo";
		int res = str.strStrKMP(s1, s2);
		System.out.println(res);
	}

	int strStr(String s1, String s2) {
		char[] source = s1.toCharArray();
		char[] pattern = s2.toCharArray();
		
		int s = 0, p = 0;
		int index = 0;
		
		while (s < source.length && p < pattern.length) {
			char c1 = source[s];
			char c2 = pattern[p];
			
			if (c1 == c2) {
				s++;
				p++;
			} else {
				p = 0;
				index++;
				s = index;
			}
		}
		
		if (p == pattern.length) {
			return index;
		} else {
			return -1;
		}
	}
	
	int strStrKMP(String s1, String s2) {
		char[] source = s1.toCharArray();
		char[] pattern = s2.toCharArray();
		
		int[] helper = getKMP(pattern);
		
		int s = 0, p = 0;
//		int index = 0;
		while (s < source.length && p < pattern.length) {
			char c1 = source[s];
			char c2 = pattern[p];
			
			if (c1 == c2) {
				s++;
				p++;
			} else {
				if (p != 0) {
					p = helper[p - 1];
				} else {
					s++;
//					index++;
//					s = index;
				}
			}
		}
		
		if (p == pattern.length) {
			return s - p;
		} else {
			return -1;
		}
	}
	
	int[] getKMP(char[] pattern) {
		int[] kmp = new int[pattern.length];
		
		int index = 0;
		for (int i = 1; i < pattern.length;) {
			if (pattern[i] == pattern[index]) {
				kmp[i] = index + 1;
				index++;
				i++;
			} else {
				if (index != 0) {
					index = kmp[index - 1];
				} else {
					kmp[i] = 0;
					i++;
				}
			}
		}
		
		return kmp;
	}
}
