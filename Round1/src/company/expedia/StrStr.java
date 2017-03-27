package company.expedia;

public class StrStr {

	public static void main(String[] args) {
		StrStr ss = new StrStr();
		String source = "mississippi";
		String pattern = "issip";
		int res = ss.strStrKMP(source, pattern);
		
		System.out.println(res);
	}

	int strStr(String source, String pattern) {
		char[] arr1 = source.toCharArray();
		char[] arr2 = pattern.toCharArray();
		
		int p1 = 0, p2 = 0;
		int index = 0;
		
		while (p1 < arr1.length && p2 < arr2.length) {
			char c1 = arr1[p1];
			char c2 = arr2[p2];
			
			if (c1 == c2) {
				p1++;
				p2++;
			} else {
				p2 = 0;
				index++;
				p1 = index;
			}
		}
		
		return p2 == arr2.length ? index : -1;
	}
	
	int strStrKMP(String source, String pattern) {
		char[] arr1 = source.toCharArray();
		char[] arr2 = pattern.toCharArray();
		
		int[] helper = getKMP(arr2);
		int p1 = 0, p2 = 0;
		
		while (p1 < arr1.length && p2 < arr2.length) {
			char c1 = arr1[p1];
			char c2 = arr2[p2];
			
			if (c1 == c2) {
				p1++;
				p2++;
			} else {
				if (p2 != 0) {
					p2 = helper[p2 - 1];
				} else {
					p1++;
				}
			}
		}
		
		return p2 == arr2.length ? p1 - p2 : -1;
	}
	
	int[] getKMP(char[] arr) {
		int[] res = new int[arr.length];
		
		int index = 0;
		for (int i = 1; i < arr.length;) {
			if (arr[i] == arr[index]) {
				res[i] = index + 1;
				index++;
				i++;
			} else {
				if (index != 0) {
					index = res[index - 1];
				} else {
					res[i] = 0;
//					index = 0;// No need to do this, already 0
					i++;
				}
			}
		}
		
		return res;
	}
}
