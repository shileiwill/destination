package chapter7.dataStructure;

import java.util.Stack;

/**
 * 85. Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
 * @author Lei
 *
 */
public class MaximalRectangle {
	// 这个题很难
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        
        // m行n列
        int[][] height = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    height[0][j] = (matrix[i][j] == '1') ? 1 : 0 ;
                } else {
                    height[i][j] = (matrix[i][j] == '1') ? height[i - 1][j] + 1 : 0;
                }
            }
        }
        
        int max = 0;
        // 对每一行求Largest Rectangle Histogram
        for (int i = 0; i < m; i++) {
            Stack<Integer> stack = new Stack<Integer>();
            for (int j = 0; j < n; j++) {
                while (!stack.isEmpty() && height[i][j] < height[i][stack.peek()]) {
                    int h = height[i][stack.pop()];
                    int width = (stack.isEmpty()) ? j : j - stack.peek() - 1;
                    max = Math.max(max, width * h);
                }
                stack.push(j);
            }
            
            while (!stack.isEmpty()) {
                int h = height[i][stack.pop()];
                int width = (stack.isEmpty()) ? n : n - stack.peek() - 1;
                max = Math.max(max, width * h);
            }
        }
        
        return max;
    }
}
