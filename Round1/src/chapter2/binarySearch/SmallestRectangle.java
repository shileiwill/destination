package chapter2.binarySearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 302. An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
 * The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
 * Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.
For example, given the following image: 
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2, 

Return 6. 
 */
public class SmallestRectangle {
	
	public static void main(String[] args) {
		SmallestRectangle sr = new SmallestRectangle();
		char[][] image = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
		sr.minArea(image, 0, 2);
	}
	
    // Breadth first search
    public int minAreaBFS(char[][] image, int x, int y) {
        int up = x;
        int down = x;
        int left = y;
        int right = y;
        
        int m = image.length;
        int n = image[0].length;
        
        Queue<int[]> queue = new LinkedList<int[]>();
        boolean[][] visited = new boolean[m][n];
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] indices = queue.poll();
            int x1 = indices[0];
            int y1 = indices[1];
            
            up = Math.min(up, x1);
            down = Math.max(down, x1);
            left = Math.min(left, y1);
            right = Math.max(right, y1);
            
            for (int i = 0; i < 4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];
                
                int[] index2 = {x2, y2};
                if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && !visited[x2][y2] && image[x2][y2] == '1') {
                    queue.offer(index2);
                    visited[x2][y2] = true;
                }
            }
        }
        
        return (down - up + 1) * (right - left + 1);
    }
    
	public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
	    int colMin = binarySearchCol(image, 0, y, true);
	    int colMax = binarySearchCol(image, y, n - 1, false);
	    int rowMin = binarySearchRow(image, 0, x, true);
	    int rowMax = binarySearchRow(image, x, m - 1, false);
	    
	    return (colMax - colMin + 1) * (rowMax - rowMin + 1);
    }
    
    int binarySearchRow(char[][] image, int lower, int upper, boolean isTop) {
        int res = upper; // Anything
        while (lower + 1 < upper) {
            int mid = lower + (upper - lower) / 2;
            if (isTop) {
                if (findInRow(image, mid)) {
                    upper = mid;
                } else {
                    lower = mid;
                }
            } else {
                if (findInRow(image, mid)) {
                    lower = mid;
                } else {
                    upper = mid;
                }
            }
        }
            
        return isTop ? (findInRow(image, lower) ? lower : upper) : (findInRow(image, upper) ? upper : lower );
    }
    
    boolean findInRow(char[][] image, int row) {
        for (int i = 0; i < image[row].length; i++) {
            if (image[row][i] == '1') {
                return true;
            }
        }
        return false;
    }
    
    int binarySearchCol(char[][] image, int left, int right, boolean isLeft) {
        int res = right; // Anything, could depend on isLeft
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (isLeft) {
                if (findInCol(image, mid)) {
                    right = mid;
                } else {
                    left = mid;
                }
            } else {
                if (findInCol(image, mid)) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
            
        return isLeft ? (findInCol(image, left) ? left : right) : (findInCol(image, right) ? right : left );
    }
    
    boolean findInCol(char[][] image, int col) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][col] == '1') {
                return true;
            }
        }
        return false;
    }
}
