package company.linkedin;
/**
 * 求下一个二进制为0，1间隔的数，如10（1010），16（10000），17（10001），18（10010），20（10100），
要过大数据，考察bit操作
 */
public class Next01Interval {

	public static void main(String[] args) {

	}

	// if current position is not avaliable, if it is 1, make it 0, move left
	int next(int num) {
		int mask = -1; //11111111111111
		for (int i = 0; i < 32; i++) {
			if ((((num >>> i) & 1) == 0) && (((num >>> (i + 1)) & 1) == 0)) { // 2 consecutive 0s
				num += (1 << i); // Make the ith digit 1
				return num;
			} else if (((num >>> i) & 1) == 1) { // The ith digit is 1
				num = (mask << (i + 1)) & num;
			}
		}
		
		return num;
	}
}
