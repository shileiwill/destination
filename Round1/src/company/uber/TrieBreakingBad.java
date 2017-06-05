package company.uber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 具体介绍一下这个题， input是String array(大小写都有), 和一个String name（全小写） 
 * 输出要求有点复杂，在name一个个substring里找，如果这个substring没有在array里出现过，就直接输出。
 * 如果在array里出现过，就找尽量长的可以在array里匹配得到的，然后把第一个字母变成大写并且前后加上符号再输出。 
 * 比如array = {“b”, “Bc”, “e”} name = “abcde”; 
 * output就是ad 这里因为b可以匹配到b，bc可以匹配到bc而且更长所以答案就是这样。
 * 
 * 写出一个auto complete的程序 要求程序可以跑test。当时写出了trie 就差最后的dfs没写完 面试官不允许我用boolean去标记 说是不适合同一时间很多搜索
 */
public class TrieBreakingBad {

	TrieNode root = new TrieNode();
	public static void main(String[] args) {
		TrieBreakingBad t = new TrieBreakingBad();
		
		String source = "helloword";
		String[] dict = {"abc", "dec", "UDA", "abcde", "bcdef", "abfeU"};
		
		// Build Trie
		for (String s : dict) {
			t.root.insert(s, 0);
		}
		
		List<String> list = t.autoComplete("abc");
		for (String s : list) {
			System.out.println(s);
		}
//		String res = "";
//		for (int i = 0; i < source.length(); i++) {
//			for (int j = i + 1; j < source.length(); j++) {
//				String sub = source.substring(i, j);
//				if (!root.startsWith(sub)) {
//					break;
//				}
//				if (sub.length() > res.length()) {
//					res = sub;
//				}
//			}
//		}
	}

	List<String> autoComplete(String s) {
		TrieNode node = root.search(s, 0);
		
		List<String> res = new ArrayList<String>();
		if (node == null) {
			return res;
		}
		
		dfs(res, node); 
		return res;
	}

	private void dfs(List<String> res, TrieNode node) {
		for (Map.Entry<Character, TrieNode> child : node.children.entrySet()) {
			TrieNode childNode = child.getValue();
			
			if (childNode.hasWord) {
				res.add(childNode.word);
			}
			
			dfs(res, childNode);
		}
	}
	
	static class TrieNode {
		Map<Character, TrieNode> children = null;
		boolean hasWord = false;
		String word = null;
		
		TrieNode() {
			this.children = new HashMap<Character, TrieNode>();
		}
		
		void insert(String s, int pos) {
			if (pos == s.length()) {
				this.hasWord = true;
				this.word = s;
				return;
			}
			
			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				this.children.put(now, new TrieNode());
			}
			
			this.children.get(now).insert(s, pos + 1);
		}
		
		TrieNode search(String s, int pos) {
			if (pos == s.length()) {
				return this;
			}
			
			char now = s.charAt(pos);
			if (!this.children.containsKey(now)) {
				return null;
			}
			
			return this.children.get(now).search(s, pos + 1);
		}
		
		boolean startsWith(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}
			
			return true;
		}
		
		boolean contains(String s) {
			TrieNode node = search(s, 0);
			if (node == null) {
				return false;
			}
			
			return node.hasWord;
		}
	}
}

/**
 * 给你一堆域名，其中一些是另一些的parent，比如.com是.youku.com的parent，然后.youku.com是.service.youku.com的parent这样，
 * 然后再给你一个网址，让你在那堆域名中找到这个网址的parent里面最长的一个，然后再往前退一个返回。语言有点不好描述，
 * 举个栗子： Domains:[“.com”,“.cn”“.service.com”“.net”“.youku.net”] url: “yeah.hello.youku.net” 
 * 这里.net和.youku.net都是这个url的parent,其中最长的是.youku.net，再往前退一个是hello,所以返回“hello.youku.net”
 * 
 * 后来想了想有点像trie，从后往前看就是了
 */
class longestDomain {
	public static void main(String[] args) {
		longestDomain ld = new longestDomain();
		String res = ld.findLongest();
		System.out.println(res);
	}
	
	String[] arr = {".com", ".cn", ".service.com", ".net", ".youku.net"};
	String url = "yeah.hello.youku.net";
	TrieBreakingBad.TrieNode root = new TrieBreakingBad.TrieNode();
	
	String findLongest() {
		for (String domain : arr) {
			String reversed = reverse(domain);
			root.insert(reversed, 0);
		}
		
		String reversedUrl = reverse(url);
		String[] sections = reversedUrl.split("\\.");
		String now = "";
		for (String sec : sections) {
			now = now + sec + ".";
			
			if (!root.contains(now)) {
				break;
			}
		}
		
		return reverse(now);
	}
	
	String reverse(String s) {
		char[] arr = s.toCharArray();
		
		int left = 0, right = s.length() - 1;
		while (left < right) {
			char tmp = arr[left];
			arr[left] = arr[right];
			arr[right] = tmp;
			
			left++;
			right--;
		}
		
		return new String(arr);
	}
}

/**
 * 题目是给一个list of word， 每个word 叫做root word 然后还有个input string 是sentence， 要求是把sentence 中的单词如果有root word 是单词的prefix， 
 * 就把这个单词替换成root word。最后返回替换后的sentence 
 * 例子： root word ["abc", "car", "race"] 
 * 		sentence "abcde cars ca bounse" return: "abc car ca bounse" 另外不会有某个root word 是另外一个root word的prefix 用trie tree 解了。
 */
class RootWordReplace {
	public static void main(String[] args) {
		RootWordReplace rr = new RootWordReplace();
		rr.replace();
	}
	
	String[] rootWords = {"abc", "car", "race"};
	String sentence = "abcde cars ca bounse";
	TrieBreakingBad.TrieNode root = new TrieBreakingBad.TrieNode();
	
	void replace() {
		for (String word : rootWords) {
			root.insert(word, 0);
		}
		
		String[] arr = sentence.split("\\s+");
		for (int i = 0; i < arr.length; i++) {
			String s = arr[i];
			
			for (int len = 1; len <= s.length(); len++) {
				String sub = s.substring(0, len);
				
				TrieBreakingBad.TrieNode node = root.search(sub, 0);
				if (node == null) { // Nothing with this prefix
					break;
				} else if (node.hasWord) {
					arr[i] = node.word;
					break;
				} else {
					// has this prefix, continue
				}
			}
		}
		
		for (String s : arr) {
			System.out.print(s + " ");
		}
	}
}