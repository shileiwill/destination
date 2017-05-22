package company.tripadvisor.trialpay;

public class Divide {

	public static void main(String[] args) {
		Divide d = new Divide();
		
		int res = d.divide(1200, -32);
		System.out.println(res);
		
		String a = "ab";
		System.out.println(a.hashCode());
		
		int val = 'a';
		System.out.println(val);
		
		int val1 = 'b';
		System.out.println(val1);
	}
	// a / b
	int divide(int a, int b) {
		int flag = ((a < 0 && b < 0) || (a > 0 && b > 0)) ? 1 : -1;
		
		a = Math.abs(a);
		b = Math.abs(b);
		
		if (b == 0) {
			return Integer.MAX_VALUE;
		}
		if (b > a) {
			return 0;
		}
		
		int left = 1, right = a;
		
		while (true) {
			int mid = (left + right) / 2;
			
			if (mid > a / b) {
				right = mid - 1;
			} else {
				if ((mid + 1) > a / b) {
					return flag * mid;
				}
				left = mid + 1;
			}
		}
	}
}
