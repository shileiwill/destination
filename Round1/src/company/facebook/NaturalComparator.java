package company.facebook;

import java.util.Comparator;
/**
 * 自然string comparator。不知道的搜下。就是string 比较的时候考虑里面数字的大小，比如 abc9 < abc123 abc > ab9  因为char比digit重要。
 */
public class NaturalComparator implements Comparator<String> {

	public static void main(String[] args) {
		NaturalComparator nc = new NaturalComparator();
		
		String s1 = "abdc";
		String s2 = "abcc";
		
		int res = nc.compare(s1, s2);
		System.out.println(res);
	}

	@Override
	public int compare(String s1, String s2) {
		int pos1 = 0, pos2 = 0;
		
		while (pos1 < s1.length() && pos2 < s2.length()) {
			char c1 = s1.charAt(pos1);
			char c2 = s2.charAt(pos2);
			
			if (Character.isLetter(c1) && Character.isLetter(c2)) {
				if (c1 == c2) {
					pos1++;
					pos2++;
				} else {
					return c1 - c2;
				}
			} else if (Character.isDigit(c1) && Character.isDigit(c2)) {
				int pos11 = pos1 + 1;
				while (pos11 < s1.length() && Character.isDigit(s1.charAt(pos11))) {
					pos11++;
				}
				int num1 = Integer.valueOf(s1.substring(pos1, pos11));
				
				int pos22 = pos2 + 1;
				while (pos22 < s2.length() && Character.isDigit(s2.charAt(pos22))) {
					pos22++;
				}
				int num2 = Integer.valueOf(s2.substring(pos2, pos22));
				
				if (num1 == num2) {
					pos1 = pos11;
					pos2 = pos22;
				} else {
					return num1 - num2;
				}
			} else {
				if (Character.isLetter(c1)) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		
		if (pos1 == s1.length() && pos2 == s2.length()) {
			return 0;
		}
		
		return pos1 == s1.length() ? -1 : 1;
	}

}
