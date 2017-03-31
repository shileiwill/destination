package company.tripadvisor.trialpay;

public class LeaveOneSpace {

	public static void main(String[] args) {
		LeaveOneSpace los = new LeaveOneSpace();
		
		while (los.hasNext()) {
			System.out.println(los.next());
		}
	}

	int[] arr = {1, 3, 5, 4, 6, 2, 9, 6, 4, 3, 7, 8, 9, 10};
	int pos = 0; // Next possible index
	
	int next() {
		if (!hasNext()) {
			throw new RuntimeException();
		}
		return arr[pos++];
	}
	
	boolean hasNext() {
		while(pos < arr.length && arr[pos] % 2 != 0) {
			pos++;
		}
		
		return pos != arr.length;
	}
	
	void leaveOneSpace() {
		String str = "I  love this good    place  ";
		
		StringBuilder sb = new StringBuilder(str);
		boolean prev = false;
		
		for (int i = str.length() - 1; i >= 0; i--) {
			if (prev && str.charAt(i) == ' ') {
//				sb.setCharAt(i, 'N');
				sb.deleteCharAt(i); // Must delete from the end, as the length of StringBuilder is changing all the time
				prev = true; // Still true
			} else if (str.charAt(i) == ' ') {
				prev = true;
			} else {
				prev = false;
			}
		}
		
		System.out.println("--" + sb.toString() + "--");
	}
}
