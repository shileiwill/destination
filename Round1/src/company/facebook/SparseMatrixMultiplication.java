package company.facebook;

import java.util.HashMap;
import java.util.Map;
/**
 * 311. Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
                  
可以用自己喜欢的数据结构，最简单的就是用一个map存啦，这样每次找value 只要O(1),
然后就问说如果有很多0怎么办，那就只存不为0的值，遍历A和B里面非0值比较少的，这样时间复杂度就是非0值的个数的较小值。
但是map存的话空间不够优化，就有说到两个指针的做法，用两个list来存。
还问如果一个很长，一个很短怎么办，极端情况就是B的非0值只有一个，A有10000个，那就从B开始乘，用二分找A的value list里面对应于B的index。
反正多沟通最重要，说出每种方法的优缺点，然后面试官让你写你再写
 */
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
