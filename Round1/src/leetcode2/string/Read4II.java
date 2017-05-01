package leetcode2.string;

import java.util.LinkedList;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it
 * returns 3 if there is only 3 characters left in the file.
 * 
 * By using the read4 API, implement the function int read(char *buf, int n)
 * that reads n characters from the file.
 * 
 * Note: The read function may be called multiple times.
 * 
 * 
 * Think that you have 4 chars "a, b, c, d" in the file, and you want to call
 * your function twice like this:
 * 
 * read(buf, 1); // should return 'a' read(buf, 3); // should return 'b, c, d'
 * All the 4 chars will be consumed in the first call. So the tricky part of
 * this question is how can you preserve the remaining 'b, c, d' to the second
 * call.
 * 
 * The only thing is when you call read4() which reads 4 bytes into your buffer
 * you might read more than you need, so you want to store those bytes in the
 * structure, and next time you call read will start from those stored bytes,
 * then read more from the file.
 */
public class Read4II {
	
	int read4(char[] buf) {return -1;}
	
	LinkedList<Character> leftover = new LinkedList<Character>();

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

		int count = 0;

		while (leftover.size() > 0 && count < n) {

			char c = leftover.removeFirst();

			buf[count++] = c;

		}

		while (count < n) {

			char[] curBuf = new char[4]; // This doesnt matter

			int charsRead = read4(curBuf);

			if (charsRead == 0) {

				return count; // Nothing to read any more

			}

			int index = 0;

			while (index < charsRead && count < n) {

				buf[count++] = curBuf[index++]; // Transfer to destination
												// buffer

			}

			while (index < charsRead) {

				leftover.add(curBuf[index++]);

			}

		}

		return count;

	}

}

/* The read4 API is defined in the parent class Reader4.
int read4(char[] buf); */

class ANewVersionByMyself extends Reader4 {
/**
* @param buf Destination buffer
* @param n   Maximum number of characters to read
* @return    The number of characters read
*/

LinkedList<Character> leftOver = new LinkedList<Character>();
public int read(char[] buf, int n) {
  int index = 0;
  
  // Use cache
  while (!leftOver.isEmpty() && index < n) {
          buf[index++] = leftOver.removeFirst();
  }
  
  if (index == n) {
      return n; // Cache is enough
  }
  
  while (index < n) {
      char[] arr = new char[4];
      int count = read4(arr);
  
      int i = 0;
      while (i < count && index < n) {
          buf[index++] = arr[i++];
      }
      
      // Enough, and have some extra, save to leftOver
      if (index == n) {
          while (i < count) {
              leftOver.addLast(arr[i++]);
          }
      }
      
      // Can't get more
      if (count < 4) {
          return index;
      }
  }
  
  return index;
}
}
