package company.facebook;

public class Fibonacci {
	// Get Nth Fibonacci number. Use Map to memorize
	int getFib(int N) {
		if (N == 0) {
			return 0;
		}
		
		if (N == 1) {
			return 1;
		}
		
		return getFib(N - 1) + getFib(N - 2);
	}
	
	int getFibIterative(int N) {
		int num1 = 0;
		int num2 = 1;
		
		if (N == 0) {
			return num1;
		} else if (N == 1) {
			return num2;
		}
		
		for (int i = 2; i <= N; i++) {
			int num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
		}
		
		return num2;
	}
	
	public static void main(String[] args) {
		Fibonacci fib = new Fibonacci();
		int res1 = fib.getFib(6);
		int res2 = fib.getFibIterative(6);
		System.out.println(res1 + "==" + res2);
	}
}

class FibIterator {
	int num1 = -1;
	int num2 = -1;

	public int next() {
		if (num1 == -1) {
			num1 = 0;
			return num1;
		} else if (num2 == -1) {
			num2 = 1;
			return num2;
		} else {
			int num3 = num1 + num2;
			num1 = num2;
			num2 = num3;
			return num3;
		}
	}
	
}