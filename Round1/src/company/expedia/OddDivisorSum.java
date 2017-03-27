package company.expedia;

import java.util.HashMap;
import java.util.Map;

public class OddDivisorSum {
	
	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public static void main(String[] args) {
		int[] arr = {3, 4, 20};
		OddDivisorSum ods = new OddDivisorSum();
		
		int sum = 0;
		for (int num : arr) {
			int s = ods.getSumOfOddDivisor(num);
			System.out.println(s);
			sum += s;
		}
		
		System.out.println(sum);
	}

	int getSumOfOddDivisor(int num) {
		if (map.containsKey(num)) {
			return map.get(num);
		}
		
		int sum = 0;
		
		for (int i = 1; i <= num; i++) {
			if (i % 2 != 0 && num % i == 0) {
				sum += i;
			}
		}
		
		map.put(num, sum);
		return sum;
	}
}
