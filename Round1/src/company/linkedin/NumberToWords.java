package company.linkedin;

public class NumberToWords {

	public static void main(String[] args) {
		NumberToWords ntw = new NumberToWords();
		double f = 1234434.34343;
		String str = ntw.numberToWord(f);
		System.out.println(str);
	}
	
	String numberToWord(int num) {
		if (num == 0) {
			return "Zero";
		}
		
        // Small to Large
        String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] TENS = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        // Large to Small
        String[] HUNDREDS = {"Billion", "Million", "Thousand", "Hundred"};
        int[] RADIX = {1000000000, 1000000, 1000, 100};
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < RADIX.length; i++) {
        	int times = num / RADIX[i];
        	
        	if (times == 0) {
        		continue;
        	} else {
        		sb.append(numberToWord(times) + " " + HUNDREDS[i] + " ");
        	}
        	
        	num = num % RADIX[i];
        }
        
        if (num < 20) {
        	sb.append(LESS_THAN_20[num] + " ");
        } else {
        	sb.append(TENS[num / 10 - 2] + " " + LESS_THAN_20[num % 10] + " ");
        }
        
        return sb.toString().trim();
	}

	String numberToWord(double num) {
		String str = String.valueOf(num);
		
		int dot = str.indexOf(".");
		if (dot != -1) {
			String firstPart = str.substring(0, dot);
			String secondPart = str.substring(dot + 1);
			int len = secondPart.length();
			
			int root = 1;
			for (int i = 0; i < len; i++) {
				root = root * 10;
			}
			
			return numberToWord(Integer.valueOf(firstPart)) + " " + secondPart + "/" + root; 
		} else {
			return numberToWord((int)(num));
		}
	}
}
