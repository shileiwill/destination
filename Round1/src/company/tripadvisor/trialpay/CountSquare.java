package company.tripadvisor.trialpay;

public class CountSquare {

	public static void main(String[] args) {

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
