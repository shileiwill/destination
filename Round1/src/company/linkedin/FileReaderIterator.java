package company.linkedin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 英国人开始出第二题。他一开始先问我记得iterator吗，我想起准备过的什么nested integer iterator, merge array iterator,就说有点知识
，然后他说，写一个text file iterator, 每次return one line in the file...我就傻眼了，没见过啊，
而且我也忘了好reader的syntax了。想了一下我觉得可以有个temp string记录curr line, call next()的时候再call一下readline(), 
return temp之后，temp指向下一行。大概讲了一下思路就开始写，坑爹的是collabedit一直不能sync up，我自己写了半天，
他们还是最近的4,5行都看不到，三哥要换到stypi去，英国人说没时间换了凑合着吧，于是就是我写一段，自己存下来，refresh screen,再贴上去我写的，
写完过了几个case: null file, large file, grep(就是一直call next())，这时已经55分钟了，于是我开始问问题。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=132118&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
Implement a (Java) Iterable object that iterates lines one by one from a text file

** A reference to a file. 
public class TextFile implements Iterable<String>
{
  public TextFile(String fileName) { // please implement this

  /** Begin reading the file, line by line. The returned Iterator.next() will return a line.
  @Override
  public Iterator<String> iterator() { // please implement this-
 */
public class FileReaderIterator {

	public static void main(String[] args) {

	}

	BufferedReader br = null;
	FileReaderIterator(String path) {
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	Iterator<String> iterator() {
		List<String> res = new ArrayList<>();
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				res.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return res.iterator();
	}
}
