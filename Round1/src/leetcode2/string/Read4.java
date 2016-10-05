package leetcode2.string;

/**
 * 157. Read N Characters Given Read4
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * Note: The read function will only be called once for each test case.
 */
public class Read4 {

	/*
	 * The read4 API is defined in the parent class Reader4.
	 * 
	 * int read4(char[] buf);
	 */
	int read4(char[] buf) {return -1;}
	/**
	 * 
	 * @param buf
	 *            Destination buffer
	 * 
	 * @param n
	 *            Maximum number of characters to read
	 * 
	 * @return The number of characters read
	 * 
	 */

	public int read(char[] buf, int n) {

		char[] curBuf = new char[4];

		int count = 0;

		while (count < n) {

			curBuf = new char[4]; // This doesnt matter

			int charsRead = read4(curBuf);

			if (charsRead == 0) {

				return count; // Nothing to read any more

			}

			int index = 0;

			while (index < charsRead && count < n) {

				buf[count++] = curBuf[index++]; // Transfer to destination
												// buffer

			}

		}
		
		return count;
	}
}