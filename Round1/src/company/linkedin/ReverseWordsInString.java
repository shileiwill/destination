package company.linkedin;
/**
 *  lc151 刚开始不用考虑前后有空格以及每个word之间大于一个空格情况，很快写出来了
接着followup 如果word之间大于一个空格保留空格数，可能因为太紧张调来调去都不对，然后问小哥有没有hint
结果小哥说了一个很好的方法 把中间的空格也按照word一样reverse 然后在整体reverse.
 */
public class ReverseWordsInString {

	public static void main(String[] args) {
		ReverseWordsInString r = new ReverseWordsInString();
		
		String s = "   hello tom  jack ";
		String res = r.reverseWords(s);
		System.out.println(res);
	}


    public String reverseWords2(String s) {
        if (s == null) {
            return null;
        }
        
        s = s.trim();
        String[] arr = s.split("\\s+");
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i] + " ");
        }
        
        sb.setLength(sb.length() - 1);
        
        return sb.toString();
    }
    
    public String reverseWords(String s) {
        if (s == null) {
            return null;
        }
        
        char[] arr = s.toCharArray();
        reverse(arr, 0, s.length() - 1);
        
        int left = 0; int right = 0;
        while (left < arr.length && right < arr.length) {
            while (left < arr.length && arr[left] == ' ') {
                left++; // left is first non-space
            }
            
            right = left + 1;
            while (right < arr.length && arr[right] != ' ') {
                right++; // right is first space
            }
            
            reverse(arr, left, right - 1);
            left = right;
        }
        
        return new String(arr);
    }
    
    void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            
            left++;
            right--;
        }
    }

}
