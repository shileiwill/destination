package linkedin;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		int res = getGreatestCommonDivisor(6, 11);
		System.out.println(res);
	}
	
    private static int getGreatestCommonDivisor(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return getGreatestCommonDivisor(b, a % b);
        }
    }

}
