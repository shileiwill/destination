package company.amazon;

public class ReverseBits {

	public static void main(String[] args) {
		long a = 3L;
		long res = reverse(a);
		System.out.println(res);
	}
	
	public static long reverse(long a) {
	    long res = 0L;
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < 32; i++) {
	        long lastDigit = (a >> i) & 1;
	        if (lastDigit == 1) {
//	            res |= (long)(Math.pow(2, 32 - i));
	        	sb.append("1");
	        } else {
	        	sb.append("0");
	        }
	    }
	    System.out.println(sb.toString());
	    return res;
	}
}
