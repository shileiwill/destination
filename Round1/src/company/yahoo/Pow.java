package company.yahoo;

public class Pow {

	public static void main(String[] args) {
		Pow p = new Pow();
		int res = p.pow(3, -8);
		System.out.println(res);
	}

	int pow(int a, int b) {
		if (b < 0) {
			a = 1 / a;
			b = -b;
		}
		
		if (b == 0) {
			return 1;
		}
		
		if (b == 1) {
			return a;
		}
		
		int half = pow(a, b / 2);
		
		return b % 2 == 0 ? half * half : half * half * a;
	}
}
