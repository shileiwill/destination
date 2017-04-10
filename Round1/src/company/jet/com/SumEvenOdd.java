package company.jet.com;

public class SumEvenOdd {

	public static void main(String[] args) {
		SumEvenOdd s = new SumEvenOdd();
		int[] N1 = {1, 2, 3};
		int[] N2 = {4, 9, 0};
		int[] N3 = {-4, 8, 3};
		int[][] N = {N1, N2, N3};
		
		int res = s.diff(N);
		System.out.println(res);
		//System.out.println(s.even + " : " + s.odd);
	}

	int even = 0, odd = 0;
	void cal(int[] N, int i) {
		if (i == N.length) {
			return;
		}
		
		if (i % 2 == 1) {
			odd += N[i];
		} else {
			even += N[i];
		}
		
		cal(N, i + 1);
	}
	
	// n * n
	int diff(int[][] N) {
		int len = N.length;
		
		int sum1 = 0;
		for (int i = 0; i < len; i++) {
			sum1 += N[i][i];
		}
		
		int sum2 = 0;
		for (int i = 0; i < len; i++) {
			sum2 += N[i][len - 1 - i];
		}
		
		return Math.abs(sum1 - sum2);
	}
}
