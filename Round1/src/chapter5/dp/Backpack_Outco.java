	/**
	  Input:
	    weights = [10, 20, 30]
	    values =  [60, 100, 120]
	    capacity = 50
	  
	  Output: 220
	  
	  Input2:
	    weights = [10, 20, 30, 40]
	    values =  [130, 100, 120, 50]
	    capacity = 50
	  
	  Output2: 250 Note: Most optimal knapsack does not use the full capacity in this example
	  
	  Input3:
	    weights = [10, 20, 30, 40]
	    values =  [130, 100, 120, 50]
	    capacity = 60
	  
	  Output3: 350

	Notes:
	  Each item can be used only once
	  Input is valid integer, positive
	  Should be less than or equal to capacity
	  It is a subset problem with following 2 restrictions. Get all the items with the max values and less than capacity
	  For each item, there are 2 options, pick it or leave it

	                                        {}
	                  /                                          \
	                {10} pick 10                                   {} leave 10
	            /          \                                    /       \
	          {10, 20}      {10}                             {20}        {}       20
	       /      \        /      \                       /      \       /   \
	{10, 20, 30} {10, 20} {10, 30} {10}                {20, 30}  {20}  {30}   {}  30    
	                                             60
	                                    /        |        \          \
	                              50(60-10)      40        30         20
	                        /   |  |  \                          /   |   |  \
	                     40    30  20  10                     10     0   -10  -20 
	        /  |  | \                                      /  |  | \
	                                                     0   -10 -20  -30 
	  The magic equation is 
	  max = Math.max(helper(capacity), helper(capacity - weights[i]) + values[i]);
	  This is because for any item, there are 2 options, pick or leave it.

	  base cases:
	  if capacity <= 0, return. otherwise, keep trying.

	  https://github.com/OutcoSF/outcode_master/blob/master/whiteboarding/04_dynamic_programming/321_knapsack.md
	**/
import java.util.*;

class HelloWorld {
    static int[] weights = new int[]{10, 20, 30, 40};
    static int[] values = new int[]{130, 100, 120, 50};
    static int capacity = 60;

    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) {
      int res = helper(capacity, 0);
      System.out.println("Res is " + res);
    }

    // Solution 1: with return
    static int helper(int capacity, int i) {
      if (capacity <= 0) {
        return 0;
      }

      if (i >= weights.length) {
        return 0;
      }

      if (map.containsKey(capacity + "" + i)) {
        return map.get(capacity + "" + i);
      }

      int pick = 0;
      if (capacity >= weights[i]){
          pick = helper(capacity - weights[i], i + 1) + values[i];
      }

      int notpick = helper(capacity, i + 1);
      int max = Math.max(notpick, pick);

      map.put(capacity + "" + i, max);

      return max;
  }
}

class HelloWorld {
  static int globalMax = 0;
  public static void main(String[] args) {
    System.out.println("Hello world!");
    int []weights = {10, 20, 30};
    int []values =  {60, 100, 120};

    compute(weights, values, 0, 0, 50);
    System.out.println( globalMax);
  }

  // Solution 2: no return
  static void compute (int []weights, int []values, int i, int value, int w ){
      if(i == weights.length){
        return;
      }

	    if(w < 0){ // we may not need this at all
	      return;
	    }
    

    if(w- weights[i] >= 0){
      globalMax = Math.max(globalMax, value+ values[i]);
      compute (weights, values, i +1,  value + values[i], w- weights[i]);
    }
    globalMax = Math.max(globalMax, value);
    compute (weights, values, i +1,  value , w );
  }
}
