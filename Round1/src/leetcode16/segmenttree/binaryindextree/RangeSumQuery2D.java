package leetcode16.segmenttree.binaryindextree;
/**
 * 308. Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
Note:
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQuery2D {
//https://discuss.leetcode.com/topic/30343/java-2d-binary-indexed-tree-solution-clean-and-short-17ms
//https://discuss.leetcode.com/topic/58909/segment-tree-solution-in-java
 int[][] tree = null;
 int[][] nums = null;
 int m = 0;
 int n = 0;
 
 public RangeSumQuery2D(int[][] matrix) {
     if (matrix.length == 0 || matrix[0].length == 0) return;
     m = matrix.length;
     n = matrix[0].length;
     
     tree = new int[m + 1][n + 1];
     nums = new int[m][n];
     
     for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
             update(i, j, matrix[i][j]);
         }
     }
 }
 
 public void update(int row, int col, int val) {
     if (m == 0 || n == 0) return;
     int delta = val - nums[row][col];
     nums[row][col] = val;
     for (int i = row + 1; i <= m; i += (i & -i)) {
         for (int j = col + 1; j <= n; j += (j & -j)) {
             tree[i][j] += delta;
         }
     }        
 }
 
 public int sumRegion(int row1, int col1, int row2, int col2) {
     if (m == 0 || n == 0) return 0;
     // 包括 不包括
     return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1) - sum(row2 + 1, col1);
 }
 
 int sum(int x, int y) {
     int sum = 0;
     for (int i = x; i > 0; i -= (i & -i)) {
         for (int j = y; j > 0; j -= (j & -j)) {
             sum += tree[i][j];
         }
     }
     return sum;
 }
}

/**
* Your NumMatrix object will be instantiated and called as such:
* NumMatrix obj = new NumMatrix(matrix);
* obj.update(row,col,val);
* int param_2 = obj.sumRegion(row1,col1,row2,col2);
*/