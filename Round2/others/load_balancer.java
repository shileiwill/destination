load_balancer.java


import java.util.*;


/**
Description
Implement a load balancer for web servers. It provide the following functionality:

Add a new server to the cluster => add(server_id).
Remove a bad server from the cluster => remove(server_id).
Pick a server in the cluster randomly with equal probability => pick().
At beginning, the cluster is empty. When pick() is called you need to randomly return a server_id in the cluster.

1 - you have large pool of machines 10K in different countries
2 - pick machine in round robin order
3 - machine can go down and should not be retrived in 12 hours
4 - write web application (backend) that supports the following APIs: GetMachine|GET - returns IP adddress of the selected machine and POST|ReportError(ip address) - marks machine X with ip address as invalid for 12 hours

https://github.com/geemaple/leetcode/blob/master/lintcode/526.load-balancer.py
**/

public class Main {
    public static void main(String[] args) {
      // System.out.println("Hello world!");
      // Main lb = new Main();
      // lb.add(1);
      // lb.add(2);
      // lb.add(3);
      // System.out.println(lb.pickRR());
      // System.out.println(lb.pickRR());
      // System.out.println(lb.pickRR());
      // System.out.println(lb.pickRR());
      // lb.remove(1);
      // System.out.println(lb.pickRR());
      // System.out.println(lb.pickRR());
      // System.out.println(lb.pickRR());

      System.out.println("Hello world!");
      Main lb = new Main();
      lb.add(1);
      lb.add(2);
      lb.add(3);
      System.out.println(lb.pickRRWithBlock(8));
      System.out.println(lb.pickRRWithBlock(9));
      System.out.println(lb.pickRRWithBlock(10));
      System.out.println(lb.pickRRWithBlock(11));
      lb.block(1);
      System.out.println(lb.pickRRWithBlock(13));
      System.out.println(lb.pickRRWithBlock(14));
      System.out.println(lb.pickRRWithBlock(15));
      System.out.println(lb.pickRRWithBlock(23));
      System.out.println(lb.pickRRWithBlock(24));
      System.out.println(lb.pickRRWithBlock(25));
      System.out.println(lb.pickRRWithBlock(26));
      System.out.println(lb.pickRRWithBlock(27));
      System.out.println(lb.pickRRWithBlock(28));
    }

    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
  
    public Main() {
      // do intialization if necessary
    }

    /*
     * @param server_id: add a new server to the cluster
     * @return: nothing
     */
    public void add(int server_id) {
      // write your code here
      list.add(server_id);
      map.put(server_id, list.size() - 1);
    }

    /*
     * @param server_id: server_id remove a bad server from the cluster
     * @return: nothing
     */
    public void remove(int server_id) {
      // write your code here
      if (!map.containsKey(server_id)) {
        return;
      }
      int index = map.get(server_id);
      map.remove(server_id);

      int lastServerIndex = list.size() - 1;
      int lastServerId = list.get(lastServerIndex);
      System.out.println("lastServerIndex: " + lastServerIndex);

      list.set(index, lastServerId);
      map.put(lastServerId, index);

      System.out.println("lastServerId: " + lastServerId);
      System.out.println("index: " + index);

      // remove last one
      list.remove(lastServerIndex);

      System.out.println(list);
    }

    Map<Integer, Integer> blockMap = new HashMap<>();
    public void block(int serverId) {
      // current timestamp
      blockMap.put(serverId, 12);
    }

    /*
     * @return: pick a server in the cluster randomly with equal probability
     */
    public int pick() {
      // write your code here
      // [0,1) * 2 = [0,2)
      int ranIndex = (int)(Math.random() * list.size());
      System.out.println("ranIndex: " + ranIndex);
      return list.get(ranIndex);
    }

    int count = 0;
    public int pickRR() {
      // write your code here
      int curIndex = count % list.size();
      count = count + 1;
      System.out.println("curIndex: " + curIndex);
      return list.get(curIndex);
    }

    // curHour should be really the current hour, no need to pass in.
    public int pickRRWithBlock(int curHour) {
      while (true) {
        int curIndex = count % list.size();
        count = count + 1;
        System.out.println("curHour: " + curHour + ": curIndex: " + curIndex);
        int curServer = list.get(curIndex);
        
        if (!blockMap.containsKey(curServer)) {
          return curServer;
        } else if (curHour - blockMap.get(curServer) > 12) {
          blockMap.remove(curServer);
          return curServer;
        } else {
          continue;
        }
      }
    }
}