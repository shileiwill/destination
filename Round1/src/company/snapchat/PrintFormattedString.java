package company.snapchat;

/**
 * 后来写了个简单的string题，给'(ab(cd))(ef)', 打印出

(
    ab
        (
            cd
        )
)
(
    ef
)

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=481907&highlight=snapchat

There is some difference in this example, empty character matters, they are in a new line
第三个给一个String 比如“(hey hello (good (bye) hi))” 给这个string加上indentation。加完以后打印出来应该是
(
  hey
  hello
  (
    good
    (
      bye
    )
    hi
  )
)
 * @author lei2017
 *
 */
public class PrintFormattedString {

	public static void main(String[] args) {
		String input = "(ab(cd))(ef)";
//		String input = "(hey hello (good (bye) hi))";
		
		StringBuilder sb = new StringBuilder();
		int tabs = 0; // Count/Track缩进 或加或减 一直有效
		
		for(int i = 0; i < input.length();) { // Don't worry about i++
			char cur = input.charAt(i);
			sb = new StringBuilder();
			
			if(cur == ' ') {
				i++;
				continue; // A new line, with same tab
			} else if(cur == '(') {
				for (int j = 0; j < tabs; j++) { // Print tabs first
					sb.append("\t");
				}
				
				sb.append("(");
				tabs++;
				i++;
			} else if(cur == ')') {
				tabs--; // 先缩进一下
				for (int j = 0; j < tabs; j++) {
					sb.append("\t");
				}
				
				sb.append(")");
				i++;
			} else {
				for (int j = 0; j < tabs; j++) {
					sb.append("\t");
				}
				
				// Letters！
				while(i < input.length() && input.charAt(i) != '(' && input.charAt(i) != ')' && input.charAt(i) != ' ') {
					sb.append(input.charAt(i));
					i++;
				}
			}
			
			System.out.println(sb.toString());
		}
	}
}