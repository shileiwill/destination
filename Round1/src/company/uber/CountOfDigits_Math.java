package company.uber;
/**
 * 1.一面国人大哥 给你一列数，从1，2，3，…… 输入一个n，返回第n个数和之前所有数字位数的总和。 
 * ps：function(1) = 1, function(9) = 9, function(10) = 11(10算两位) 一开始理解错意思以为是stream里面读取，后来发现是数学题，谢大哥 
 */
public class CountOfDigits_Math {

	public static void main(String[] args) {

	}

	int get(int N) {
		if (N < 10) {
			return N;
		}
		
		int next = N / 10;
		int mod = N % 10;
		
		res += get(N / 10);
		
		
	}
}
