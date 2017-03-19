package amazon.new_03_16;

public class IntegerToEnglishWords {
	public String numberToWords(int num) {
		if (num == 0) {
			return "Zero";
		}
		String[] lessThan20 = {"", "One", "Two", "Three", "Four","Five", "Six", "Seven", "", "","", "", "", "", "","", "", "", "", 
				"","", "", "", "", "","", "", "", "", "","", "", "", "", "","", "", "", "Eighteen", "Nineteen"};
		String[] tens = {"Twenty", "Thirty", "Forty", "", "", "Ninty"};
		String[] hundreds = {"Billion", "Million", "Thousand", "Hundred"};
		int[] radix = {1000000000, 1000000, 1000, 100};
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < radix.length; i++) {
			if (num / radix[i] == 0) {
				continue;
			}
			int times = num / radix[i];
			//sb.append(times + hundreds[i] + " "); // It could be 13, should not be thir
			sb.append(numberToWords(times) + hundreds[i] + " ");
			num = num % radix[i];
		}
		
		if (num < 20) {
			sb.append(lessThan20[num]);
		} else {
			sb.append(tens[num / 10 - 2] + " " + lessThan20[num % 10]);
		}
		
		return sb.toString().trim();
	}
}
