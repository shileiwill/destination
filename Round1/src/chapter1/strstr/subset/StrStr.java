package chapter1.strstr.subset;

public class StrStr {

	public static void main(String[] args) {
		StrStr strstr = new StrStr();
		String haystack = "abcdefg";
		String needle = "ef";
		
		int res = strstr.strStr(haystack, needle);
		System.out.println(res);
	}

	public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        
        int i, j;
        for (i = 0; i < haystack.length() - needle.length() + 1; i++) { // Be careful with the stop point. i can start from everywhere
            // Search one character by character
            for (j = 0; j < needle.length(); j++) { // j starts at 0, always
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            // Successfully found
            if (j == needle.length()) {
                return i;
            }
        }
        
        return -1;
    }
    
    public int strStr2(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int lenA = haystack.length();
        int lenB = needle.length();
        if (lenB == 0) {
            return 0;
        }
        if (lenA == 0 || lenB == 0 || lenB > lenA) {
            return -1;
        }
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < lenA) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                i = res + 1;
                j = 0;
                res = i;
            }
            if (j == lenB) {
                return res;
            }
        }
        return -1;
    }
}
