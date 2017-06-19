package company.facebook;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * int read4(char[] buf);
 */
public class Read4 {

	public int read(char[] buf, int n) {
		List<Character> list = new ArrayList<Character>();
		
		while (list.size() != n) {
			char[] arr = new char[4];
			
			int k = read4(arr);
			
			for (int i = 0; i < k && list.size() < n; i++) {
				list.add(arr[i]);
			}
			
			if (k < 4) {
				// No more
				break;
			}
		}
		
		return list.size();
	}
	
	LinkedList<Character> leftOver = new LinkedList<Character>();
	public int readMultipleTimes(char[] buf, int n) {
		int index = 0;
		List<Character> list = new ArrayList<Character>();
		
		// First read from leftover
		while (index < n && !leftOver.isEmpty()) {
			list.add(leftOver.pollFirst());
			index++;
		}
		
		while (index < n) {
			char[] arr = new char[4];
			
			int k = read4(arr);
			
			int i = 0;
			for (; i < k && index < n; i++) {
				list.add(arr[i]);
				index++;
			}
			
			while (i < k) { // Means there is leftOver
				leftOver.addLast(arr[i]);
				i++;
			}
			
			if (k < 4) { // No more from the stream
				break;
			}
		}
		
		return index;
	}
}
