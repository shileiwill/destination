package company.tripadvisor.trialpay;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

	public static void main(String[] args) {

	}

	int N = 8;
	List<List<String>> nQueens() {
		List<List<String>> res = new ArrayList<List<String>>();
		List<Integer> list = new ArrayList<Integer>();
		
		dfs(res, list);
		return res;
	}
	
	void dfs(List<List<String>> res, List<Integer> list) {
		if (list.size() == N) {
			res.add(drawBoard(list));
			return;
		}
		
//		int row = list.size(); // Dont set row in this way, row is dynamic
		for (int col = 0; col < N; col++) {
			if (isValid(list, col)) {
				list.add(col);
				dfs(res, list);
				list.remove(list.size() - 1);
			}
		}
	}
	
	boolean isValid(List<Integer> list, int col) {
		int row = list.size();
		for (int i = 0; i < list.size(); i++) {
			int j = list.get(i);
			
			if (j == col || (i + j) == (row + col) || (i - j) == (row - col)) {
				return false;
			}
		}
		
		return true;
	}
}
