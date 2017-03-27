package company.expedia;
/**
 * 用海伦公式求三角形面积。海伦公式是：面 积=Math.sqrt(s(s-a)(s-b)(s-c))，其中s 是三角形的半周长，
 * a, b, c是三角形三条边的长度
 */
public class CheckNumInTriangle {

	public static void main(String[] args) {
		CheckNumInTriangle tri = new CheckNumInTriangle();
		
		int[] A = {-4, 0};
		int[] B = {5, 0};
		int[] C = {0, 8};
		int[] P = {4, 0};
		boolean area = tri.isInTriangle(P, A, B, C);
		System.out.println(area);
	}

	boolean isInTriangle(int[] P, int[] A, int[] B, int[] C) {
		double areaABC = area(A, B, C);
		double areaABP = area(A, B, P);
		double areaACP = area(A, C, P);
		double areaBCP = area(B, C, P);
		
		return areaABC == (areaABP + areaACP + areaBCP);
	}
	
	double area(int[] A, int[] B, int[] C) {
		int x1 = A[0];
		int y1 = A[1];
		int x2 = B[0];
		int y2 = B[1];
		int x3 = C[0];
		int y3 = C[1];
		
		return Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0;
	}
}
