package company.yahoo;

import java.util.Scanner;
// http://www.javawithus.com/programs/towers-of-hanoi
public class HanoiTower {
	
	// Bruce
	void move(int n, String from, String to, String by) {
		if (n == 2) {
			System.out.println(String.format("Move %d from %s to %s", 1, from, by));
			System.out.println(String.format("Move %d from %s to %s", 2, from, to));
			System.out.println(String.format("Move %d from %s to %s", 1, by, to));
			return;
		}
		
		move(n - 1, from, by, to);
		System.out.println(String.format("Move %d from %s to %s", n, from, to));
		move(n - 1, by, to, from);
	}

	int kthFib(int[] sum, int[] pos) {
		if (pos[0] == 0) {
			return sum[0];
		}

		if (pos[0] == 0 || pos[0] == 1) {
			return 1;
		}
		
		pos[0] -= 1;
		int val1 = kthFib(sum, pos);
		
		pos[0] -= 1;
		int val2 = kthFib(sum, pos);
		
		sum[0] = val1 + val2;
		return sum[0];
	}
		   
	public static void main(String[] args) {
		HanoiTower ht = new HanoiTower();
		int res = ht.kthFib(0, new int[]{3});
		System.out.println(res);
	}
}
