package leetcode2.string;
/**
 * 44. Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
 */
/*
    For each element in s
If *s==*p or *p == ? which means this is a match, then goes to next element s++ p++.
If p=='*', this is also a match, but one or many chars may be available, so let us save this *'s position and the matched s position.
If not match, then we check if there is a * previously showed up,
       if there is no *,  return false;
       if there is an *,  we set current p to the next element of *, and set current s to the next saved s position.

e.g.

abed
?b*d**

a=?, go on, b=b, go on,
e=*, save * position star=3, save s position ss = 3, p++
e!=d,  check if there was a *, yes, ss++, s=ss; p=star+1
d=d, go on, meet the end.
check the rest element in p, if all are *, true, else false;
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int sIdx = 0;
        int pIdx = 0;
        int match = 0;
        int star = -1;
        
        while (sIdx < s.length()) {
            if (pIdx < p.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')) {
                // Move both, if there is a match
                pIdx++;
                sIdx++;
            } else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                // * found, remember the match point in S and star point in P, move forward only pIdx, as * can match empty
                match = sIdx;
                star = pIdx;
                pIdx++;
            } else if (star != -1) {
                // There is no match and current char in P is not *, Need to bring previous star to help
                pIdx = star + 1; // Bring pIdx back to star + 1
                match++; // One more char matched
                sIdx = match; // Move sIdx forward
            } else {
                return false;
            }
        }
        
        // Further check pIdx, as it may contain more characters
        // S is exausted already, P must contain only *. ? is not allowed, as it will match one.
        while (pIdx < p.length() && p.charAt(pIdx) == '*') {
            pIdx++;
        }
        
        return pIdx == p.length();
    }
}