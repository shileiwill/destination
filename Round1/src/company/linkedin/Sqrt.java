package company.linkedin;

public class Sqrt {

	public static void main(String[] args) {
		Sqrt s = new Sqrt();
		double value = 22.2323242433333333333333333333333333333333333333333333333333333333333333333333334342;
//		System.out.println(Math.sqrt(value));
		System.out.println(s.sqrt(value));
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
}
