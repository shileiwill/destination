package company.snapchat;

import java.util.Arrays;

/**
 * 240. Count the number of prime numbers less than a non-negative number, n.
 */
public class CountPrimes {
    // Sieve of Eratosthenes 
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        
        for (int i = 2; i * i <= n; i++) {
            if (!isPrime[i]) { // Continue if itself is not prime
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) { // Must go through all
            if (isPrime[i]) {
                count++;
            }
        }
        
        return count;
    }
    
    public int countPrimes2(int n) {
        int count = 0;
        
        for (int i = 1; i < n; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        
        return count;
    }
    
    boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        
        if (n % 2 == 0) {
            return false;
        }
        
        // Loop's ending condition is i * i <= num instead of i <= sqrt(num)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;        
            }
        }
        
        return true;
    }
}
