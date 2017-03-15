package amazon;
/**
 * Given expression with operands and operators (OR , AND , XOR) , in how many ways can you evaluate the expression to true, by grouping in different ways? Operands are only true and false.
Input:
 string : T|F&T^T here '|' will represent or operator '&' will represent and operator '^' will represent xor operator 'T' will represent true operand 'F' will false 

Output:
 different ways % MOD where MOD = 1003 

Example:
 string : T|F only 1 way (T|F) => T so output will be 1 % MOD = 1 
 */
public class EvaluateExpressionToTrue {
    int MOD = 1003;
	public int cnttrue(String a) {
	    StringBuilder symbols = new StringBuilder();
	    StringBuilder operators = new StringBuilder();
	    
	    for (char c : a.toCharArray()) {
	        if (c == 'T' || c == 'F') {
	            symbols.append(c);
	        } else {
	            operators.append(c);
	        }
	    }
	    
	    return helper(symbols.toString().toCharArray(), operators.toString().toCharArray()) % MOD;
	}
	
	int helper(char[] symbols, char[] operators) {
	    int len = symbols.length;
	    int[][] T = new int[len][len];
	    int[][] F = new int[len][len];
	    
	    for (int i = 0; i < len; i++) {
	        if (symbols[i] == 'T') {
	            T[i][i] = 1;
	        } else {
	            F[i][i] = 1;
	        }
	    }
	    
	    for (int gap = 1; gap < len; gap++) { // Length of a substring
	        for (int i = 0, j = gap; j < len; i++, j++) { // left point, right point
	            for (int g = 0; g < gap; g++) { // 中间插一刀
	                int k = i + g; // OR this is also fine, which is easier to understand? for (int k = i; k < j; k++) {  
	                
	                switch(operators[k]) {
	                    case '&':
	                        T[i][j] += T[i][k] * T[k + 1][j];
	                        F[i][j] += T[i][k] * F[k + 1][j] + F[i][k] * T[k + 1][j] + F[i][k] * F[k + 1][j];
	                        break;
	                    case '|':
	                        T[i][j] += T[i][k] * F[k + 1][j] + F[i][k] * T[k + 1][j] + T[i][k] * T[k + 1][j];
	                        F[i][j] += F[i][k] * F[k + 1][j];
	                        break;
	                    case '^':
	                        T[i][j] += T[i][k] * F[k + 1][j] + F[i][k] * T[k + 1][j];
	                        F[i][j] += F[i][k] * F[k + 1][j] + T[i][k] * T[k + 1][j];
	                        break;
	                }
	                
	                T[i][j] %= MOD;
	                F[i][j] %= MOD;
	            }
	        }
	    }
	    
	    return T[0][len - 1];
	}
}

