package leetcode8.dynamicprogramming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 279. Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {
    // Start from node 0 in queue, and keep pushing in perfect square number + curr value, once we reach number n, we found the solution.
    public int numSquaresBFS(int n) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        queue.offer(0);
        int depth = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                
                for (int j = 1; j * j <= n; j++) {
                    int newVal = cur + j * j;
                    
                    if (newVal == n) {
                        return depth;
                    }
                    
                    if (newVal > n) {
                        break;
                    }
                    
                    if (!visited.contains(newVal)) {
                        visited.add(newVal);
                        queue.offer(newVal);
                    }
                }
            }
        }
        
        return depth;
    }
    
    public int numSquares(int n) {
        int[] hash = new int[n + 1];
        Arrays.fill(hash, Integer.MAX_VALUE);
        
        for (int i = 1; i * i <= n; i++) {
            hash[i * i] = 1;
        }
        
        for (int a = 1; a <= n; a++) {
            for (int b = 1; a + b * b <= n; b++) {
                hash[a + b * b] = Math.min(hash[a] + 1, hash[a + b * b]);
            }
        }
        
        return hash[n];
    }
}
