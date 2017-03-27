package company.amazon;
/**
 * 给两个列表，每个列表装着一样的元素，合并两个列表，并要求短的列表的元素把长列表元素尽可能均衡分开。
 * 比如列表为AAAAA和BBBBBB，返回BABABABABAB；又比如AAAAAA和BBBB，返回AABABABABA。
 */
public class Merge2Arrays {

	public static void main(String[] args) {
		char[] A = {'A', 'A', 'A', 'A'};
		char[] B = {'B', 'B', 'B', 'B', 'B', 'B'};
		char[] res = merge(B, A);
		for (char c : res) {
			System.out.print(c + "--");
		}
	}

	static char[] merge(char[] A, char[] B) {
		int lenA = A.length;
		int lenB = B.length;
		// Make A smaller, B larger
		if (lenA > lenB) {
			return merge(B, A);
		}
		
		char[] res = new char[lenA + lenB];
		int i = 0, j = 0;
		boolean pickB = true;
		
		while (i < lenA && j < lenB) {
			if (pickB) {
				res[i + j] = B[j];
				j++;
			} else {
				res[i + j] = A[i];
				i++;
			}
			pickB = !pickB;
		}
		
		// A must be done already, only B left
		while (j < lenB) {
			res[i + j] = B[j];
			j++;
		}
		
		return res;
	}
}
