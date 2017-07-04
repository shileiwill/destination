package company.linkedin;

public class Sqrt {

	public static void main(String[] args) {
		Sqrt s = new Sqrt();
		double value = 22.45433;
//		System.out.println(Math.sqrt(value));
		System.out.println(s.sqrtBinarySearch(value));
	}
	// If input is integer, use binary search

	// If it is double
	double sqrt(double x) {
		double sqr = 0;
		double offset = 1;

		while (true) {
			if (sqr * sqr == x) {
				break;
			} 
			
			// The time complexity could be O(length of Double result), which is huge. So, we could terminate based on precise
//			if (String.valueOf(sqr).length() > 10) {
//				break;
//			}
			
			// 根据精度terminate
			if (Math.abs(sqr * sqr - x) <= 0.00001) {
				break;
			}
			
			if (sqr * sqr > x) { // 有点大了
				sqr -= offset; // value调低
				offset /= 10; // 粒度调小
			}

			sqr += offset;
		}

		return sqr;

	}
	
	double sqrtBinarySearch(double x) {
		double left = 0;
		double right = x;
		double precision = 0.0000000000000000000000001;
		
		while (left < right) {
			double mid = (left + right) / 2.0;  // 都是double, 出来的mid也是double
			
			if (Math.abs(mid * mid - x) <= precision) {
				return mid;
			}
			
			if (mid * mid > x) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		return -1; // It will never come here. So, You can use while(true)
	}
	
	double cubicRoot(double x) {
		double left = 0;
		double right = x;
		double precision = 0.00000000000000000000001;
		
		while (true) {
			double mid = (left + right) / 2.0;
			
			if (Math.abs(mid * mid * mid - x) <= precision) {
				return mid;
			}
			
			if (mid * mid * mid > x) {
				right = mid;
			} else {
				left = mid;
			}
		}
	}
	
	// Not working
	double sqrt2(double x) {
		double sqr = 0;
		double offset = 1;
		int count = 0;

		while (true) {
			if (sqr * sqr == x) {
				break;
			} 
			
			// 根据小数位数terminate
			if (count == 2) {
				break;
			}
			
			if (sqr * sqr > x) { // 有点大了
				sqr = sqr - 1;
				sqr += offset; // value调低
				offset /= 10; // 粒度调小
				count++;//直接比较double不准，所以用count
			}

			sqr += offset;
		}

		return sqr;

	}
}
