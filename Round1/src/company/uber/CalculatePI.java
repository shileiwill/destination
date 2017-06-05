package company.uber;

import java.util.Random;

// http://www.stealthcopter.com/blog/2009/09/python-calculating-pi-using-random-numbers/
public class CalculatePI {

	public static void main(String[] args) {
		CalculatePI cpi = new CalculatePI();
		System.out.println(cpi.calculatePI());
	}

	double calculatePI() {
		Random ran = new Random();
		
		int N = 10000;
		int inside = 0;
		
		for (int i = 0; i < N; i++) {
			double x = ran.nextDouble(); // 0.0 - 1.0, not include 1
			double y = ran.nextDouble();
			
			if ((x * x + y * y) <= 1) {
				inside++;
			}
		}
		// 数量和面积的关系， 假设半径是1， Square的宽度也是1
		double res = (double)((4.0 * inside) / N);
		return res;
	}
}
