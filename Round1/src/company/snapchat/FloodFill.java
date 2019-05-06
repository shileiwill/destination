package company.snapchat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * https://leetcode.com/problems/flood-fill/
 * 
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
 * @author lei2017
 *
 */

// BFS, DFS
class FloodFill {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    // BFS fails 1 test case
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        
        int m = image.length;
        int n = image[0].length;
        int target = image[sr][sc];
        int targetId = sr * n + sc;
        
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        queue.offer(targetId);
        visited.add(targetId); // Since we are changing the color on the fly, we dont need the visited
        image[sr][sc] = newColor; //变色！
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for(int i = 0; i < size; i++) {
                int nowId = queue.poll();
                int x = nowId / m;
                int y = nowId % m;
                
                for(int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    int newId = newX * m + newY;
                    
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && image[newX][newY] == target && !visited.contains(newId)) {   
                        queue.offer(newId);
                        visited.add(newId);
                        image[newX][newY] = newColor;
                    }
                }
            }
        }
        
        return image;
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] == newColor) {
            return image;
        }
        dfs(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    void dfs(int[][] image, int i, int j, int target, int newColor) {
    	// Validity check must be here
        if(i < 0 || i >= image.length || j < 0 || j >= image[0].length || image[i][j] != target) {
            return;
        }
        
        image[i][j] = newColor; // 一切合格，变色，之后继续找四周
        dfs(image, i + 1, j, target, newColor);
        dfs(image, i - 1, j, target, newColor);
        dfs(image, i, j + 1, target, newColor);
        dfs(image, i, j - 1, target, newColor);
    }
}