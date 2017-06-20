package company.facebook;
// LC 91
public class DecodeWays {

	public static void main(String[] args) {
		DecodeWays dw = new DecodeWays();
		int res1 = dw.numberOfWays("123");
		int res2 = dw.numberOfWays3("123");
		
		System.out.println(res1 + "==" + res2);
	}

	int numberOfWays(String s) {
		int[] hash = new int[s.length() + 1];
		
		hash[0] = 1;
		hash[1] = 1;
		
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
	
	// 优化成三个变量
	int numberOfWays3(String s) {
		int num1 = 1;
		int num2 = 1;
		int num3 = 0;
		
		for (int i = 2; i <= s.length(); i++) {
			int digit1 = Integer.valueOf(s.substring(i - 1, i));
			int digit2 = Integer.valueOf(s.substring(i - 2, i));
			
			if (digit1 >= 1 && digit1 <= 9) {
				num3 += num2;
			}
			
			if (digit2 >= 10 && digit2 <= 26) {
				num3 += num1;
			}
			
			num1 = num2;
			num2 = num3;
			num3 = 0;
		}
		
		return num2;
	}
}
