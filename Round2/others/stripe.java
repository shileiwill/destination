stripe.java

// https://www.1point3acres.com/bbs/thread-793699-1-1.html
import java.util.*;
class Main {
  public static void main(String[] args) {
    
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    map.put("a", Arrays.asList(new String[]{"c", "d"}));
    map.put("c", Arrays.asList(new String[]{"b"}));
    map.put("d", Arrays.asList(new String[]{"c", "e"}));

    boolean res = hasMutualRank(map, "a", 1);
    System.out.println(res);
  }

  static boolean hasMutualRank(Map<String, List<String>> map, String a, int rank) {
    List<String> list1 = map.get(a);
    System.out.println(list1);
    if (list1.size() <= rank) {
      return false;
    }
    String firstUser = list1.get(rank);
    List<String> list2 = map.get(firstUser);
    System.out.println(list2);
    if (list2.size() <= rank) {
      return false;
    }
    String firstUser2 = list2.get(rank);
    // String rankMinus1 = list2.get(rank - 1)
  
    return a.equals(firstUser2);

//     Part 4
// Anti-ranking. ‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍比如 {a: [b,c,d], b: [d,c,a]}，b是a的第一rank，同时a是b的倒数第一rank，这个才能算作hasAntiMutualRank。Follow-up是如果这个anti-rank也是像part 3是浮动的怎么办
  }

  static boolean hasMutualRank4(Map<String, List<String>> map, String a, int rank, int k) {
    List<String> list1 = map.get(a);
    String b = list1.get(rank);
  }
  
  /**
     * 如果rank有浮动，rank和rank - 1都可以算作hasMutualRank，输出所有有mutual rank的user pair
     * 或者理解为每个人的option list位置是可以swap的， 就是相邻两个option 可以换位置， 但是只是一次机会
  */
  static boolean hasMutualRank3(Map<String, List<String>> map, String a, int rank, int range) {
    // int aRange = Math.max(rank - range, 0);
    int aRange = Math.min(rank + range + 1, map.get(a).size());

    for (int i = rank; i < aRange; i++) {
      String pair = map.get(a).get(i);

      if (!map.containsKey(pair)) {
        continue;
      }

      int bRange = Math.min(rank + range + 1, map.get(pair).size());
      for (int j = rank; j < bRange; j++) {
        if (map.get(pair).get(j).equals(a)) {
          System.out.println(a + ":" + pair);
        }
      }
    }
  }

  static boolean hasMutualRankFloating(Map<String, List<String>> map, String a, int rank, int range) {
    int start = Math.max(0, rank - range - 1);

    for (int i = start; i < rank; i++) {
      String aFriend = map.get(a).get(i);

      if (!map.containsKey(aFriend)) {
        continue;
      }

      for (int j = start; j < rank; j++) {
        if (map.get(aFriend).get(j).equals(a)) {
          println(a + ":" + b);
        }
      }
    }
  }
    
  }
}

/**
给一个usermap比如{a:[cd]c:[a]}key是不同的uservalue是其他user的ranking然后实现hasMutualRank(a)如果a的第一rankuser恰好也把a作为第一rank那么returntrue
*/

/**
Coding:Design an API that allows users to execute_endpoint(customer tm)The system should not allow user to enter too many data points in a short time say no more than 5 in 2 seconds

  Rate Limiter
  https://leetcode.com/discuss/interview-question/object-oriented-design/1713795/API-Rate-Limiter

  HTTP and Mutual Rank
  https://github.com/mononokehime14/Leetcode/blob/9a154ffba8405f78e6b1218e01a323f813fd34ef/leetcode/interview/StripeVOPreparation.java

  Panelty
  https://leetcode.com/problems/minimum-penalty-for-a-shop/
  
  Account balancing
  https://leetcode.com/problems/optimal-account-balancing/
*/


// Design an API that allows users to execute_endpoint(customer, tm). The system should not allow user to enter too many data points in a short time, say no more than 5 in 2 seconds.


import java.util.*;
import java.util.concurrent.*;

class Main {
    public static void main(String[] args) {
        Main rateLimiterService = new Main();
        int limit = 5; // 5 requests per minute
        RateLimit rateLimit = new RateLimit(limit);
        new RateLimitHelper("UserA", rateLimit).start();
        new RateLimitHelper("userB", rateLimit).start();
    }
}

class RateLimit {
  int rateLimit;
  Map<String, LinkedList<Integer>> userRequestMap = new ConcurrentHashMap<>(); // synchronized, thread-safe

  public RateLimit(int limit) {
    this.rateLimit = limit;
  }

  public synchronized boolean hit(String user, int ts) {
    if (!userRequestMap.containsKey(user)) {
      LinkedList<Integer> requests = new LinkedList<>();
      requests.add(ts);
      userRequestMap.put(user, requests);
      return true;
    } else {
      if (userRequestMap.get(user).size() < rateLimit) {
        LinkedList<Integer> requests = userRequestMap.get(user);
        requests.add(ts);
        userRequestMap.put(user, requests);
        System.out.println("New user " + user + "added");
        return true;
      } else {
        // too many
        boolean didRemoval = false;
        for (int i = 0; i < userRequestMap.get(user).size(); i++) {
          int diff = ts - userRequestMap.get(user).get(i);
          if (diff >= 60) {
            // this request is older than 60 seconds, safe to remove it
            userRequestMap.get(user).remove(i);
            didRemoval = true;
          } else {
            break;
          }
        }

        if (didRemoval) {
          LinkedList<Integer> requests = userRequestMap.get(user);
          requests.add(ts);
          userRequestMap.put(user, requests);
          return true;
        }

        return false;
      }
    }
  }

}

class RateLimitHelper extends Thread {

    RateLimit rateLimit;

    public RateLimitHelper(String user, RateLimit rateLimitService) {
        super(user);
        this.rateLimit = rateLimitService;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 65; i++) {
            System.out.println("Thread Name - " + getName() + ", Time - " + i + ", rate limit: " + hit(getName(), i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("DONE! " + getName());
    }

    public boolean hit(String user, int ts) {
        return rateLimit.hit(user, ts);
    }
}
