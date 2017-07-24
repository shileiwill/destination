package Facebook;

import java.util.*;

/**
 * Created by weixwu on 7/11/2017.
 */
public class RemoveInvalidParentheses {
    public String removeInvalid(String s){
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ')'){
                if (stack.isEmpty()){
                    sb.replace(i, i + 1, "*");
                }else{
                    stack.pop();
                }
            }else if (s.charAt(i) == '('){
                stack.push(i);
            }
        }
        while(!stack.isEmpty()){
            int cur = stack.pop();
            sb.replace(cur, cur + 1, "*");
        }
        return sb.toString().replaceAll("\\*", "");
    }

    public static void main(String args[]){
        RemoveInvalidParentheses rp = new RemoveInvalidParentheses();
        System.out.print(rp.removeInvalid("(a)())((()"));
    }
}
