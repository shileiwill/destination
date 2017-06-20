package company.facebook;
/**
 * follow-up 如果不是只加兩個數 是加a list of string nums
想到用寫好的function (也想到LC 23 Merge 2 sorted list) 兩兩處理 討論時間空間複雜度 O(k n). k 是 num個數, n是一個binary string的長度
 */
public class AddBinaryString {

	public static void main(String[] args) {
		AddBinaryString abs = new AddBinaryString();
		String s1 = "1011";
		String s2 = "100101";
		String res = abs.addBinary(s1, s2, 2);
		System.out.println(res);
	}

	String addBinary(String s1, String s2, int base) {
		char[] arr1 = s1.toCharArray();
		char[] arr2 = s2.toCharArray();
		
		int pos1= arr1.length - 1;
		int pos2 = arr2.length - 1;
		
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		
		while (pos1 >= 0 && pos2 >= 0) {
			int val1 = arr1[pos1] - '0';
			int val2 = arr2[pos2] - '0';
			
			int sum = val1 + val2 + carry;
			int digit = sum % base;
			carry = sum / base;
			
			sb.insert(0, digit);
			pos1--;
			pos2--;
		}
		
		while (pos1 >= 0) {
			int val1 = arr1[pos1] - '0';
			
			int sum = val1 + carry;
			int digit = sum % base;
			carry = sum / base;
			
			sb.insert(0, digit);
			pos1--;
		}
		
		while (pos2 >= 0) {
			int val2 = arr2[pos2] - '0';
			
			int sum = val2 + carry;
			int digit = sum % base;
			carry = sum / base;
			
			sb.insert(0, digit);
			pos2--;
		}
		
		// You forget this.
		if (carry != 0) {
			sb.insert(0, carry);
		}
		
		return sb.toString();
	}
}
