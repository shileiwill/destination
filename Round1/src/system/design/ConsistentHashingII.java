package system.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*
在 Consistent Hashing I 中我们介绍了一个比较简单的一致性哈希算法，这个简单的版本有两个缺陷：
增加一台机器之后，数据全部从其中一台机器过来，这一台机器的读负载过大，对正常的服务会造成影响。
当增加到3台机器的时候，每台服务器的负载量不均衡，为1:1:2。
为了解决这个问题，引入了 micro-shards 的概念，一个更好的算法是这样：
将 360° 的区间分得更细。从 0~359 变为一个 0 ~ n-1 的区间，将这个区间首尾相接，连成一个圆。
当加入一台新的机器的时候，随机选择在圆周中撒 k 个点，代表这台机器的 k 个 micro-shards。
每个数据在圆周上也对应一个点，这个点通过一个 hash function 来计算。
一个数据该属于那台机器负责管理，是按照该数据对应的圆周上的点在圆上顺时针碰到的第一个 micro-shard 点所属的机器来决定。
n 和 k在真实的 NoSQL 数据库中一般是 2^64 和 1000。
请实现这种引入了 micro-shard 的 consistent hashing 的方法。主要实现如下的三个函数：
create(int n, int k)
addMachine(int machine_id) // add a new machine, return a list of shard ids.
getMachineIdByHashCode(int hashcode) // return machine id
----------------------
 Notice
当 n 为 2^64 时，在这个区间内随机基本不会出现重复。
但是为了方便测试您程序的正确性，n 在数据中可能会比较小，所以你必须保证你生成的 k 个随机数不会出现重复。
LintCode并不会判断你addMachine的返回结果的正确性（因为是随机数），只会根据您返回的addMachine的结果判断你getMachineIdByHashCode结果的正确性。
Example
create(100, 3)
addMachine(1)
>> [3, 41, 90]  => 三个随机数
getMachineIdByHashCode(4)
>> 1
addMachine(2)
>> [11, 55, 83]
getMachineIdByHashCode(61)
>> 2
getMachineIdByHashCode(91)
>> 1
 * create(100, 3)
addMachine(1)
getMachineIdByHashCode(4)
addMachine(2)
getMachineIdByHashCode(61)
getMachineIdByHashCode(91)

你这么想，你在一个圆上，随机撒1000个红色的点，随机撒1000个蓝色的点，再随机撒1000个黑色的点。
现在再随便在这个圆上随机挑100个点，是不是有很大的概率拿到比较均匀分配的红蓝黑？

如果一台机器挂了，对应的是 1000 个 virtual node 挂了，就从圆上撤出。然后本来需要分配到这些virtual node的数据，就会顺时针去找下一个，然后就会找到我们说的 replica。
（因为之前就是顺时针找3个virtual node）

因为有replica在，所以你数据全都丢掉的概率就很小。

记住一句话，系统设计不是要让你的系统100%不会出问题，而是要尽可能的降低出问题的概率和出问题之后的影响。你可以说这种方法仍然是有很极端的情况会发生的，但是这个概率及其小。

先有个概念，在复杂版本中，把机器和数据都看做一个点。
在简单版本中，原来一个圆不是分360份么，现在是分2^64份，然后每加入一个机器，随机在这个圆上找1000个随机点（不重复的micro-shards），这样这个机器就加入进去了（机器也变成点了）。
每个数据同时在这个圆上也有一个点，通过hashcode算出来（0 - 2^64中的一个数），得到这个hashcode之后，我们以这个点为起点，在圆上顺时针找到第一个随机点（micro-shards）所属的机器，存进去。

挂了的问题，如果一个机器挂了，那么受影响的是从这个机器开始，逆时针一直找，直到上一个机器之间的这些点，那么我们把这些点存到挂了的机器开始，顺时针找到的下一个机器上。

replica 就是存在顺时针的下两个virtual node里。所以步骤就是 hash 得到一个virtual node，然偶顺时针取两个 virtual nodes，然后存上去。
 * */

public class ConsistentHashingII {
    public int n, k;
    public Set<Integer> ids = null; // 已经有机器的一堆point, 不能再使用了
    public Map<Integer, List<Integer>> machines = null; // 机器的ID 对应的在环上的Points

    // @param n a positive integer 环上的区间（Point）个数
    // @param k a positive integer 每个机器需要多少个Points (replica)
    // @return a Solution object
    public static ConsistentHashingII create(int n, int k) {
        // Write your code here
    	ConsistentHashingII solution = new ConsistentHashingII();
        solution.n = n;
        solution.k = k;
        solution.ids = new TreeSet<Integer>();
        solution.machines = new HashMap<Integer, List<Integer>>();
        return solution;
    }

    // @param machine_id an integer
    // @return a list of shard ids
    public List<Integer> addMachine(int machine_id) {
        Random ra =new Random();
        List<Integer> random_nums = new ArrayList<Integer>();
        for (int i = 0; i < k; ++i) {
            int index = ra.nextInt(n);
            while (ids.contains(index))
                index = ra.nextInt(n);
            ids.add(index);
            random_nums.add(index);
        }

        Collections.sort(random_nums);
        machines.put(machine_id, random_nums);
        return random_nums;
    }

    // @param hashcode an integer
    // @return a machine id
    public int getMachineIdByHashCode(int hashcode) {
        int distance = n + 1;
        int machineId = 0;
        
        for (Map.Entry<Integer, List<Integer>> entry : machines.entrySet()) {
            int curMachineId = entry.getKey();
            List<Integer> curPoints = entry.getValue();
            
            for (Integer point : curPoints) {
                int curDistance = point - hashcode;
                if (curDistance < 0) { // Circle
                	curDistance += n;
                }
                
                if (curDistance < distance) { // Find a closer one
                    distance = curDistance;
                    machineId = curMachineId;
                }
            }
        }
        
        return machineId;
    }
}

// Use TreeMap
class SolutionWithTreeMap {  
	  
    private TreeMap<Integer, Integer> treeMap = new TreeMap<>();  
      
    private int[] arr;  
    private int size = 0;  
    private int k;  
      
    public SolutionWithTreeMap(int n, int k) {  
        this.arr = new int[n];  
        for(int i = 0; i < n; i++) {
        	this.arr[i] = i; // 先给个初始值
        }
        
        Random ran = new Random();  
        for(int i = 0; i < n; i++) {   // Array中的数字是随机的 from 0 to n - 1
            int j = ran.nextInt(i + 1); 
            
            int temp = arr[i];  
            arr[i] = arr[j];  
            arr[j] = temp;  
        }
        
        this.k = k;  
    }  
      
    // @param n a positive integer  
    // @param k a positive integer  
    // @return a Solution object  
    public static SolutionWithTreeMap create(int n, int k) {  
        // Write your code here  
        return new SolutionWithTreeMap(n, k);  
    }  
  
    // @param machine_id an integer  
    // @return a list of shard ids  
    public List<Integer> addMachine(int machine_id) {  
        List<Integer> ids = new ArrayList<>();  
        for(int i = 0; i < this.k; i++) {  
            int id = this.arr[size++ % this.arr.length];  
            ids.add(id);  
            this.treeMap.put(id, machine_id);  
        }  
        
        return ids;  
    }  
  
    // @param hashcode an integer  
    // @return a machine id  
    public int getMachineIdByHashCode(int hashcode) {  
        if (treeMap.isEmpty()) return 0;  
        Integer ceiling = treeMap.ceilingKey(hashcode); // Ceiling, floor 都行，顺时针 逆时针的区别
        
        if (ceiling != null) {
        	return treeMap.get(ceiling);
        }
        
        return treeMap.get(treeMap.firstKey()); // 环
    }  
}  
