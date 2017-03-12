package amazon;

public class PowerOf2_Divide {
	// 是否是2的次方
	public int power(String a) {
	    
	    if (a.equals("1")) {
	        return 0;
	    }
    
        while (!a.equals("1") && !oddAtEnd(a)) {
            a = divide(a);
        }
        
        if (a.equals("1")) {
            return 1;
        }
        return 0;
	}
	
	String divide(String a) {
	    StringBuilder sb = new StringBuilder();
	    
	    int i = 0;
	    int carry = 0;
	    while (i < a.length()) {
	        String now = "";
	        if (carry != 0) {
	            now += carry;
	        }
	        
	        now += a.charAt(i);
	        int val = Integer.valueOf(now);
	        
	        int digit = val / 2;
	        carry = val % 2;
	        if (digit == 0) {
	            if (i != 0) {
        	        sb.append(digit + "");
	            }
	        } else {
	            sb.append(digit + "");
	        }
	        i++;
	    }
	    
	    return sb.toString();
	}
	
	boolean oddAtEnd(String s) {
	    char c = s.charAt(s.length() - 1);
	    int v = c - '0';
	    if (v % 2 != 0) {
	        return true;
	    }
	    return false;
	}
}
