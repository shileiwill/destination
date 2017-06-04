package company.uber;

import java.util.ArrayList;
import java.util.List;

/**
 * input: a very long string as a message to send to user, the string length limit of a single message 
 * output: array of messages formatted as ['(1/10) message 1', '(2/10) message 2', ...] 
 */
public class StringOperation {

	public static void main(String[] args) {
//		print("averylongstringasamessagetosendtouser", 5);
		StringOperation so = new StringOperation();
		so.evaluate("2y+4+ 3y+4 = 5y-6y+2y +1", 7);
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
}
