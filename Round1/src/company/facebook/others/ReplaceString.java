package company.facebook.others;

/**
 * Created by weixwu on 7/9/2017.
 */
public class ReplaceString {
	// 把 %20 replace 成 " "
	public String replaceSpace(String s) {
		int posOld = 0, posNew = 0;
		char[] res = s.toCharArray();
		
		while (posOld < s.length()) {
			if (posOld < s.length() - 2 && res[posOld] == '%' && res[posOld + 1] == '2'
					&& res[posOld + 2] == '0') {
				while (posOld < s.length() - 2 && res[posOld] == '%' && res[posOld + 1] == '2'
						&& res[posOld + 2] == '0') {
					posOld += 3;
				} // 找出连续的多个%20, 一起替换成一个" "
				
				res[posNew++] = ' ';
			} else {
				res[posNew++] = res[posOld++];
			}
		}
		
		return new String(res).substring(0, posNew);
	}

	public static void main(String args[]) {
		ReplaceString rs = new ReplaceString();
		System.out.print(rs.replaceSpace("%20fhkj%2dgjt%20%20%20dgti%20"));
	}
}
