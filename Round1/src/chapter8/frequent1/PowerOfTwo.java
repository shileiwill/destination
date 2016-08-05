package chapter8.frequent1;

public class PowerOfTwo {

	public static void main(String[] args) {
		PowerOfTwo p2 = new PowerOfTwo();
		p2.isPowerOfTwo(2);
	}
	
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) {
            return false;
        }
        // 2的次幂的特点是只有一位是1，别的全是0。所以我们去掉最后一个1，看看结果是不是0
        return ((n - 1) & n) == 0;
    }
    
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        
        double res = n;
        while (res > 1) {
            res = res / 2.0;
        }
        return res == 1;
    }

}
