package company.expedia;
// https://www.hackerrank.com/challenges/sherlock-and-the-beast
public class Sherlock {

	public static void main(String[] args) {
		Sherlock s = new Sherlock();
		String str = s.getNumber(21);
		System.out.println(str);
	}

	String getNumber(int N) {
		StringBuilder sb = new StringBuilder();
		
		int timesOf5 = N / 3;
		while (timesOf5 > 0) {
			int remainder = N - timesOf5 * 3;
			
			if (remainder % 5 != 0) {
				timesOf5--;
			} else {
				for (int i = 0; i < timesOf5 * 3; i++) {
					sb.append("5");
				}
				
				for (int i = 0; i < remainder; i++) {
					sb.append("3");
				}
				
				return sb.toString();
			}
		}
		
		// Only 3
		if (N % 5 == 0) {
			for (int i = 0; i < N; i++) {
				sb.append("3");
			}
			return sb.toString();
		}
		
		return sb.append("-1").toString();
	}
}
