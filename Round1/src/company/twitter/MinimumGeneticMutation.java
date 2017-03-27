package company.twitter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 433. A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".

Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.

For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.

Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.

Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.

Note:

Starting point is assumed to be valid, so it might not be included in the bank.
If multiple mutations are needed, all mutations during in the sequence must be valid.
You may assume start and end string is not the same.
Example 1:

start: "AACCGGTT"
end:   "AACCGGTA"
bank: ["AACCGGTA"]

return: 1
Example 2:

start: "AACCGGTT"
end:   "AAACGGTA"
bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]

return: 2
Example 3:

start: "AAAAACCC"
end:   "AACCCCCC"
bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]

return: 3
 */
public class MinimumGeneticMutation {

    public int minMutation(String start, String end, String[] bank) {
        Set<String> dict = new HashSet<String>();
        for (String str : bank) {
            dict.add(str);
        }
        
        char[] arr = {'A', 'C', 'G', 'T'};
        
        if (!dict.contains(end)) {
            return -1;
        }
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        dict.remove(start);
        int level = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                
                for (int j = 0; j < now.length(); j++) {
                    for (int k = 0; k < 4; k++) {
                        if (arr[k] != now.charAt(j)) {
                            String newStr = now.substring(0, j) + arr[k] + now.substring(j + 1);
                            if (newStr.equals(end)) {
                                return level + 1;
                            }
                            
                            if (dict.contains(newStr)) {
                                queue.offer(newStr);
                                dict.remove(newStr);
                            }
                        }
                        
                    }
                }
            }
            
            level++;
        }
        
        return -1;
    }

}
