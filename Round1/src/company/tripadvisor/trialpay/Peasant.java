package company.tripadvisor.trialpay;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * peasant's algorithmn, 就是给你两个数，每次左边的数除以2 右边乘以2，求右边的数之和（如果左边的数是2的倍数则这次不加）。 
 * 于是Lz就写了一个while循环里边带if，小哥说可以了，然后follow up是怎么把它搞快点，于是lz说位运算？小哥说对头，然后就>>1 和<<1了
 */
public class Peasant {

	public static void main(String[] args) {
		Peasant p = new Peasant();
		Integer[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
		Integer[] arr2 = {3, 5, 6, 8};
		List<Integer> all = Arrays.asList(arr1);
		List<Integer> part = Arrays.asList(arr2);
		
		p.findMissingPersons(all, part);
	}

	/**
	 * 第三题是给你两个表里面是名字，左边是应该来开会的所有人的名字，右边是实际来开会的人的名字，让你找出没有来开会的人。
	 * lz说HashMap呗然后写了一遍，问一下时间空间复杂度，问我能优化不？我说老哥这好像不可以优化了。
	 */
	Set<Integer> findMissingPersons(List<Integer> all, List<Integer> part) {
		Set<Integer> res = new HashSet<Integer>();
		int pos1 = 0, pos2 = 0;
		
		while (pos1 != all.size()) {
			int val1 = all.get(pos1);
			
			if (pos2 == part.size()) {
				res.add(val1);
				pos1++;
			} else {
				int val2 = part.get(pos2);
				if (val1 == val2) { // Attended
					pos1++;
					pos2++;
				} else {
					res.add(all.get(pos1));
					pos1++;
				}
			}
		}
		
		for (int val : res) {
			System.out.print(val + "--");
		}
		
		return res;
	}
	
	void peasant() {
		int val1 = 44;
		int val2 = 23;
		int res = 0;
		
		while (val1 != 0) {
			if (val1 % 2 != 0) {
				res += val2;
			}
			
			val1 >>= 1; // divide
			val2 <<= 1;
		}
		
		System.out.println(res);
	}
}
