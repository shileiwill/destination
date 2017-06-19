package company.facebook;

public class NumberOf1Bits {
	public static void main(String[] args) {
		NumberOf1Bits num1 = new NumberOf1Bits();
		
		int res = num1.faster(11);
		System.out.println(res);
	}
	
	int hammingWeight(int val) {
		int count = 0;
		
		for (int i = 0; i < 32; i++) {
			int num = val >> i;
			
			if ((num & 1) == 1) {
				count++;
			}
		}
		
		return count;
	}
	
	int faster(int val) {
		int count = 0;
		
		while (val != 0) {
			count++;
			val = (val & (val - 1)); // Flip last 1 to 0
		}
		
		return count;
	}
}
