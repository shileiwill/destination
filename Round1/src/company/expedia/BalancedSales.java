package company.expedia;

public class BalancedSales {

	public static void main(String[] args) {
		BalancedSales bs = new BalancedSales();
		int[] sales = {17, 3, 5, 0, 4, 2, 3};
		int pivot = bs.findPivot(sales);
		System.out.println(pivot);
	}

	int findPivot(int[] sales) {
		int len = sales.length;
		int[] sumFromLeft = new int[len];
		int[] sumFromRight = new int[len];
		
		sumFromLeft[0] = sales[0];
		for (int i = 1; i < len; i++) {
			sumFromLeft[i] = sumFromLeft[i - 1] + sales[i]; // Including i
		}
		
		sumFromRight[len - 1] = 0;
		for (int i = len - 2; i >= 0; i--) { // Not include i
			sumFromRight[i] = sumFromRight[i + 1] + sales[i + 1];
		}
		
		for (int i = 0; i < len; i++) {
			int sumLeft = sumFromLeft[i];
			int sumRight = sumFromRight[i];
			
			if (sumLeft == sumRight) {
				return i;
			}
		}
		
		return -1;
	}
}
