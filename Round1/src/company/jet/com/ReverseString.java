package company.jet.com;

public class ReverseString {

	public static void main(String[] args) {
		ReverseString rs = new ReverseString();
		String reversed = rs.reverse2(" Texas is  hot");
		System.out.println(reversed);
	}

	String reverse(String s) {
		s = s.trim();
		s = s.replaceAll("\\s+", " ");
		
		String[] arr = s.split(" ");
		int left = 0, right = arr.length - 1;
		
		while (left < right) {
			String tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
			
			left++;
			right--;
		}
		
		StringBuilder sb = new StringBuilder();
		for (String s1 : arr) {
			sb.append(s1 + " ");
		}
		
		sb.setLength(sb.length() - 1);
		
		return sb.toString();
	}
	
	String reverse2(String s) {
		StringBuilder sb = new StringBuilder();
		
		s = s.trim();
		char[] arr = s.toCharArray();
		
		int left = 0, right = s.length() - 1;
		while (left < right) {
			swap(arr, left, right);
			left++;
			right--;
		}
		
		left = 0; right = 0;
		while (left < arr.length) {
			while (left < arr.length && arr[left] == ' ') {
				left++;
			}
			
			right = left;
			while (right < arr.length && arr[right] != ' ') {
				right++;
			}
			
			if (left < right) {
				reverseChar(arr, left, right - 1);
				for (int i = left; i <= right - 1; i++) {
					sb.append(arr[i]);
				}
				sb.append(" ");
			}
			
			left = right + 1;
		}
		
		System.out.println("SB : " + sb.toString());
		return String.valueOf(arr);
	}
	
	void reverseChar(char[] arr, int left, int right) {
		while (left < right) {
			char tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
			
			left++;
			right--;
		}
	}
	
	void swap(char[] arr, int left, int right) {
		char tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;
	}
}
