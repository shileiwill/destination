package cc150.chapter1;

public class StringCompression {

	public static void main(String[] args) {
		StringCompression sc = new StringCompression();
		String source = "aabcccdccaaa";
		String res = sc.compress2(source);
		
		System.out.println(res);
	}

	String compress(String source) {
		StringBuilder sb = new StringBuilder();
		
		char prev = source.charAt(0);
		int count = 1;
		for (int i = 1; i < source.length(); i++) {
			char cur = source.charAt(i);
			
			if (cur != prev) {
				sb.append(prev + "" + count);
				prev = cur;
				count = 1;
			} else {
				count++;
			}
		}
		sb.append(prev + "" + count);

		return source.length() <= sb.length() ? source : sb.toString();
	}
	
	String compress2(String source) {
		// Get length first to avoid creating stringbuilder
		int compressionLen = countCompression(source);
		
		if (compressionLen >= source.length()) {
			return source;
		}
		
		StringBuilder sb = new StringBuilder(compressionLen); // Init capacity
		
		char prev = source.charAt(0);
		int count = 1;
		for (int i = 1; i < source.length(); i++) {
			char cur = source.charAt(i);
			
			if (cur != prev) {
				sb.append(prev + "" + count);
				prev = cur;
				count = 1;
			} else {
				count++;
			}
		}
		sb.append(prev + "" + count);

		return source.length() <= sb.length() ? source : sb.toString();
	}
	
	int countCompression (String source) {
		int compressionLen = 0;
		int count = 0;
		
		for (int i = 0; i < source.length(); i++) {
			count++;
			if (i + 1 >= source.length() || source.charAt(i) != source.charAt(i + 1)) {
				compressionLen += 1 + String.valueOf(count).length();
				count = 0;
			}
		}
		
		return compressionLen;
	}
}
