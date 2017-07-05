package company.linkedin;

import java.util.ArrayList;
import java.util.List;
/**
 * 254. Factor Combinations
 */
public class FactorCombination {

	public static void main(String[] args) {
		FactorCombination fc = new FactorCombination();
		List<Integer> factors = fc.getFactors(36);
		
		for (int factor : factors) {
			System.out.print(factor + "==");
		}
	}

	List<Integer> getFactors(int num) {
		List<Integer> res = new ArrayList<Integer>();
		
		while (num % 2 == 0) {
			res.add(2);
			num = num / 2;
		}
		
		for (int i = 3; i <= Math.sqrt(num); i += 2) {
			while (num % i == 0) {
				res.add(i);
				num = num / i;
			}
		}
		
		// Remember the last guy
		if (num > 2) {
			res.add(num);
//			System.out.print(num + "--");
		}
		
		return res;
	}
	
	List<List<Integer>> factCombination(int N) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		
		helper(res, list, N, N / 2);
		return res;
	}
	
	// factor is a position
	void helper(List<List<Integer>> res, List<Integer> list, int target, int pos) {
		if (target == 1) { // Termination point is 1
			res.add(new ArrayList<>(list));
			return;
		}
		
		for (int i = pos; i >= 2; i--) {
			if (target % i == 0) {
				list.add(i);
				helper(res, list, target / i, i);
				list.remove(list.size() - 1);
			}
		}
	}
}
