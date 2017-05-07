package company.linkedin;

public class ValidNum {

	public static void main(String[] args) {
		ValidNum vn = new ValidNum();
		System.out.println(vn.validNum("  2.32a"));
	}

	boolean validNum(String s) {
		if (s == null) {
			return false;
		}
		
		s = s.trim();
		boolean seenNum = false;
		boolean seenDot = false;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (c >= '0' && c <= '9') {
				seenNum = true;
			} else if (c == '.') {
				if (seenDot) {
					return false;
				}
				seenDot = true;
			} else if (c == '+' || c == '-') {
				if (i != 0) {
					return false;
				}
			} else {
				return false;
			}
		}
		
		return seenNum;
	}
}
