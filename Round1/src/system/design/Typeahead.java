package system.design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/*
 * Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. 
 * The dictionary will give at the initialize method and wont be changed. The method to find all words with given
		substring would be called multiple times.

Given dictionary = {"Jason Zhang", "James Yu", "Bob Zhang", "Larry Shi"}

search "Zhang", return ["Jason Zhang", "Bob Zhang"].

search "James", return ["James Yu"].

当数据量小时，可以用Map做。数据量大了，用Trie做, Trie共用了prefix 更省空间

如果数据规模本身就比较小，能一次性装入内存呢？虽然有一千万个Query，但是由于重复度比较高，因此事实上只有300万的Query，每个Query255Byte，
因此我们可以考虑把他们都放进内存中去（300万个字符串假设没有重复，都是最大长度，那么最多占用内存3M*1K/4=0.75G。所以可以将所有字符串都存放在内存中进行处理），
而现在只是需要一个合适的数据结构，在这里，HashTable绝对是我们优先的选择

hashmap统计：先对这批海量数据预处理。具体方法是：维护一个Key为Query字串，Value为该Query出现次数的HashTable，即hash_map(Query，Value)，
每次读取一个Query，如果该字串不在Table中，那么加入该字串，并且Value值设为1；如果该字串在Table中，那么将该字串的计数加一即可。最终我们在O(N)的时间复杂度内用Hash表完成了统计；
堆排序：借助堆这个数据结构，找出Top K，时间复杂度为N‘logK。即借助堆结构，我们可以在log量级的时间内查找和调整/移动。因此，维护一个K(该题目中是10)大小的小根堆，
然后遍历300万的Query，分别和根元素进行对比。所以，我们最终的时间复杂度是：O（N） + N' * O（logK），（N为1000万，N’为300万）
 * */

public class Typeahead {
    private HashMap<String, List<String>> map = new HashMap<String , List<String>>();
    
    // @param dict A dictionary of words dict
    public Typeahead(Set<String> dict) {
        for (String str : dict) {
            int len = str.length();
            
            for (int start = 0; start < len; start++) {
            	for (int end = start + 1; end <= len; end++) {
                    String sub = str.substring(start, end);
                    
                    if (!map.containsKey(sub)) {
                        map.put(sub, new ArrayList<String>());
                        map.get(sub).add(str);
                    } else {
                        List<String> list = map.get(sub);
                        if (!str.equals(list.get(list.size() - 1))) { // 去重，{"Jason Zhang"， "Jason Zhang"}
                            list.add(str);
                        }
                    }
                }
            }
        }
    }

    // @param str: a string
    // @return a list of words
    public List<String> search(String str) {
        if (!map.containsKey(str)) {
            return new ArrayList<String>();
        } else {
            return map.get(str);
        }
    }
}
