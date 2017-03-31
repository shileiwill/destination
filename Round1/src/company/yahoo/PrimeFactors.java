package company.yahoo;

public class PrimeFactors {

	public static void main(String[] args) {
		PrimeFactors pf = new PrimeFactors();
		pf.findPrimeFactors(315);
	}

	void findPrimeFactors(int num) {
		while (num % 2 == 0) {
			System.out.print(2 + "--");
			num /= 2;
		}
		
		// Num is odd now
		for (int i = 3; i <= Math.sqrt(num); i += 2) { // Jump, because we filtered all Even factors already. Num is changing all the time
			while (num % i == 0) {
				System.out.print(i + "--");
				num /= i;
			}
		}
		
		if (num > 2) {
			System.out.print(num + "--");
		}
	}
}
