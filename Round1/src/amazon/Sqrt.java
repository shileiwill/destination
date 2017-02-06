package amazon;

public class Sqrt {
	// 给一个平方数,求平方根。就/2 /2 /2。。。 log(n)的复杂度
	// 限定条件必须是平方数 否则死循环
	public static void main(String[] args) {
		int N = 1024;
		int x = N;
		while (true) {
			x = x / 2;
			if (x * x == N) {
				System.out.println(x);
				break;
			}
		}
	}

}
