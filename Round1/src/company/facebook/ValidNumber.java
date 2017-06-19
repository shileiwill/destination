package company.facebook;
/**
 * 65. Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */
public class ValidNumber {
    /*  All we need is to have a couple of flags so we can process the string in linear time:
        We start with trimming.
        If we see [0-9] we reset the number flags.
        We can only see . if we didn't see e or ..
        We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
        We can only see + and - in the beginning and after an e
        any other character break the validation.
        At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.
    */
    public boolean isNumber(String s) {
        s = s.trim();
        
        boolean numberSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if (c == '.') {
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (c == 'e') {
                if (eSeen || !numberSeen) { // 46.e2 is true
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            } else if (c == '+' || c == '-') { // i == 0 is fine, e is one step ahead is also fine
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else { // Other characters are illegal
                return false;
            }
        }
        
        return numberSeen && numberAfterE;
    }
}