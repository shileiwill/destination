package leetcode20.math;
/**
 * 372. Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024
 */
public class SuperPow {

    // http://www.cnblogs.com/grandyang/p/5651982.html
    //  ab % k = (a%k)(b%k)%k
    // a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
    public int superPow(int a, int[] b) {
        int res = 1;
        
        for (int i = 0; i < b.length; i++) {
            res = (pow(res, 10) * pow(a, b[i])) % 1337;
        }
        
        return res;
    }
    
    int pow(int x, int n) { // x ^ n
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x % 1337;
        }
        
        return ((pow(x % 1337, n / 2)) * (pow(x % 1337, n - n / 2))) % 1337;
    }

}
