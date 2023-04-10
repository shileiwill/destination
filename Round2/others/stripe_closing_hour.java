stripe_closing_hour.java

// https://docs.google.com/document/d/1gIPOFFxOcl3bLcQN0G3E1VbfYBTb8clLzw3bL5noS_w/edit


import java.util.*;
class Main {

  static int compute_penalty(String log, int closing) {
    	String[] arr = log.split(" ");
    
    	int penalty = 0;
    
    	if (closing < 0 || closing > arr.length) {
    		throw new RuntimeException("invalid closing");
    	}
    
    	for (int i = 0; i < closing; i++) {
    		if (arr[i].equals("N")) {
    			// if no customer during open time
    			penalty += 1;
    		}
    	}
    
    	for (int i = closing; i < arr.length; i++) {
    		// if there is customer during close time
    		if (arr[i].equals("Y")) {
    			penalty += 1;
    		}
    	}
  
  	 return penalty;
  }

  static int find_best_closing_time(String log) {
    int lowest_penalty = Integer.MAX_VALUE;
    int best_time = -1;
    int len = log.split(" ").length;

    for (int i = 0; i <= len; i++) {
      int cur_penalty = compute_penalty(log, i);
      if (cur_penalty < lowest_penalty) {
        lowest_penalty = cur_penalty;
        best_time = i;
      }
    }

    return best_time;
  }

  // important!!!
  static List<Integer> get_best_closing_times(String log) {
    List<Integer> res = new ArrayList<Integer>();
    int pos = 0;
    while (pos < log.length()) {
      int firstEnd = log.indexOf("END", pos);
      if (firstEnd == -1) {
        return res;
      }
      int lastBegin = log.substring(pos, firstEnd).lastIndexOf("BEGIN");
      if (lastBegin == -1) {
        return res;
      }

      String sub = log.substring(pos + lastBegin + 6, firstEnd - 1);
      // remove empty space, newlines from front and end
      sub = sub.trim();
      System.out.println(sub);
      int best_time = find_best_closing_time(sub);
      res.add(best_time);

      pos = firstEnd + 3;
    }

    return res;
  }
  
  public static void main(String[] args) {
    List<Integer> res1 = get_best_closing_times("BEGIN Y Y END \nBEGIN N N END");
    List<Integer> res2 = get_best_closing_times("BEGIN BEGIN \nBEGIN N N BEGIN Y Y\n END N N END");
    System.out.println(res1 + ":" + res2);
    // int best_time1 = find_best_closing_time("Y Y N N");
    // int best_time2 = find_best_closing_time("Y Y N Y");
    // int best_time3 = find_best_closing_time("N Y N Y");
    
    // System.out.println(best_time1 + ":" + best_time2 + ":" + best_time3);
    // int res1 = compute_penalty("Y Y N Y", 0);
    // int res2 = compute_penalty("N Y N Y", 2);
    // int res3 = compute_penalty("Y Y N Y", 4);
    
    // System.out.println(res1 + ":" + res2 + ":" + res3);
  }
}