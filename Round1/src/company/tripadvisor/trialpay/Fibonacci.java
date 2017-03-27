package company.tripadvisor.trialpay;

public class Fibonacci {

	public static void main(String[] args) {
		Fibonacci fib = new Fibonacci();
		int res = fib.getFib(6);
		System.out.println(res);
	}

	// Get Nth Fibonacci number
	int getFib(int N) {
		if (N == 0) {
			return 0;
		}
		
		if (N == 1) {
			return 1;
		}
		
		return getFib(N - 1) + getFib(N - 2);
	}
}
