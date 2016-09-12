package cc150.chapter1.array.string;

public class URLify {

	public static void main(String[] args) {
		URLify c1 = new URLify();
		String str = "Mr John Smith    ";
		char[] charArr = str.toCharArray();
		String res = c1.replace(charArr);
		
		System.out.println("--" + res + "--");
	}

	String replace(char[] charArr) {
		int i = charArr.length - 1;
		// Find the last non-empty digit
		while (i >= 0 && charArr[i] == ' ') {
			i--;
		}
		
		int j = charArr.length - 1;
		
		while (i >= 0) {
			char cur = charArr[i];
			if (cur == ' ') {
				charArr[j] = '0';
				charArr[j - 1] = '2';
				charArr[j - 2] = '%';
				j = j - 3;
			} else {
				charArr[j] = cur;
				j--;
			}
			i--;
		}
		
		return new String(charArr);
	}
}
