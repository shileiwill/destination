package leetcode16.segmenttree.binaryindextree;
/**
 * Write a function that, for a given no n, finds a number p which is greater than or equal to n and is a power of 2.
 */
public class NextPowerOfTwo {

	// Power of 2 must have only one 1 digit
	static int nextPowerOf2(int num) {
		if (num == 0) {
			return 1;
		}
		
		if (num > 0 && (num & (num - 1)) == 0) {
			return num;
		}
		
		while ((num & (num - 1)) != 0) {
			num = (num & (num - 1));
		}
		
		return num << 1;
	}
	public static void main(String[] args) {
		int res = nextPowerOf2(7);
		System.out.println(res);
	}

}
