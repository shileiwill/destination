package Facebook;

/**
 * Created by weixwu on 7/6/2017.
 */
public class LeftMostOne {
    public int findLeftOne(int[][] matrix) {
        int left = matrix[0].length; // right end, becoming smaller
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < left; j++) {
                if (matrix[i][j] == 1) {
                    left = j;
                    break;
                }
            }
        }
        return left;
    }
}
