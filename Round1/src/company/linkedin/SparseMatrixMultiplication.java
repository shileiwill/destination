package company.linkedin;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

	public static void main(String[] args) {

	}

    public int[][] multiply2(int[][] A, int[][] B) {
        Map<Integer, Map<Integer, Integer>> map1 = new HashMap<Integer, Map<Integer, Integer>>();
        Map<Integer, Map<Integer, Integer>> map2 = new HashMap<Integer, Map<Integer, Integer>>();
        
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] != 0) {
                    if (!map1.containsKey(i)) {
                        map1.put(i, new HashMap<Integer, Integer>());
                    }
                    map1.get(i).put(j, A[i][j]);
                }
            }
        }
        
        for (int i = 0; i < B.length; i++) {
            for (int j = 0; j < B[i].length; j++) {
                if (B[i][j] != 0) {
                    if (!map2.containsKey(i)) {
                        map2.put(i, new HashMap<Integer, Integer>());
                    }
                    map2.get(i).put(j, B[i][j]);
                }
            }
        }
        
        int[][] res = new int[A.length][B[0].length];
        for (int row : map1.keySet()) {
            for (int col : map1.get(row).keySet()) {
                if (map2.containsKey(col)) {
                    
                    for (int k : map2.get(col).keySet()) {
                        res[row][k] += map1.get(row).get(col) * map2.get(col).get(k);
                    }
                }
            }
        }
        
        return res;
    }
    
    public int[][] multiply(int[][] A, int[][] B) {
        int len1 = A.length; // A's row count
        int len2 = A[0].length; // A's col count and B's row count
        int len3 = B[0].length; // B's col count
        int[][] res = new int[len1][len3];
        
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (A[i][j] != 0) {
                    for (int k = 0; k < len3; k++) {
                        res[i][k] += A[i][j] * B[j][k];
                    }
                }
            }
        }
        
        return res;
    }
}
