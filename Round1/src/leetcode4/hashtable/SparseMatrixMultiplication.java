package leetcode4.hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 */
public class SparseMatrixMultiplication {
	
    public int[][] multiplyTwoLookUpTablesBruteForce(int[][] A, int[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("A's column number must match B's row number");
        }
        
        int common = A[0].length;
        int row = A.length, col = B[0].length;
        int[][] res = new int[row][col];
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int k = 0, sum = 0;
                while (k < common) {
                    sum += (A[i][k] * B[k][j]);
                    k++;
                }
                res[i][j] = sum;                
            }
        }
        
        return res;
    }
    
    // Better brute force
    public int[][] multiply2(int[][] A, int[][] B) {
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
    
    // Good
    public int[][] multiplyTwoLookUpTables(int[][] A, int[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("A's column number must match B's row number");
        }
        
        Map<Integer, Map<Integer, Integer>> mapA = new HashMap<Integer, Map<Integer, Integer>>();
        Map<Integer, Map<Integer, Integer>> mapB = new HashMap<Integer, Map<Integer, Integer>>();
        int[][] res = new int[A.length][B[0].length]; // A's row count, B's col count
        
        for (int i = 0; i < A.length; i++) { // Row
            for (int j = 0; j < A[i].length; j++) { // Col
                if (A[i][j] == 0) {
                    continue;
                }
                if (!mapA.containsKey(i)) {
                    mapA.put(i, new HashMap<Integer, Integer>());
                }
                mapA.get(i).put(j, A[i][j]);
            }
        }
        
        for (int i = 0; i < B.length; i++) { // Row
            for (int j = 0; j < B[i].length; j++) { // Col
                if (B[i][j] == 0) {
                    continue;
                }
                if (!mapB.containsKey(i)) {
                    mapB.put(i, new HashMap<Integer, Integer>());
                }
                mapB.get(i).put(j, B[i][j]);
            }
        }
        
        for (int i : mapA.keySet()) { // A's Row
            for (int j : mapA.get(i).keySet()) { // A's Col
                // Table A 中所有不为0的数
                if (!mapB.containsKey(j)) {
                    continue;
                }
                for (int k : mapB.get(j).keySet()) { // B's jth row
                    res[i][k] += mapA.get(i).get(j) * mapB.get(j).get(k);
                }
            }
        }
        
        return res;
    }
    
    // A sparse matrix can be represented as a sequence of rows, each of which is a sequence of 
    //(column-number, value) pairs of the nonzero values in the row.
    // So let's create a non-zero array for A, and do multiplication on B.
    public int[][] multiply(int[][] A, int[][] B) {
        int rowA = A.length, colARowB = A[0].length, colB = B[0].length;
        List[] arr = new List[rowA];
        int[][] res = new int[rowA][colB];
        
        for (int i = 0; i < rowA; i++) {
            for (int j = 0; j < colARowB; j++) {
                if (A[i][j] == 0) {
                    continue;
                }
                if (arr[i] == null) {
                    arr[i] = new ArrayList<Integer>();
                }
                arr[i].add(j);
                arr[i].add(A[i][j]);
            }
        }
        
        for (int i = 0; i < rowA; i++) { // i is the row in A
            List<Integer> aRowA = arr[i];
            
            if (aRowA == null) { // Means all values in this row are 0
                continue;
            }
            
            for (int j = 0; j < aRowA.size() - 1; j += 2) {
                int colInA = aRowA.get(j); // column in row i in A. This will also be the row in B
                int value = aRowA.get(j + 1);
                
                for (int k = 0; k < colB; k++) {
                    if (B[colInA][k] == 0) {
                        continue;
                    }
                    res[i][k] += A[i][colInA] * B[colInA][k];
                }
            }
        }
        
        return res;
    }
}