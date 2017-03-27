package company.expedia;

public class MatrixToX {

	public static void main(String[] args) {
		MatrixToX mtx = new MatrixToX();
		int[] arr = {2, 9 ,9};
		char[] res = mtx.updatePeakToX(arr);
		
		for (char c : res) {
			System.out.println(c);
		}
	}

	char[] updatePeakToX(int[] arr) {
		int len = arr.length;
		char[] res = new char[len];
		for (int i = 0; i < arr.length; i++) {
			res[i] = (char)(arr[i] + '0');
		}
		
		if (len <= 2) {
			return res;
		}
		
		for (int i = 1; i < len - 1; i++) {
			int prev = arr[i - 1];
			int now = arr[i];
			int next = arr[i + 1];
			
			if (now > prev && now > next) {
				res[i] = 'X';
			}
		}
		
		return res;
	}
}
