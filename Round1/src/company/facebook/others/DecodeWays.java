package Facebook;

/**
 * Created by weixwu on 7/2/2017.
 */
public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        dp[1] = dp[0];
        for (int i = 2; i <= s.length(); i++){
            dp[i] += s.charAt(i - 1) - '0' != 0 ? dp[i - 1] : 0;
            dp[i] += s.charAt(i - 2) - '0' > 0 && (s.charAt(i - 2) - '0') * 10 + s.charAt(i - 1) <= 26 ? dp[i - 2] : 0;
        }
        return dp[s.length()];
    }

    public static int decodeWays(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = s.charAt(0) == '0' ? 0 : 1;
        dp[1] = s.charAt(0) == '0' ? 0 : (s.charAt(0) == '*' ? 9 : 1);
        for (int i = 2; i <= s.length(); i++){
            if (s.charAt(i - 1) != '*'){
                if (s.charAt(i - 1) != '0') dp[i] += dp[i-1];
                if (s.charAt(i - 2) != '*')
                    dp[i] += s.charAt(i - 2) > '0' && (s.charAt(i - 2)- '0' ) * 10 + s.charAt(i - 1) - '0' <= 26 ? dp[i - 2] : 0;
                else if (s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '6') dp[i] += dp[i - 2] * 2;
                else dp[i] += dp[i - 2];

            }else{
                dp[i] += dp[i - 1] * 9;
                if (s.charAt(i - 2) != '*'){
                    if (s.charAt(i - 2) == '1') dp[i] += dp[i - 2] * 9;
                    else if (s.charAt(i - 2) == '2') dp[i] += dp[i -2] * 6;
                }else{
                    dp[i] += dp[i -2] * 15;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String args[]){
        DecodeWays dw = new DecodeWays();
        System.out.print(dw.decodeWays("**9"));
    }
}
