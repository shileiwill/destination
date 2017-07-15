package system.design;
/*
Trie Service
Build tries from a list of pairs. Save top 10 for each node.
 Example
Given a list of
<"abc", 2>
<"ac", 4>
<"ab", 9>
Return <a[9,4,2]<b[9,2]<c[2]<>>c[4]<>>>, and denote the following tree structure:
         Root
         / 
       a(9,4,2)
      /    \
    b(9,2) c(4)
   /
 c(2)
int value可以看做是document ID, 这个char在哪个document中出现过（Inverted Index）
把输入的单词放进树中，在放入频率的时候按从大到小顺序插入top10 List中即可。
如果top10 List大小大于10，则删掉最后一个元素。
*/
import java.util.*;

public class TrieService {
	class TrieNode {
		public NavigableMap<Character, TrieNode> children;
		public List<Integer> top10;
		
		public TrieNode() {
			children = new TreeMap<Character, TrieNode>();
			top10 = new ArrayList<Integer>();
		}
	}
	
    private TrieNode root = null;

    public TrieService() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        // Return root of trie root, and lintcode will print the tree struct.
        return root;
    }

    // @param word a string
    // @param frequency an integer
    public void insert(String word, int frequency) {
        TrieNode cur = root;
        int len = word.length();

        for (int i = 0; i < len; ++i) { //用遍历的方法，curNode一直在变
            Character c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
            	cur.children.put(c, new TrieNode());
            }

            cur = cur.children.get(c);
            addFrequency(cur.top10, frequency);
        }
    }

    public void addFrequency(List<Integer> top10, int frequency) {
        top10.add(frequency);
        
        int len = top10.size();
        int index = len - 1;
        
        while (index > 0) {
            if (top10.get(index) > top10.get(index - 1)) {
                int temp1 = top10.get(index);
                int temp2 = top10.get(index - 1);
                top10.set(index, temp2);
                top10.set(index - 1, temp1);
                
                index--;
            } else {
            	break; 
            }
        }
        
        if (len > 10) {
        	top10.remove(len - 1);
        }
    }
}
