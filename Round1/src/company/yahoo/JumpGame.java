package company.yahoo;

public class JumpGame {
	public static void main(String[] args) {
		JumpGame jg = new JumpGame();
		int res1 = jg.jump(5);
		int res2 = jg.dp(5);
		System.out.println(res1 + ":" + res2);
	}
	
	int jump(int level) {
		if (level == 0 || level == 1) {
			return 1;
		}
		
		return jump(level - 1) + jump(level - 2);
	}
	
	int dp(int level) {
		int[] hash = new int[level + 1];
		
		hash[0] = hash[1] = 1;
		
		for (int i = 2; i <= level; i++) {
			hash[i] = hash[i - 1] + hash[i - 2];
		}
		
		return hash[level];
	}
}
