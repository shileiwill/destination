stripe_design.java


https://www.1point3acres.com/bbs/thread-939996-1-1.html
metric 系统，侧重在client push metrics to serv‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌er的情况。很多细节问题。E.g client如何存储metrics，如果app使用这个系统，如何减少耗电量，如何handle app crash的情况等等。
有点像weather monitoring system.

 Mobile integration: 完成一个app， 像一个点单系统，可以把item加入/移除订单。订单完成后需要call backend service来获取discount信息，
 然后利用discount+银行卡信息来进行结账，感觉考察的点跟https://www.1point3acres.com/bbs/thread-908575-1-1.html的bike map很像



第二轮Coding，设计load balancer，不难，地里面经写的不是很细。第一问就是每个request有一个weight，我用PQ存，每次找最小的serve。
第二问是每个request有TTL，商量后不考虑distributed和多线程，我propose lazy delete TTL，
每次有request时候直接brute force PQ里的所有request。follow up了一个我提到的test问题，
我觉得用一个clock最好，test的时候可以把fake clock穿进去，就不用thread.sleep来test了 which is flaky。这轮能感觉面试官挺满意。

第三轮integration，bike map，过程也不错，直接附了我写的答案。之前没练过，基本写出来了。handle_json是例子没用。
第一问写的readJson，第二问sendRequest，第三问sendRequest里面加一点第一问的‍‍‍‍‌‍‍‍‍‍‌‍‍‍‌‌‍到的field继续send。

第四轮moshi debug表演，感觉演的也可以。附件中错误的两行代码在ParserStack里，我注释掉了，可以直接run自己debug试试。

第五轮design ledger，我觉得说的也不错，国人面试官，帮我把控时间，所有问题都接住了，感觉说的不错。但是时间不够，
有些我想到的点没说到。但是看他反应感觉也不错啊。



/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

//      AU: 80
//      US: 140
//      MX: 110
//      SG: 120
//      FR: 70

class Solution {
  static void move(Map<String, Integer> map) {
    // sanity check
    if (map == null || map.isEmpty()) {
      throw new RuntimeException("Invalid input");
    }

    PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
      map.size(),
      new Comparator<Map.Entry<String, Integer>>() {
        // sort by the balance
        public int compare(Map.Entry<String, Integer> account1, Map.Entry<String, Integer> account2) {
          return account1.getValue() - account2.getValue();
        }
      }
      );

    PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
      map.size(),
      new Comparator<Map.Entry<String, Integer>>() {
        // sort by the balance
        public int compare(Map.Entry<String, Integer> account1, Map.Entry<String, Integer> account2) {
          return account2.getValue() - account1.getValue();
        }
      }
      );

      // minHeap: (FR: 70) (AU:80)
      // maxHeap: (US: 140) (SG: 120) (MX: 110) 

      // iterate through the input accounts in map
      for (Map.Entry<String, Integer> entry : map.entrySet()) {
        String accountName = entry.getKey();
        int accountBalance = entry.getValue();

        if (accountBalance < 0) {
          throw new RuntimeException("Invalid balance for account: " + accountName);
        }

        if (accountBalance > 100) {
          maxHeap.offer(entry);
        } else if (accountBalance < 100) {
          minHeap.offer(entry);
        }
        // we ignored the accounts which have 100 balance
      }

      // minHeap: 
      // maxHeap: (US: 110) (MX: 110) 
      while (!minHeap.isEmpty()) {
        if (maxHeap.isEmpty()) {
          throw new RuntimeException("Insufficent fund");
        }

        // (US: 140) (SG : 120)
        Map.Entry<String, Integer> highestAccount = maxHeap.poll();
        // (FR: 70) (AU : 80)
        Map.Entry<String, Integer> lowestAccount = minHeap.poll();

        // check how much I should move 
        // 140, 70 -> 40 30 -> 30
        // amount = 20
        int amount = Math.min(highestAccount.getValue() - 100, 100 - lowestAccount.getValue());

        // update the amount of above accounts
        // from: 140-30 = 110, to: 70 + 30 = 100
        // from: 120-20 = 100, to: 80 + 20 = 100
        int newAmountFrom = highestAccount.getValue() - amount; // 100
        int newAmountTo = lowestAccount.getValue() + amount; // 90

        // 30 dollars from US to FR
        // 20 dollars from SG to AU
        // from: US, to: AU, amount: 20  
        System.out.println("from: " + highestAccount.getKey() + ", to : " + lowestAccount.getKey() + ", amount: " + amount);

        // 110
        if (newAmountFrom > 100) {
          highestAccount.setValue(newAmountFrom);
          maxHeap.offer(highestAccount);
        } // if new amount is 100, then we skip it.
      
        if (newAmountTo < 100) {
          lowestAccount.setValue(newAmountTo);
          minHeap.offer(lowestAccount);
        } // if new amount is 100, no need to put it back.
      }
  }


//      AU: 80
//      US: 140
//      MX: 110
//      SG: 120
//      FR: 70

//      CN: 10

  public static void main(String[] args) {

    Map<String, Integer> map = new HashMap<>();
    map.put("AU", 80);
    map.put("US", 1400);
    map.put("MX", 110);
    map.put("SG", 120);
    map.put("FR", 70);
    map.put("CN", -10);
 
    // 50, 70, 80
    // CN FR = 70
    move(map);
}
}


// At Stripe we keep track of where the money is and move money between bank
// accounts to make sure their balances are not below some threshold. This is for
// operational and regulatory reasons, e.g. we should have enough funds to pay
// out to our users, and we are legally required to separate our users' funds
// from our own. This interview question is a simplified version of a real-world
// problem we have here.
 
// Let's say there are at most 500 bank accounts, some of their balances are
// above 100 and some are below. How do you move money between them so that they
// all have at least 100?
 
// Just to be clear we are not looking for the optimal solution, but a working one.
 
// Example input:
//      AU: 80
//      US: 140
//      MX: 110
//      SG: 120
//      FR: 70
 
// Output:
//      from: US, to: AU, amount: 20  
//      from: US, to: FR, amount: 20
//      from: MX, to: FR, amount: 10

/**
1. Greater than 100:
US:140, SG: 120, MX: 110
Max Heap

2. Smaller than 100:
FR:70, AU:80
Min Heap

Move from US to FR for 30
  - US 110
  - FR 100
1. Greater than 100:
SG: 120, US:110, MX: 110

2. Smaller than 100:
AU:80

Move from SG to AU for 20 
1. Greater than 100:
US:110, MX: 110

2. Smaller than 100:


1. Greater than 100:
SG: 110, US:110, MX: 110

2. Smaller than 100:
AU:80

Move from SG to AU for 10

1. Greater than 100:
US:110, MX: 110

2. Smaller than 100:
AU:90

Move from US to AU for 10
1. Greater than 100:
MX: 110

2. Smaller than 100:
None
 */



