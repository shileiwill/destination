package company.uber;
/**
 * 1.一面国人大哥 给你一列数，从1，2，3，…… 输入一个n，返回第n个数和之前所有数字位数的总和。 
 * ps：function(1) = 1, function(9) = 9, function(10) = 11(10算两位) 一开始理解错意思以为是stream里面读取，后来发现是数学题，谢大哥 
 */
public class CountOfDigits_Math {

	public static void main(String[] args) {
		int N = 10;
		getLengthByString(N);
		getLengthByLog10(N);
		numlength(N);
	}

	private static void getLengthByString(int N) {
		int res = 0;
		for (int i = 1; i <= N; i++) {
			int len = String.valueOf(i).length();
			res += len;
		}
		
		System.out.println(res);
	}

	static int getLengthByLog10(int N) {
		int res = 0;
		for (int i = 1; i <= N; i++) {
			int length = (int)(Math.log10(i)+1);
			res += length;
		}
		System.out.println(res);
		return res;
	}
	
	// Divide Conquer is fastest, see below page
	//https://stackoverflow.com/questions/1306727/way-to-get-number-of-digits-in-an-int
	static int numlength(int n)
	{
	    if (n == 0) return 1;
	    int l;
	    n=Math.abs(n);
	    for (l=0;n>0;++l)
	        n/=10;
	    System.out.println(l);
	    return l;           
	}
}
