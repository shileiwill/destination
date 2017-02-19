package amazon;

import java.util.Stack;
/**
 * 给一个数组,代表一组人的身高。然后输出一个数组,表示在当前人之后的所有 比他高的人里,离他最近的人的身高。
 * 比如输入是[3, 6, 7, 2, 3] 输出就是[6, 7, null, 3, null]。 我给出了俩解,都是O(n2)的。她希望得到一个O(n)的解
 * 
 * 第三题类似那个Leetcode里面的histgram吧

用一个stack记录从右到左的身高 且保持身高递减的 比如例子里面[3,6,7,2,3], 从右往左扫:

3, 因为是最右, 肯定是NULL, 然后把 3放到stack里; 2, 发现stack的top比2大, 于是就是3, 然后把2也放进stack里;
7, 发现2比7小, pop, 发现3 比7小, pop, stack空了 所以是NULL, 然后把7放进stack里;
6, 发现top=7>6, 所以就是7, 然后把6放进stack里;
3, 发现top=6>3, 所以就是3, 然后把3放进stack里;

时间空间复杂度都是O(n)
 */
public class Height {

	public static void main(String[] args) {
//		int[] arr = {3, 6, 7, 2, 3};
		int[] arr = {9,1,2,8,6,2,1,3,6,7,2,3};
		int[] res = nextHigher(arr);
		
		for (int val : res) {
			System.out.print(val + "==");
		}
	}
	
	static int[] nextHigher(int[] arr) {
		Stack<Integer> stack = new Stack<Integer>();
		int[] res = new int[arr.length];
		
		for (int i = arr.length - 1; i >= 0; i--) {
			if (stack.isEmpty()) { // 最右边的人
				stack.push(arr[i]);
				res[i] = -1;
			} else if (arr[i] < stack.peek()) {// 立马找到比自己高的
				res[i] = stack.peek();
				stack.push(arr[i]);
			} else { // 这个太高了
				while (!stack.isEmpty()) {
					if (stack.peek() > arr[i]) {
						res[i] = stack.peek();
//						stack.push(arr[i]);
						break;
					}
					stack.pop();
				}
				if (stack.isEmpty()) {
					res[i] = -1;
				}
				stack.push(arr[i]);
			}
		}
		
		return res;
	}
}
