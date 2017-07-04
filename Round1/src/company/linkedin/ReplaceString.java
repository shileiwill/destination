package company.linkedin;

import java.util.Arrays;

public class ReplaceString {
	
		public static void main(String[] args) {
			ReplaceString rs = new ReplaceString();
			String res = rs.replaceWithJavaAPI("heabcdefabcgh", "ab", "abc");
			System.out.println(res);
		}
		
		String replaceWithJavaAPI(String original, String from, String to) {
			int index = 0;
			while (original.indexOf(from, index) != -1) {
				int left = original.indexOf(from, index);
				String newString = original.substring(0, left) + to + original.substring(left + from.length());
				original = newString;
				index = left + to.length();
			}
			
			return original;
		}
		
		// 4. 字符串替换就是给你一个原字符串s，一个目标串p，一个替换字符串t，把s里面所有的p替换成t，比如给s="abcdefg", p="bc",
		// t="x"，返回"axdefg"
		// 面试官期望的是O(n)的解法，比如用一个sliding window记录当前窗口每个字符出现频率，只有出现频率符合目标串p里面字符频率才进行字符串比较，我说如果遇见"aaaaaa", 
		// 目标串为"aa",这种情况是会退化成O(n^2)的，面试官说是的，但是平均来说是O(n)
		String replaceString(String original, String from, String to) {
			if (original.length() < from.length() || from.equals(to)) {
				return original;
			}

			int[] count = new int[26];
			for (char c : from.toCharArray()) {
				count[c - 'a']++;
			}

			int[] curCount = new int[26]; // 记录个数
			int left = 0, right = 0;
			while (right < original.length()) {
				if (right - left < from.length()) {
					curCount[original.charAt(right) - 'a']++;
				} else {
					boolean isSame = true;
					for (int i = 0; i < 26; i++) {
						if (count[i] != curCount[i]) {
							isSame = false;
						}
					}

					if (isSame && original.substring(left, right).equals(from)) { // 比较顺序
						original = original.substring(0, left) + to + original.substring(right);
						Arrays.fill(curCount, 0);
						left = left + to.length(); // Move
						right = left;
						continue;
					}

					curCount[original.charAt(left) - 'a']--;
					left++;
					curCount[original.charAt(right) - 'a']++;
				}

				right++;
			}

			return original;
		}
		
		int[] getKMP(char[] pattern) {
			int[] arr = new int[pattern.length];
			
			int left = 0, right = 1;
			while (right < arr.length) {
				if (pattern[left] == pattern[right]) {
					arr[right] = left + 1;
					left++;
					right++;
				} else {
					if (left == 0) {
						arr[right] = 0;
						right++;
					} else {
						left = arr[left - 1];
					}
				}
			}
			
			return arr;
		}
		
		int strstr(char[] source, char[] pattern) {
			int[] kmp = getKMP(pattern);
			
			int pos1 = 0;
			int pos2 = 0;
			while (pos1 < source.length && pos2 < pattern.length) {
				char c1 = source[pos1];
				char c2 = pattern[pos2];
				
				if (c1 == c2) {
					pos1++;
					pos2++;
				} else {
					if (pos2 == 0) {
						pos1++;
					} else {
						pos2 = kmp[pos2 - 1];
					}
				}
			}
			
			if (pos2 == pattern.length) {
				return pos1 - pos2;
			} else {
				return -1;
			}
		}
}
