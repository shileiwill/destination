package company.facebook;

import java.util.ArrayList;
/**
 * Pretty print a json object using proper indentation.

Every inner brace should increase one indentation to the following lines.
Every close brace should decrease one indentation to the same line and the following lines.
The indents can be increased with an additional ‘\t’
Example 1:

Input : {A:"B",C:{D:"E",F:{G:"H",I:"J"}}}
Output : 
{ 
    A:"B",
    C: 
    { 
        D:"E",
        F: 
        { 
            G:"H",
            I:"J"
        } 
    }     
}
Example 2:

Input : ["foo", {"bar":["baz",null,1.0,2]}]
Output : 
[
    "foo", 
    {
        "bar":
        [
            "baz", 
            null, 
            1.0, 
            2
        ]
    }
]
[] and {} are only acceptable braces in this case.

Assume for this problem that space characters can be done away with.

Your solution should return a list of strings, where each entry corresponds to a single line. The strings should not have “\n” character in them.
 */
public class PrettyJSON {

	public static void main(String[] args) {

	}

	public ArrayList<String> prettyJSON(String A) {
		
		ArrayList<String> res = new ArrayList<>();
		StringBuilder str = new StringBuilder();
		int n = A.length();
		int tabs = 0;

		for (int i = 0; i < n;) {

			i = skipSpace(A, i);

			if (i >= n)
				break;

			str = new StringBuilder();
			char c = A.charAt(i);

			if (delimiter(c)) {

				if (isOpenBracket(c)) {
					for (int j = 0; j < tabs; j++)
						str.append("\t");
					tabs++;
				} else if (isClosedBracket(c)) {
					tabs--;
					for (int j = 0; j < tabs; j++)
						str.append("\t");
				}

				str.append(c);
				i++;

				if (i < n && canAdd(A.charAt(i))) {
					str.append(A.charAt(i));
					i++;
				}

				res.add(str.toString());

				continue;
			}

			while (i < n && !delimiter(A.charAt(i))) {
				str.append(A.charAt(i));
				i++;
			}

			if (i < n && canAdd(A.charAt(i))) {
				str.append(A.charAt(i));
				i++;
			}

			StringBuilder strB = new StringBuilder();

			for (int j = 0; j < tabs; j++)
				strB.append("\t");

			strB.append(str);
			res.add(strB.toString());
		}

		return res;

	}

	public boolean canAdd(char c) {
		if (c == ',' || c == ':')
			return true;

		return false;
	}

	public boolean delimiter(char c) {
		if (c == ',' || isOpenBracket(c) || isClosedBracket(c))
			return true;
		return false;
	}

	public boolean isOpenBracket(char c) {
		if (c == '[' || c == '{')
			return true;
		return false;
	}

	public boolean isClosedBracket(char c) {
		if (c == ']' || c == '}')
			return true;
		return false;
	}

	public int skipSpace(String A, int i) {
		int n = A.length();
		while (i < n && A.charAt(i) == ' ')
			i++;
		return i;
	}

}