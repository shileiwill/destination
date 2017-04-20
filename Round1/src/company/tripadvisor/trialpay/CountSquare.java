package company.tripadvisor.trialpay;

public class CountSquare {

	public static void main(String[] args) {
//		int num = 123;
//		int res = 0;
//		
//		while (num != 0) {
//			int digit = num % 10;
//			num = num / 10;
//			
//			res = res * 10 + digit;
//		}
//		
//		System.out.println(res);
		
		CountSquare cs = new CountSquare();
		int[] arr = {1, 2, 4, 5, 6, 9};
		cs.helper(arr, 0);
		
		System.out.println(cs.oddSum + "===" + cs.evenSum);
	}
	
	int oddSum = 0;
	int evenSum = 0;
	
	void helper(int[] arr, int i) {
		if (i >= arr.length) {
			return;
		}
		if (i % 2 == 0) {
			evenSum += arr[i];
		} else {
			oddSum += arr[i];
		}
		
		helper(arr, i + 1);
	}

	public int countSquare (boolean[][] ver , boolean[][] hor) {
	    if ( ver == null || hor == null || ver.length == 0 || ver[0].length == 0 || hor.length == 0 || hor[0].length == 0 ){
	        return 0;
	    }
	    int[][] dpV = new int[ ver.length ][ ver[0].length ];
	    int[][] dpH = new int[ hor.length ][ hor[0].length ];
	    int res = 0;
	    for (int j = 0; j < dpV[0].length; ++j) { // Column is primary, column by column
	        for ( int i = 0; i < dpV.length; ++i ) {
	            if (ver[i][j]) {
	                dpV[i][j] = i == 0 ? 1 : (dpV[i - 1][j] + 1);
	            } else {
	                dpV[i][j] = 0;
	            }
	        }
	    } 
	    for (int i = 0; i < dpH.length; ++i) { // Row is primary, row by row
	        for ( int j = 0; j < dpH[0].length; ++j ) {
	            if (hor[i][j]) {
	                dpH[i][j] = j == 0 ? 1 : (dpH[i][j - 1] + 1);
	            } else {
	                dpH[i][j] = 0;
	            }
	        }
	    }

	    for ( int i = 0; i < dpH.length; ++i ) {
	        for ( int j = 0; j < dpH[0].length; ++j ) {
	            for (int len = 1; len <= Math.min(dpH[0].length - j, dpV.length - i); ++len){
	            	// Top && Bottom && left && right
	                if (dpH[i][j + len - 1] >= len && dpH[i + len][j + len - 1] >= len && dpV[i + len - 1][j] >= len && dpV[i + len - 1][len + j] >= len) {
	                	++res;
	                }
	                if (!(dpH[i + len][len + j - 1] >= len)) {
	                    break;
	                }
	             }
	        }
	    }
	    return res; 
	}
}
