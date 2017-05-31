package company.uber;

public class StrStr {

	public static void main(String[] args) {
		String source = "helloworld";
		String pattern = "word";
		
		StrStr ss = new StrStr();
		System.out.println(ss.strStr2(source, pattern));
	}

	// Regular
	int strStr1(String source, String pattern) {
		int start = 0;
		int pos1 = 0;
		int pos2 = 0;
		
		while (pos1 < source.length() && pos2 < pattern.length()) {
			char c1 = source.charAt(pos1);
			char c2 = pattern.charAt(pos2);
			
			if (c1 == c2) {
				pos1++;
				pos2++;
			} else {
				pos1 = ++start;
				pos2 = 0;
			}
		}
		
		if (pos2 == pattern.length()) {
			return start;
		}
		
		return -1;
	}
	
	int[] getKMP(String pattern) {
		int[] arr = new int[pattern.length()];
		int pos = 0;
		
		for (int i = 1; i < pattern.length();) {
			if (pattern.charAt(i) == pattern.charAt(pos)) {
				arr[i] = pos + 1; // Plus One!
				pos++;
				i++;
			} else {
				if (pos == 0) {
					arr[i] = 0;
					i++;
				} else {
					pos = arr[pos - 1];
				}
			}
		}
		
		return arr;
	}
	
	int strStr2(String source, String pattern) {
		int[] arr = getKMP(pattern);
		
		int pos1 = 0;
		int pos2 = 0;
		while (pos1 < source.length() && pos2 < pattern.length()) {
			if (source.charAt(pos1) == pattern.charAt(pos2)) {
				pos1++;
				pos2++;
			} else {
				if (pos2 == 0) {
					pos1++;
				} else {
					pos2 = arr[pos2 - 1];
				}
			}
		}
		
		return pos2 == pattern.length() ? pos1 - pos2 : -1;
	}
	
	/**
	 * 把所有自然数串成一个字符串"12345678910111213"，给定一个数，找其在串的起始位置
	 */
	int find(String source, String pattern) {
		
	}
}
