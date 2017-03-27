package company.jet.com;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 是一道读取图片的问题。给一个矩阵， 0-1分布。0为图片。return图片的对角线位置。最简单的是遍历一遍，正着反着，很轻松就能搞定。 
 * 但是我有一个typo，然后一直没注意到，耽误了很长时间没有机会写第二题 第二题其实是升级版。一个矩阵内有多个图片。

对，0是代表图片。有可能图片是在边缘，这种情况就是最外面一层有0了。最外面一层的意思不是四个边都有0， 而是其中两边有0，即图片在矩阵边上。

1 1 1 1 1 1 
1 1 1 1 1 1 
1 1 0 0 0 0 
1 1 0 0 0 0

链接: https://instant.1point3acres.com/thread/195576


一个由0,1组成的二维matrix。 其中肯定有且只有一个由0组成的长方体，其他全是1. 找出这个长方体的左上角左边，长方体的长和高。 
{ {1,1,1,1,1,1}, {1,0,0,0,1,1}, {1,0,0,0,1,1}, {1,1,1,1,1,1}, {1,1,1,1,1,1} } 那么返回{1,1,3,2} 坐标为（1,1），长为3，高为2 
follow up 如果 Matrix 中有多个由0组成的长方体，请返回多套值（前提每两个长方体之间是不会连接的，所以放心）

我的想法有点像，rotated sorted array with repeated element那题的二分法吧 每次都找中点看是不是0，如果不是就还是要在左右两部分接着找。4
目的只是找到这个0矩阵的任一点。 虽然最坏情况是O（n），但还是稍微快点。

对follow-up, 如果不要求保持原来的矩阵不变的话，还可以在遍历的时候，每遇到一个0，在找长、宽、顶点的过程中把所有的0都变成2，然后接着遍历，
这样就可以避免判断之后遇到的0是不是属于前面哪一个长方形，这样不增加复杂度和额外的空间。
 */
public class Rectangle {
	/*
	 * 1,1,1,1,1,1
	 * 1,0,0,0,1,1
	 * 1,0,0,0,1,1
	 * 1,1,1,1,1,1
	 * 1,1,1,1,1,1
	 */
	public static void main(String[] args) {
		int[][] matrix = { {1,1,1,1,1,1}, {0,0,0,0,0,0}, {0,0,0,0,0,0}, {1,1,0,1,1,1}, {1,1,1,1,0,0} };
		
		Rectangle rect = new Rectangle();
		List<int[]> res = rect.findMultipe(matrix);
		
		for (int[] val : res) {
			System.out.println(val[0] + "--" + val[1] + "--" + val[2] + "--" + val[3]);
		}
	}
	
	/**
	 * What if there could be multiple 0 regions, update value in matrix is not allowed
	 * Use a visited Set to avoid duplicate visit
	 */
	List<int[]> findMultipeVisited(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<int[]> res = new ArrayList<int[]>();
		Set<Integer> visited = new HashSet<Integer>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int id = i * n + j;
				if (matrix[i][j] == 0 && !visited.contains(id)) {
					visited.add(id);
					int[] point = findPointAndAddVisited(matrix, i, j, visited);
					res.add(point);
				}
			}
		}
		
		return res;
	}
	
	int[] findPointAndAddVisited(int[][] matrix, int row, int col, Set<Integer> visited) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int[] point1 = new int[2]; // Left Top
		int[] point2 = new int[2]; // Right Bottom
		point1[0] = row;
		point1[1] = col;
		point2[0] = row;
		point2[1] = col;
		
		// Go to right-down
		for (int i = row; i < m; i++) { 
			if (i == m - 1 && matrix[i][col] == 0) {
				point2[0] = m - 1;
			} else if (matrix[i][col] != 0) {
				point2[0] = i - 1;
				break;
			}
			
			for (int j = col; j < n; j++) {
				if (j == n - 1 && matrix[i][j] == 0) {
					point2[1] = n - 1;
				} else if (matrix[i][j] != 0) {
					point2[1] = j - 1;
					break; // Go to next row
				}

				visited.add(i * n + j);
			}
		}
		
		return new int[]{point1[0], point1[1], point2[0], point2[1]};
	}
	
	/**
	 * What if there could be multiple 0 regions
	 * If value in matrix is allowed to change, just change the value to avoid duplicate visit
	 */
	List<int[]> findMultipe(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		List<int[]> res = new ArrayList<int[]>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					int[] point = findPointAndChangeValue(matrix, i, j);
					
					res.add(point);
				}
			}
		}
		
		return res;
	}
	
	int[] findPointAndChangeValue(int[][] matrix, int row, int col) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int[] point1 = new int[2]; // Left Top
		int[] point2 = new int[2]; // Right Bottom
		point1[0] = row;
		point1[1] = col;
		point2[0] = row;
		point2[1] = col;
		
		// Go to right-down
		for (int i = row; i < m; i++) { 
			if (i == m - 1 && matrix[i][col] == 0) {
				point2[0] = m - 1;
			} else if (matrix[i][col] != 0) {
				point2[0] = i - 1;
				break;
			}
			
			for (int j = col; j < n; j++) {
				if (j == n - 1 && matrix[i][j] == 0) {
					point2[1] = n - 1;
				} else if (matrix[i][j] != 0) {
					point2[1] = j - 1;
					break; // Go to next row
				}

				// Update value
				matrix[i][j] = 1;
			}
		}
		
		return new int[]{point1[0], point1[1], point2[0], point2[1]};
	}
	
	/**
	 * Only 1 zero-region in matrix
	 * Use binary search to speed up the search
	 */
	int[] findBinarySearch(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int row1 = -1;
		int row2 = -1;
		int col1 = -1;
		int col2 = -1;
		
		for (int i = 0; i < m; i++) {
			int left = 0, right = matrix[i].length - 1;
			
			while (left + 1 < right) {
				int mid = (left + right) / 2;
				
				if (matrix[i][mid] == 0) {
					row1 = i;
					row2 = findBottomRow(matrix, i, mid);
					col1 = findLeftCol(matrix, i, mid);
					col2 = findRightCol(matrix, i, mid);
					
					return new int[]{row1, col1, row2, col2};
				} else {
					if (matrix[i][left] != 0) {
						left++;
					} else {
						right--;
					}
				}
			}
			
			if (matrix[i][left] == 0) {
				row1 = i;
				row2 = findBottomRow(matrix, i, left);
				col1 = findLeftCol(matrix, i, left);
				col2 = findRightCol(matrix, i, left);
				
				return new int[]{row1, col1, row2, col2};
			} else if (matrix[i][right] == 0) {
				row1 = i;
				row2 = findBottomRow(matrix, i, right);
				col1 = findLeftCol(matrix, i, right);
				col2 = findRightCol(matrix, i, right);
				
				return new int[]{row1, col1, row2, col2};
			}
		}
		
		return new int[]{}; // If it exists, it should never come here
	}
	
	int findLeftCol(int[][] matrix, int row, int col) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int left = 0, right = col;
		
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (matrix[row][mid] == 0) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		if (matrix[row][left] == 0) {
			return left;
		} else if (matrix[row][right] == 0) {
			return right;
		}
		
		return -1; // It should never come here
	}
	
	int findRightCol(int[][] matrix, int row, int col) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int left = col, right = n - 1;
		
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (matrix[row][mid] == 0) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (matrix[row][right] == 0) {
			return right;
		} else if (matrix[row][left] == 0) {
			return left;
		}
		
		return -1; // It should never come here
	}
	
	int findBottomRow(int[][] matrix, int row, int col) {
		int m = matrix.length;
		
		while (row < m) {
			if (matrix[row][col] != 0) {
				return row - 1;
			}
			row++;
		}
		
		return m - 1;
	}
	
	/**
	 * Only 1 zero-region in matrix.
	 * Use BFS to search from the left-top corner, and find the left-top point, then spread down and right
	 */
	int[][] directions = {{1, 0}, {0, 1}};
	int[] findTopLeftBFS(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		
		int[] point0 = new int[2];
		Set<Integer> visited = new HashSet<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(0);
		visited.add(0);
		
		while (!queue.isEmpty()) {
			int id = queue.poll();
			int x = id / n;
			int y = id % n;
			
			if (matrix[x][y] == 0) {
				point0[0] = x;
				point0[1] = y;
				break;
			}
			
			for (int[] dir : directions) {
				int x1 = x + dir[0];
				int y1 = y + dir[1];
				int id1 = x1 * n + y1;
				
				if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && !visited.contains(id1)) {
					visited.add(id1);
					queue.offer(id1);
				}
			}
		}
		
		System.out.println(point0[0] + "==" + point0[1]);
		int x0 = point0[0];
		int y0 = point0[1];
		
		while (x0 < m && matrix[x0][point0[1]] == 0) {
			x0++;
		}
		
		while (y0 < n && matrix[point0[0]][y0] == 0) {
			y0++;
		}
		
		// Result is 2 corners' indexes
		return new int[]{point0[0], point0[1], x0 - 1, y0 - 1};
	}

	/**
	 * Only 1 zero-region in mattrix
	 * Use regular 2 for loops. One search from left-top corner to find the first point, while the second for loop searches from the bottom right.
	 */
	int[] find(int[][] matrix) {
		int[] res = new int[4];
		int[] point1 = findPoint1(matrix);
		int[] point2 = findPoint2(matrix);
		
		res[0] = point1[0];
		res[1] = point1[1];
		res[2] = Math.max(point2[1] - point1[1] + 1, point2[0] - point1[0] + 1);
		res[3] = Math.min(point2[1] - point1[1] + 1, point2[0] - point1[0] + 1);
		return res;
	}
	
	int[] findPoint2(int[][] matrix) {
		int[] point2 = new int[2];
		for (int i = matrix.length - 1; i >= 0; i--) {
			for (int j = matrix[i].length - 1; j >= 0; j--) {
				if (matrix[i][j] == 0) {
					point2[0] = i;
					point2[1] = j;
					return point2;
				}
			}
		}
		return point2; // Empty
	}
	
	int[] findPoint1(int[][] matrix) {
		int[] point1 = new int[2];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					point1[0] = i;
					point1[1] = j;
					return point1;
				}
			}
		}
		return point1;
	}
}
