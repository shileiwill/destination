package company.snapchat;
//重要
/**
 * How to add "and" and ","
 * 
 * It could be negative number
 */
public class IntegerToEnglishWords {
	public static String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}
		String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
				"Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
		String[] hundreds = {"Billion", "Million", "Thousand", "Hundred"};
		int[] radix = {1000000000, 1000000, 1000, 100};
		
		StringBuilder sb = new StringBuilder();
		
		// Handle negative numbers
		if (num < 0) {
			sb.append("Minus ");
			num = -num;
		}
		
		for (int i = 0; i < radix.length; i++) {
			if (num / radix[i] == 0) {
				continue;
			}
			int times = num / radix[i];
			//sb.append(times + hundreds[i] + " "); // It could be 13, should not be thir
			sb.append(numberToWords(times) + " " + hundreds[i] + " and ");
			num = num % radix[i];
		}
		
		if (num < 20) {
			sb.append(lessThan20[num]);
		} else {
			sb.append(tens[num / 10 - 2] + " " + lessThan20[num % 10]);
		}
		
		return sb.toString().trim();
	}
	
	public static void main(String[] args) {
		String res = numberToWords(-3435452);
		System.out.println(res);
	}
}
