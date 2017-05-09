package company.linkedin;
// There are n houses and k colors
public class PaintHouse2 {

	public static void main(String[] args) {

	}

	public int minCostII(int[][] costs) {
		int hc = costs.length;
		int cc = costs[0].length;
		
		int[][] hash = new int[hc][cc];
		int min1 = -1, min2 = -1;
		
		for (int i = 0; i < hc; i++) {
			int last1 = min1, last2 = min2;
			
			min1 = -1; // First time to enter this house
			min2 = -1;
			
			for (int j = 0; j < cc; j++) {
				if (j != last1) {
					hash[i][j] = costs[i][j] + last1 < 0 ? 0 : last1;
				} else {
					hash[i][j] = costs[i][j] + last2 < 0 ? 0 : last2;
				}
				
				if (min1 < 0 || hash[i][j] <= hash[i][min1]) {
					min2 = min1;
					min1 = j;
				} else if (min2 < 0 || hash[i][j] < hash[i][min2]) {
					min2 = j;
				}
			}
		}
		
		return hash[hc - 1][min1];
	}
}
