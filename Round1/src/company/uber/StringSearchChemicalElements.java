package company.uber;

import java.util.HashSet;
import java.util.Set;

/**
 * input: firstname lastname 
 * output:返回每个名字里第一个化学元素 比如锂（Li）钠(Na)... 
 * 
 * 我用了hashset存元素 然后暴力查找，因为我觉得每个名字也不长.... 
 * follow up:优先级 2-letters element > 1-letter element 
 * 跪在一开始掉入了忘了元素有2位字母的名字的坑中，用char写了半天 后来突然想起特么中学化学元素表里有Li Na Ba这种名字，浪费了大量时间
 * 
 * Either with length 1 or length 2
 */
public class StringSearchChemicalElements {

	public static void main(String[] args) {
		StringSearchChemicalElements ss = new StringSearchChemicalElements();
		
		Set<String> set = new HashSet<String>();
		set.add("li");
		set.add("na");
		set.add("k");
		set.add("n");
		
		String fullName = "helnli jacknam";
		String[] res = ss.find(fullName, set);
		for (String s : res) {
			System.out.println(s);
		}
	}

	String[] find(String fullName, Set<String> set) {
		String[] names = fullName.split("\\s+");
		
		String[] res = new String[2];
		res[0] = search(names[0], set);
		res[1] = search(names[1], set);
		
		return res;
	}

	private String search(String S, Set<String> set) {
		String potentialS = null;
		for (int i = 0; i < S.length() - 1; i++) {
			String s1 = S.substring(i, i + 1);
			String s2 = S.substring(i, i + 2);
			
			if (set.contains(s2)) {
				return s2;
			} else if (set.contains(s1)) {
				potentialS = s1;
				//return s1;
			}
		}
		return potentialS;
	}
}
