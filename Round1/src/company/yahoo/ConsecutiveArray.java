package company.yahoo;
/**
 * 判断一个都是正整数的array有没有可能变成一个连续的array. 这题我先从sort开始，然后写完说，如果数据范围在特定范围内的话，
 * 按照类似bucket sort的方式，可以做到O(n), 咖喱表示满意，最后问我，能不能不用额外的space(input不算), 
 * 我说那要改变input，用input来做flag了，但是可能会有点麻烦. 咖喱说直接把正数变负数就可以了，写完代码，pass
 */
public class ConsecutiveArray {

	public static void main(String[] args) {
		ConsecutiveArray ca = new ConsecutiveArray();
		int[] arr = {3, 1, 0, 4, 1};
		boolean res = ca.isConsecutiveNoExtraSpace(arr);
		System.out.println(res);
		
		String str = "aabbbcdd";
		
		int prev = 0;
		int cur = 1;
		
		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(prev));
		
		while (cur < str.length()) {
			if (str.charAt(cur) != str.charAt(prev)) {
				sb.append(str.charAt(cur));
			}
			prev++;
			cur++;
		}
		System.out.println(sb.toString());
	}

	boolean isConsecutive(int[] arr) {
		int[] bucket = new int[arr.length];
		
		for (int val : arr) {
			bucket[val]++;
			
			if (bucket[val] > 1) {
				return false;
			}
		}
		
		return true;
	}
	
	boolean isConsecutiveNoExtraSpace(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int now = Math.abs(arr[i]);
			
			if (arr[now] < 0) {
				return false;
			}
			
			arr[now] = -arr[now];
		}
		
		return true;
	}
}
