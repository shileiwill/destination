package cc150.chapter1;

public class Chapter1_2 {

	public static void main(String[] args) {
		Chapter1_2 c1 = new Chapter1_2();
		String str = "abced";
		String res = c1.reverse(str);
		System.out.println(res);
	}
	
	String reverse(String str) {
		char[] charArr = str.toCharArray();
		int left = 0, right = charArr.length - 1;
		
		while (left < right) {
			swap(charArr, left++, right--);
		}
		
		return new String(charArr);
	}
	
	void swap(char[] charArr, int left, int right) {
		char tmp = charArr[left];
		charArr[left] = charArr[right];
		charArr[right] = tmp;
	}

}
