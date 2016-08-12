package chapter7.dataStructure;

/**
 * 263. Write a program to check whether a given number is an ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another
 * prime factor 7.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * @author Lulu
 *
 */
public class UglyNumber {

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

	// Here is my solution, not good but works
	public boolean isUgly2(int num) {

		while (num > 1) {
			int cur = num;
			while (cur > 1 && cur % 5 == 0) {
				cur = cur / 5;
			}
			while (cur > 1 && cur % 3 == 0) {
				cur = cur / 3;
			}
			while (cur > 1 && cur % 2 == 0) {
				cur = cur / 2;
			}

			if (cur == num) {
				return false;
			} else {
				num = cur;
			}
		}

		return num == 1;

	}
}
