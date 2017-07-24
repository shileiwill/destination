package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * input: a very long string as a message to send to user, the string length limit of a single message 
 * output: array of messages formatted as ['(1/10) message 1', '(2/10) message 2', ...] 
 */
public class StringOperation {

	public static void main(String[] args) {
//		print("averylongstringasamessagetosendtouser", 5);
		StringOperation so = new StringOperation();
		//so.evaluate("2y+4+ 3y+4 = 5y-6y+2y +1", 7);
		
		//System.out.println(so.canForm("hello", "howareyloul"));
		
		String[] arr1 = {"hello", "ak", "ow"};
		List<String> list1 = Arrays.asList(arr1);
		
		Character[] arr2 = {'h', 'o', 'w', 'a', 'r', 'e', 'y', 'l', 'o', 'u', 'l'};
		List<Character> list2 = Arrays.asList(arr2);
		
		System.out.println(so.canFormStream(list1, list2.iterator()));
	}

	static void print(String S, int N) {
		List<String> list = new ArrayList<String>();
		
		for (int i = 0; i < S.length(); i += N) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < N && i + j < S.length(); j++) {
				sb.append(S.charAt(i + j));
			}
			list.add(sb.toString());
		}
		
		int size = list.size();
		System.out.print("[");
		for (int i = 0; i < list.size(); i++) {
			System.out.print("'(" + (i + 1) + "/" + size + ") " + list.get(i) + "'");
			if (i < list.size() - 1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}

	// “2x+3y+4 = 5x-6y+1” 给一个equation, 保证是合法的， 但是系数可能是小数，并且有空格。再给一个x值，求y值
	void evaluate(String S, int x) {
		String[] arr = S.split("=");
		arr[0] = arr[0].trim();
		arr[1] = arr[1].trim();
		
		if (arr[0].indexOf("y") == - 1 && arr[1].indexOf("y") == - 1) {
			return; // N/A
		}
		
		double[] left = parse(arr[0]);
		double[] right = parse(arr[1]);
		
		double a = left[0] - right[0];
		double b = left[1] - right[1];
		double c = left[2] - right[2];
		
		double res = -(a * x + c) / b;
		System.out.println(res);
	}
	
	// 利用indexOf进行切分
	double[] parse2(String S) {
		double[] res = new double[3];
		
		int xIndex = S.indexOf("x");
		StringBuilder xSB = new StringBuilder();
		while (xIndex >= 0) {
			char c = S.charAt(xIndex);
			if (Character.isDigit(c)) {
				xSB.insert(0, c);
				xIndex--;
			} else if (c == ' ') {
				xIndex--;
			} else {
				break;
			}
		}
		
		S = S.substring(0, xIndex) + S.substring(S.indexOf("x") + 1).trim();
		
		return res;
	}
	
	double[] parse(String S) {
		double[] res = new double[3];
		int pos = 0;
		StringBuilder sb = new StringBuilder();
		int flag = 1;
		
		while (pos < S.length()) {
			if (S.charAt(pos) == '+') {
				flag = 1;
				pos++;
			} else if (S.charAt(pos) == '-') {
				flag = -1;
				pos++;
			}
			
			if (S.charAt(pos) == ' ') {
				pos++;
				continue;
			}
			
			while (pos < S.length() && (S.charAt(pos) != 'x' && S.charAt(pos) != 'y') && (S.charAt(pos) != '+' && S.charAt(pos) != '-') && S.charAt(pos) != ' ') {
				sb.append(S.charAt(pos));
				pos++;
			}
			
			System.out.println(sb.toString());
			Double val = Double.parseDouble(sb.toString());
			if (pos == S.length()) { // Come to the end, and it is a single digit
				res[2] += (flag * val);
			} else if (S.charAt(pos) == 'x' || S.charAt(pos) == 'y') {
				if (S.charAt(pos) == 'x') {
					res[0] += (flag * val);
				} else {
					res[1] += (flag * val);
				}
				pos++;
			} else if (S.charAt(pos) == '+' || S.charAt(pos) == '-') { // + or -
				res[2] += (flag * val);
			} else { // Space
				//pos++; // Actually, no need to do it here since we will do at the beginning always. But need to check in while loop
			}
			
			sb = new StringBuilder();
		}
		
		return res;
	}
	
	/**
	 * 给一个word，一个string，问word能不能用string里面的字母组出来，考虑个数因素。followup是如果给的是string流和一个list of words，如何判断能不能。
	 * 如果words不能全部match，自己如何定义目标（比如match尽可能多的words或者尽可能多的字母个数）进行取舍。
	 */
	boolean canForm(String word, String str) {
		char[] arr = str.toCharArray();
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : word.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		for (char c : arr) {
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				
				if (map.get(c) == 0) {
					map.remove(c);
				}
			}
			
			if (map.size() == 0) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * By default, it is match as many chars as possible
	 * What if the strategy is to match as many words as possible. 
	 * My thought is to sort the word list by length first. Build a Map using data stream. Match word by word, from short to long
	 */
	boolean canFormStream(List<String> list, Iterator<Character> it) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (String word : list) {
			for (char c : word.toCharArray()) {
				map.put(c, map.getOrDefault(c, 0) + 1);
			}
		}
		
		while (it.hasNext()) {
			char c = it.next();
			
			if (map.containsKey(c)) {
				map.put(c, map.get(c) - 1);
				
				if (map.get(c) == 0) {
					map.remove(c);
				}
			}
			
			if (map.size() == 0) {
				return true;
			}
		}
		
		// How many chars are not matched. Need to multiply the count
		System.out.println(map.size());
		
		return false;
	}
}
