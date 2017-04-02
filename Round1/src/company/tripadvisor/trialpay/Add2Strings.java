package company.tripadvisor.trialpay;

public class Add2Strings {

	public static void main(String[] args) {
		Add2Strings ad = new Add2Strings();
		String s1 = "1223.45";
		String s2 = "341.2569";
		
		String[] sub1 = s1.split("\\.");
		String[] sub2 = s2.split("\\.");
		
		int len2 = sub2[1].length();
		int len1 = sub1[1].length();
		if (len1 < len2) {
			int i = 0;
			while (i < len2 - len1) {
				sub1[1] += "0";
				i++;
			}
		} else {
			int i = 0;
			while (i < len1 - len2) {
				sub2[1] += "0";
				i++;
			}
		}
		
		String ss1 = ad.addStrings(sub1[0], sub2[0]);
		String ss2 = ad.addStrings(sub1[1], sub2[1]);
		System.out.println(ss1 + "." + ss2);
	}

	String addStrings(String s1, String s2) {
		StringBuilder sb = new StringBuilder();
		
		int pos1 = s1.length() - 1, pos2 = s2.length() - 1;
		int carry = 0;
		
		while (pos1 >= 0 && pos2 >= 0) {
			int val1 = Integer.valueOf(s1.charAt(pos1) - '0');
			int val2 = Integer.valueOf(s2.charAt(pos2) - '0');
			
			int sum = val1 + val2 + carry;
			int digit = sum % 10;
			carry = sum / 10;
			
			sb.append(digit);
			
			pos1--;
			pos2--;
		}
		
		while (pos1 >= 0) {
			int val1 = Integer.valueOf(s1.charAt(pos1) - '0');
			
			int sum = val1 + carry;
			int digit = sum % 10;
			carry = sum / 10;
			
			sb.append(digit);
			pos1--;
		}
		
		while (pos2 >= 0) {
			int val2 = Integer.valueOf(s2.charAt(pos2) - '0');
			
			int sum = val2 + carry;
			int digit = sum % 10;
			carry = sum / 10;
			
			sb.append(digit);
			pos2--;
		}
		
		if (carry != 0) {
			sb.append(carry);
		}
		
		return sb.reverse().toString();
	}
}
