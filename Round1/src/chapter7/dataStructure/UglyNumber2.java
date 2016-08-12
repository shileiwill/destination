package chapter7.dataStructure;
/**
 * 264. Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number.

Hint:

The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> heap = new PriorityQueue<Long>(); // Always get the smallest
        List<Long> visited = new ArrayList<Long>(); // Avoid duplication
        
        // Always, the 3 together
        long[] primes = {Long.valueOf(2), Long.valueOf(3), Long.valueOf(5)};
        for (int i = 0; i < 3; i++) {
            heap.offer(primes[i]);
            visited.add(primes[i]);
        }
        
        long number = Long.valueOf(1);
        for (int i = 1; i < n; i++) { // nth
            number = heap.poll(); // 第一次poll出来的是2，这是为啥i < n 而不是i <= n
            
            for (int j = 0; j < 3; j++) { // 一放就放仨
                long val = number * primes[j];
                if (!visited.contains(val)) {
                    heap.offer(val);
                    visited.add(val);
                }
            }
        }
        
        return (int)number;
    }
    public int nthUglyNumberNotWork(int n) {
        if (n == 1) {
            return 1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        heap.offer(1);
        
        int count = 1;
        
        while (count < n) {
            int min = heap.poll();
            // 这样不行，因为不能保证是按顺序的 2*2， 1*5
            if (count + 1 <= n) {
                heap.offer(2 * min);
                count++;
                
                if (count == n) {
                    return 2 * min;
                }
            }
            if (count + 1 <= n) {
                heap.offer(3 * min);
                count++;
                
                if (count == n) {
                    return 3 * min;
                }
            }
            if (count + 1 <= n) {
                heap.offer(5 * min);
                count++;
                
                if (count == n) {
                    return 5 * min;
                }
            }
        }
        
        return heap.poll();
    }
    public int nthUglyNumber2(int n) {
        int count = 0;
        int res = 1;
        
        while (count < n) {
            if (isUgly(res)) {
                count++;
            }  
            res++;
        }
        
        return res - 1;
    }
    
    public boolean isUgly(int num) {
        
            while (num >= 5 && num % 5 == 0) {
                num = num / 5;
            }
            while (num >= 3 && num % 3 == 0) {
                num = num / 3;
            }
            while (num >= 2 && num % 2 == 0) {
                num = num / 2;
            }
            
        
        return num == 1;
        
    }
}
