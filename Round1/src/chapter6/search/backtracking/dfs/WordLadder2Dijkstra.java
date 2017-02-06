package chapter6.search.backtracking.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
/**
 * 126
 */
public class WordLadder2Dijkstra {

    Map<String,List<String>> map = new HashMap<String,List<String>>(); // neighbors
	List<List<String>> results = new ArrayList<List<String>>();
	
    public List<List<String>> findLadders(String start, String end, List<String> wordList) {
        Set<String> dict = new HashSet<String>(wordList);
        if (dict.size() == 0)
			return results;
        
        int min=Integer.MAX_VALUE;
        
        Queue<String> queue= new ArrayDeque<String>();
        queue.add(start);
        
		Map<String,Integer> ladder = new HashMap<String,Integer>(); // Record distances
		for (String string:dict)
		    ladder.put(string, Integer.MAX_VALUE);
		ladder.put(start, 0);
				
		dict.add(end);
		//BFS: Dijkstra search
		// http://nthucad.cs.nthu.edu.tw/~yyliu/personal/nou/04ds/dijkstra.html
		while (!queue.isEmpty()) {
		   
			String word = queue.poll();
			
			int step = ladder.get(word)+1;//'step' indicates how many steps are needed to travel to one word. 
			
			if (step > min) break; // Queue是先进先出，所以开始变大了，后边的都大了
			
			for (int i = 0; i < word.length(); i++){
			   StringBuilder builder = new StringBuilder(word); 
				for (char ch='a';  ch <= 'z'; ch++){
					builder.setCharAt(i,ch);
					String new_word=builder.toString();				
					if (ladder.containsKey(new_word)) { // Ladder is also the dictionary
					    if (step>ladder.get(new_word))//Check if it is the shortest path to one word.
					    	continue;
					    else if (step<ladder.get(new_word)){
					    	queue.add(new_word); // ONLY the shorter will be added to QUEUE
					    	ladder.put(new_word, step); // Update ladder
					    }// It is a KEY line. If one word already appeared in one ladder,
					   // Do not insert the same word inside the queue twice. Otherwise it gets TLE.
					    
					    if (map.containsKey(new_word)) //Build adjacent Graph
					    	map.get(new_word).add(word);
					    else{
					    	List<String> list= new LinkedList<String>();
					    	list.add(word);
					    	map.put(new_word,list);
					    }
					    
					    if (new_word.equals(end))
					    	min=step; // 不能立马break, 因为要找到所有的最短路径， 不一定只有一条， 要build neighbors

					}//End if dict contains new_word
				}//End:Iteration from 'a' to 'z'
			}//End:Iteration from the first to the last
		}//End While

    	//BackTracking
		LinkedList<String> result = new LinkedList<String>();
		backTrace(end,start,result);

		return results;        
    }
    private void backTrace(String word,String start,List<String> list){
    	if (word.equals(start)){
    		list.add(0,start);
    		results.add(new ArrayList<String>(list));
    		list.remove(0);
    		return;
    	}
    	list.add(0,word);
    	if (map.get(word)!=null)
    		for (String s:map.get(word))
    			backTrace(s,start,list);
    	list.remove(0);
    }

}
