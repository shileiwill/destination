package company.linkedin;

import java.util.ArrayList;
import java.util.List;

/**
 * 第二道是新题，给multidimensional array，给一个function， 输入这个array以及各个dimension上的index，可以output这个位置上的数字。
写一个function，input是multidimensional array，以及array的dimensions，只能调用上面给的那个function，输出这个array里面所有的数字的和。
题不难，是我当时脑子懵了，一直在想怎么找这个array的各个dimension上的boundary，其实input就给了。和面试官一直在交流，但我没说好，十几分钟一直在纠结这个问题。
后来面试官举了个例子，立刻反应过来了。但也没有什么时间，就草草的说了下pseudo code，用dfs做所有dimension上的不同index的combination，然后调用那个function求和。

Given a mutlidimensional arrays, compute the sum of all values. Given API getValue(dn, dn-1.... d0) dn = index at dimension.
 */
public class MultiDimentionalArray {

	public static void main(String[] args) {

	}

	/**
	 * 
	 * @param mArray 多维数组
	 * @param dim 每个dim的长度
	 * @return
	 */
	public int sum(MultiDimArray mArray, int[] dim) {
		List<Integer> list = new ArrayList<Integer>(); // List是存的所有可能的下标组合， combination
		return helper(mArray, dim, 0, list);
	}
	
	int helper(MultiDimArray mArray, int[] dim, int dimIndex, List<Integer> list) {
		if (dim.length == dimIndex) { // Done with all dimentions, 这是一串下标，唯一决定一个value
			return mArray.get(list); // Get the value by indices
		}
		
		int sum = 0;
		for (int i = 0; i < dim[dimIndex]; i++) { // 一个维的所有下标
			list.add(i);
			sum += helper(mArray, dim, dimIndex + 1, list); // 下一维
			list.remove(list.size() - 1);
		}
		
		return sum;
	}
}
