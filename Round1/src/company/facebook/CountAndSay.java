package company.facebook;
/**
 * 38. The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.
这个题的关键点在如何灵活利用循环
Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        String str = "1";
        
        for (int i = 1; i < n; i++) {
            StringBuilder build = new StringBuilder();
            char last = str.charAt(0);
            int count = 1;
            
            for (int j = 1; j < str.length(); j++) {
                char cur = str.charAt(j);
                if (cur == last) {
                    count++;
                } else {
                    build.append(count + "" + last);
                    count = 1;
                    last = cur;
                }
            }
            
            build.append(count + "" + last);
            str = build.toString();
        }
        
        return str;
    }
}
