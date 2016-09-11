package easy;
/**
 * 6. The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class ZigZagConvert {
    public String convert(String s, int numRows) {
        int len = s.length();
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int k = 0; k < rows.length; k++) {
            rows[k] = new StringBuilder();
        }
        
        int i = 0;
        while (i < len) {
            for (int row = 0; row < numRows && i < len; row++) {
                rows[row].append(s.charAt(i++));
            }
            for (int row = numRows - 2; row >= 1 && i < len; row--) {
                rows[row].append(s.charAt(i++));
            }
        }
        
        for (int k = 1; k < rows.length; k++) {
            rows[0].append(rows[k]);
        }
        
        return rows[0].toString();
    }
}
