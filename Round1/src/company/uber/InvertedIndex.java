package company.uber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现两个方法，一个是add document，document 都是字符串。再一个就是搜索，支持 AND OR 的搜索。返回符合条件的document
 * 
 * 倒排索引的概念很简单：就是将文件中的单词作为关键字，然后建立单词与文件的映射关系。
 * 当然，你还可以添加文件中单词出现的频数等信息。倒排索引是搜索引擎中一个很基本的概念，几乎所有的搜索引擎都会使用到倒排索引
 */
public class InvertedIndex {

	public static void main(String[] args) {

	}

	// Word, Line#/File#, Count
	Map<String, Map<Integer, Integer>> map = new HashMap<String, Map<Integer, Integer>>();
	
	void createIndex(String path) throws Exception {
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		
		String curLine = "";
		int lineNumber = 0; // Line number starting from 0
		while ((curLine = br.readLine()) != null) {
			String[] arr = curLine.split("\\s+");
			
			for (int i = 0; i < arr.length; i++) {
				String curWord = arr[i];
				
				if (map.containsKey(curWord)) {
					Map<Integer, Integer> curMap = map.get(curWord);
					curMap.put(lineNumber, curMap.getOrDefault(lineNumber, 0) + 1);
				} else {
					Map<Integer, Integer> curMap = new HashMap<Integer, Integer>();
					curMap.put(lineNumber, 1); // 在当前行，出现了一次
					map.put(curWord, curMap);
				}
			}
			
			lineNumber++;
		}
		
		br.close();
		fr.close();
	}
	
	void getOccurance(String[] arr) {
		for (String word : arr) {
			if (map.containsKey(word)) {
				Map<Integer, Integer> curMap = map.get(word);
				
				System.out.println(word + " occurs: ");
				for (Map.Entry<Integer, Integer> entry : curMap.entrySet()) {
					System.out.println("line# " + entry.getKey() + " with count# " + entry.getValue());
				}
				System.out.println();
			} else {
				System.out.println(word + " doesn't exist");
			}
		}
	}
}
