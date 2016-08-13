package chapter8.frequent;
/**
 * Calculate the an % b where a, b and n are all 32bit integers.

Have you met this question in a real interview? Yes
Example
For 231 % 3 = 2

For 1001000 % 1000 = 0
 * @author Lei
 *
 */
public class FastPower {
    public int fastPower2(int a, int b, int n) {
        int num = 1;
        for (int i = 0; i < n; i++) {
            num = num * a;
        }
        
        return num % b;
    }
    
    // a的n次方等于a的n/2次方的2次方，这样复杂度从n变成了lgn
    public int fastPower(int a, int b, int n) {
        if (n == 0) {
            return 1 % b;
        }
        if (n == 1) {
            return a % b;
        }
        // 注意用long 防止溢出
        long product = fastPower(a, b, n / 2);
        // 时刻取模， 利用%的特点，多%两次不改变结果，而且可以防止溢出
        long res = (product * product) % b;
        
        if (n % 2 == 1) { //Odd
            return (int)(res * a % b); // 多%一次没关系
        } else {
            return (int)(res);
        }
    }
}
