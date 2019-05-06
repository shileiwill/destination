package company.snapchat;

import java.util.HashMap;
import java.util.Map;
/**
 * 
 * 
 * https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=517188&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D9%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3088%5D%5Bvalue%5D%3D1%26searchoption%5B3088%5D%5Btype%5D%3Dradio%26searchoption%5B3046%5D%5Bvalue%5D%3D21%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311%26orderby%3Ddateline
矩阵相乘follow up 稀疏矩阵相乘
稀疏矩阵是这样表示的
原矩阵[[1, 0, 0], [0,1,0], [0,0,1]]
转化后[[0,0,1],[1,1,1],[2,2,1]]. 
也就是说只记录原矩阵中非零的slots. [row, col, val].

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

这两个输入如果是根据下标排序好的话应该怎么办，我说可以遍历长度较短的那一个，
然后用二分搜索的方法在另一个vector中找index相同的元素，相乘加入到结果中，这样的话复杂度就是O(M*logN)。
这时，面试官又问是否可以同时利用两个输入都是排序好这一个特性，我在这个地方有点卡住，但是在白板上写出一个test case，
试着用可视化的方法帮助我来进行思考，同时面试官给了一些提醒，最后写出了O(M + N)的双指针方法
 */
public class SparseMatrixMultiplication {

	public static void main(String[] args) {

	}

	// 如果两个table都有很多0
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
    
	// 如果只有一个table A 有很多0
    public int[][] multiply3(int[][] A, int[][] B) {
        Map<Integer, Map<Integer, Integer>> map1 = new HashMap<Integer, Map<Integer, Integer>>();
        
        for (int rowA = 0; rowA < A.length; rowA++) { // 把sparse的矩阵放到Map里，只存非零值
            for (int colA = 0; colA < A[rowA].length; colA++) {
                if (A[rowA][colA] != 0) {
                    if (!map1.containsKey(colA)) { // 以col为key, 因为第一个matrix的col是第二个的row。 用common的元素当key
                        map1.put(colA, new HashMap<Integer, Integer>());
                    }
                    map1.get(colA).put(rowA, A[rowA][colA]);
                }
            }
        }
        
        int[][] res = new int[A.length][B[0].length];
        
        for (int rowB = 0; rowB < B.length; rowB++) {
        	for (int colB = 0; colB < B[0].length; colB++) {
        		if (B[rowB][colB] != 0 && map1.containsKey(rowB)) {
        			for (int rowA : map1.get(rowB).keySet()) {
        				res[rowA][colB] += map1.get(rowA).get(rowB) * B[rowB][colB];
        			}
        		}
        	}
        }
        
        return res;
    }
    
    // 如果只有一个table B 有很多0. 这个简单些  因为A * B, 更好理解
    public int[][] multiply4(int[][] A, int[][] B) {
        if (A == null || A[0] == null || B == null || B[0] == null) return null;
        int m = A.length, n = A[0].length, l = B[0].length;
        int[][] res = new int[m][l];
        Map<Integer, HashMap<Integer, Integer>> tableB = new HashMap<>();
        
        for(int k = 0; k < n; k++) {
            tableB.put(k, new HashMap<Integer, Integer>()); // 无论有没有，扔进去，保证不空
            for(int j = 0; j < l; j++) {
                if (B[k][j] != 0){
                    tableB.get(k).put(j, B[k][j]);
                }
            }
        }

        for(int rowA = 0; rowA < m; rowA++) {
            for(int colA = 0; colA < n; colA++) {
                if (A[rowA][colA] != 0){
                    for (Integer colB: tableB.get(colA).keySet()) { // 这边不用check null
                        res[rowA][colB] += A[rowA][colA] * tableB.get(colA).get(colB);
                    }
                }
            }
        }
        return res;   
    }
    
    // 传统解法， 正常的两个Matrix
    public int[][] multiply1(int[][] A, int[][] B) {
        int len1 = A.length; // A's row count
        int len2 = A[0].length; // A's col count and B's row count
        int len3 = B[0].length; // B's col count
        int[][] res = new int[len1][len3];
        
        for (int rowA = 0; rowA < len1; rowA++) {
            for (int colA = 0; colA < len2; colA++) {
                if (A[rowA][colA] != 0) {
                    for (int colB = 0; colB < len3; colB++) {
                        res[rowA][colB] += A[rowA][colA] * B[colA][colB];
                    }
                }
            }
        }
        
        return res;
    }
}
