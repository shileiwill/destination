package company.tripadvisor.trialpay;

public class CheckPalindromeWithSpace {
	public static void main(String[] args) {
		CheckPalindromeWithSpace check = new CheckPalindromeWithSpace();
		
		boolean res = check.check(" Hello tolleH ");
		System.out.println(res);
	}
	
	
	boolean check(String s) {
		char[] arr = s.toCharArray();
		
		int left = 0, right = arr.length - 1;
		
		while (left < right) {
			char c1 = arr[left];
			char c2 = arr[right];
			
			while (left < right && c1 == ' ') {
				c1 = arr[++left];
			}
			
			while (left < right && c2 == ' ') {
				c2 = arr[--right];
			}
			
			if (left < right) {
				if (c1 != c2) {
					return false;
				}
			}
			left++;
			right--;
		}
		
		return true;
	}
}
