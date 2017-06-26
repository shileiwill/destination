package company.uber;

import java.util.ArrayList;
import java.util.List;
/**
 * 返回可能的乘积组合 比如给12返回(2,6)(3,4)(2,2,3)
 */
public class FactorCombination {

	public static void main(String[] args) {
		FactorCombination fc = new FactorCombination();
		fc.combination(12);
	}
	
	// Backtracking
	List<List<Integer>> combination(int num) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> list = new ArrayList<Integer>();
		helper(res, list, num, num / 2);
		return res;
	}

	private void helper(List<List<Integer>> res, List<Integer> list, int target, int factor) {
		if (target == 1) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = factor; i >= 2; i--) {
			if (target % i == 0) {
				list.add(i);
				helper(res, list, target / i, i);
				list.remove(list.size() - 1);
			}
		}
	}
	
	// 从小往大行吗， 不行啊，2， 3 这些小数字可能是下一步的factor
	private void helper2(List<List<Integer>> res, List<Integer> list, int target, int factor) {
		if (target == 1) {
			for (int val : list) {
				System.out.print(val + "--");
			}
			System.out.println();
			res.add(new ArrayList<Integer>(list));
			return;
		}
		
		for (int i = factor; i >= 2; i--) {
			if (target % i == 0) {
				list.add(i);
				helper(res, list, target / i, i);
				list.remove(list.size() - 1);
			}
		}
	}
}
