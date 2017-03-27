package company.jet.com;

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class InterviewQuestions {
  public static void main(String[] args) {
   int[][] imageA = {
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 0},
    };
    
    int[][] imageB = {  
      {0, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 0, 0, 0, 1},
      {1, 0, 1, 0, 0, 0, 1},
      {1, 0, 1, 1, 1, 1, 1},
      {1, 0, 1, 0, 0, 1, 1},
      {1, 1, 1, 0, 0, 1, 1},
      {1, 1, 1, 1, 1, 1, 0},
    };

    int[][] image = {
      {0, 1, 1, 1, 1, 1, 1},
      {1, 1, 1, 1, 0, 1, 1},
      {1, 1, 1, 0, 0, 0, 1},
      {1, 0, 1, 1, 0, 1, 1},
      {1, 0, 1, 1, 1, 1, 1},
      {1, 0, 0, 0, 0, 1, 1},
      {1, 1, 1, 0, 0, 1, 1},
      {1, 1, 1, 1, 1, 1, 0},
    };
    
    

    InterviewQuestions sol = new InterviewQuestions();
    sol.findZeros(image);
    
    //System.out.println("The 4 points : " + res[0] + " : " + res[1] + " : " + res[2] + " : " + res[3]);
  }
  
  int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
  // Find the shapes, L T
  void findZeros(int[][] image) {
        Set<Integer> visited = new HashSet<Integer>();
        int m = image.length;
        int n = image[0].length;
    
        for (int i = 0; i < m; i++) {
          for (int j = 0; j < n; j++) {
              int id = i * n + j;
              if (image[i][j] == 0 && !visited.contains(id)) {
                System.out.print("Here is one zero area: " + i + ":" + j);
                visited.add(id);
                dfs(image, i, j, visited);
              }
            
              System.out.println();
          }
        }
  }
  
  void dfs(int[][] image, int i, int j, Set<Integer> visited) {
    // All directions
    for (int[] dir : directions) {
      int x = i + dir[0];
      int y = j + dir[1];
      int id = x * image[0].length + y;
      
      
      if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && !visited.contains(id) && image[x][y] == 0) {
          System.out.print(" " + x + ":" + y + " ");
          visited.add(id);
          dfs(image, x, y, visited);
      }
    }
  }
  
  // Time O(m * n) Space O(m * n)
  void findMultipleRectangle(int[][] image) {
    Set<Integer> visited = new HashSet<Integer>();
    int m = image.length;
    int n = image[0].length;
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        // Find one
        if (image[i][j] == 0 && !visited.contains(i * n + j)) {
          int[] point = search(image, i, j, visited);
          System.out.println(point[0] + " : " + point[1] + " : " + point[2] + " : " + point[3]);
        }
      }
    }
  }
  
  int[] search(int[][] image, int i, int j, Set<Integer> visited) {
    int m = image.length;
    int n = image[0].length;
    int row1 = i;
    int col1 = j;
    int row2 = i;
    int col2 = j;
    
    for (int x = i; x < m; x++) {
      if (image[x][j] != 0) {
        row2 = x - 1;
        break;
      } else if (x == m - 1) {
        row2 = m - 1;
      }
      
      for (int y = j; y < n; y++) {
        if (image[i][y] != 0) {
          col2 = y - 1;
          break;
        } else if (y == n - 1) {
          col2 = n - 1;
        }
        
        int id = x * n + y;
        visited.add(id);
      }
    }
    
    return new int[]{row1, col1, row2, col2};
  }
  
  int[] findRectangle(int[][] image) {
    // Find left top point  
    int[] point1 = findPoint1(image);
    // Find right bottom point
    int[] point2 = findPoint2(image);
    
    // The 2 points together as result
    return new int[]{point1[0], point1[1], point2[0], point2[1]};
  }
  
  int[] findPoint1(int[][] image) {
    int m = image.length;
    int n = image[0].length;
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (image[i][j] == 0) {
          return new int[]{i, j};
        }
      }
    }
    
    // Should never come here if 1 rectangle is guaranteed
    return null;
  }
  
  int[] findPoint2(int[][] image) {
    int m = image.length;
    int n = image[0].length;
    
    for (int i = m - 1; i >= 0; i--) {
      for (int j = n - 1; j >= 0; j--) {
        if (image[i][j] == 0) {
          return new int[]{i, j};
        }
      }
    }
    
    // Should never come here if 1 rectangle is guaranteed
    return null;
  }
}


/* 
Your previous Markdown content is preserved below:

Welcome to the Interview!

Imagine we have an image. We’ll represent this image as a simple 2D array where every pixel is a 1 or a 0. The image you get is known to have a single rectangle of 0s on a background of 1s. Write a function that takes in the image and returns the coordinates of the rectangle -- either top-left and bottom-right; or top-left, width, and height.


// Java


 */