1.

2017 1.5 Onsite

2. 印度小哥 Coding. Merge Intervals 写两个函数 addInterval, getTotalBusyTime。写出两种不同的实现 分析trade off(基本就是根据两个函数的调用频率决定)
(1) LinkedList 插入使得每次插入后start保持有序并保持所有的节点都是disjointed 同时计算totalbusy。 O(N)的add时间和O(1)get时间
(2) Binary search tree. 也就是map，treemap这种。 保持插入后有序，O(logN) add O(N) get时间
follow up 如果需要remove interval 用哪种方式？
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=144989&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// time complexity: call O(n), get O(1)
class MergeIntervals {
    Node head = new Node(0, 0);
    int totalLength = 0;
    public void add(int start, int end) {
        if (start >= end) {
            return;
        }
        Node mover = head.next;
        Node prev = head;
        while (mover != null) {
            if (end < mover.start) {
                Node node = new Node(start, end);
                mover.pre.next = node;
                node.pre = mover.pre;
                node.next = mover;
                mover.pre = node;
                totalLength += end - start;
                break;
            }
            else if (start > mover.end) {
                prev = mover;
                mover = mover.next;
            }
            else {
                totalLength -= mover.end - mover.start;
                start = Math.min(start, mover.start);
                end = Math.max(end, mover.end);
                Node next = mover.next;
                mover.pre.next = mover.next;
                if (mover.next != null) {
                    mover.next.pre = mover.pre;
                }
                mover.next = null;
                mover.pre = null;
                mover = next;
            }
        }
        if (mover == null) {
            totalLength += end - start;
            prev.next = new Node(start, end);
            prev.next.pre = prev;
        }
    }

    public int getTotalLength() {
        return totalLength;
    }

    class Node {
        Node pre = null;
        Node next = null;
        int start;
        int end;
        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

// time complexity: call O(1), get O(nlgn)
class MergeIntervalWithLessGetCall {
    List<Interval> intervals = new ArrayList<>();
    public void add(int start, int end) {
        if (start >= end) {
            return;
        }
        intervals.add(new Interval(start, end));
    }

    public int getTotalLength() {
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval inter1, Interval inter2) {
                return inter1.start - inter2.start;
            }
        });
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        int totalLen = 0;
        for (Interval inter : intervals) {
            if (end >= inter.start) {
                end = Math.max(inter.end, end);
            }
            else {
                totalLen += end - start;
                end = inter.end;
                start = inter.start;
            }
        }
        totalLen += end - start;
        return totalLen;
    }
}


3.欧洲小哥 Sys Design，设计一个系统 收集很多服务器上不同metrics的数据 在前端做任何维度的展示(不同时间段,不同的metric, 以及sum avg等)
历史数据比较大的话怎么搞省数据库空间? （感觉这轮稍难）

4.中国小哥带吃饭，老乡见老乡，两眼泪汪汪. From 1point 3acres bbs

5. 白人小哥 Coding. house robber. Max Points on a line.

6. 中国小哥 Sys design Tiny URL. 如何做load balance. 如何做主从备份。

7. 德国小哥 Sys design and Coding. addData(int d) getAvgofLast1Min getAvgofLast7Min getAvgofLast25Min getAvgofLast1Hour

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218632&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

2.

1. 给很多点的坐标，求距离某一个点最近的k个点。. 
hr host来晚了，只做了一道题。国人面试官很nice，用中文跟我说没关系，是hr来晚了。

public class Solution {
    // get the gcd and get the simplest y/x
    // then use this as the slope as a string
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<String, Integer> slopeCount = new HashMap<>();
            int samePoint = 0;
            int sameX = 1;
            for (int j = 0; j < points.length; j++) {
                if (j == i) {
                    continue;
                }
                if (points[j].y == points[i].y && points[j].x == points[i].x) {
                    samePoint++;
                }
                if (points[j].x == points[i].x) {
                    sameX++;
                    continue;
                }
                int y = points[j].y - points[i].y;
                int x = points[j].x - points[i].x;
                boolean sign = y * x < 0 ? false : true;
                x = Math.abs(x);
                y = Math.abs(y);
                int gcd = getGCD(x, y);
                if (gcd != 0) {
                    x /= gcd;
                    y /= gcd;
                }
                String slope = (sign ? "+" : "-") + Integer.toString(y) + "/" + Integer.toString(x);
                if (slopeCount.containsKey(slope)) {
                    slopeCount.put(slope, slopeCount.get(slope) + 1);
                }
                else {
                    slopeCount.put(slope, 2);
                }
                max = Math.max(max, slopeCount.get(slope) + samePoint);
            }
            max = Math.max(max, sameX);
        }
        return max;
    }
    private int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }
}

class NearestKPoints {
    public List<Point> findNearestPoints(Point point, int k, Point[] points) {
        List<Point> result = new ArrayList<>();
        if (k <= 0) {
            return result;
        }
        PriorityQueue<Point> maxDistance = new PriorityQueue<>(k, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int distance1 = distance(p1, point);
                int distance2 = distance(p2, point);
                return distance2 - distance1;
            }
        });
        for (Point poi : points) {
            if (maxDistance.size() < k) {
                maxDistance.add(poi);
                continue;
            }
            int curDis = distance(poi, point);
            int maxDis = distance(maxDistance.peek(), point);
            if (curDis < maxDis) {
                maxDistance.poll();
                maxDistance.add(poi);
            }
        }
        while (!maxDistance.isEmpty()) {
            result.add(maxDistance.poll());
        }
        return result;
    }

    private int distance(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }
}

class NearestKPoint {
    public Point findNearestKthPoint(Point[] points, int k) {
        if (k > points.length || k == 0) {
            throw new IllegalArgumentException("K's value is illegal\n");
        }
        return quickSelect(points, 0, points.length - 1, k - 1);
    }


    private Point quickSelect(Point[] points, int left, int right, int k) {
        Random rand = new Random();
        int pivotIndex = 0;
        if (right != 0) {
            pivotIndex = rand.nextInt(right) % (right - left + 1) + left;
        }
        pivotIndex = partition(pivotIndex, points, left, right);
        if (pivotIndex == k) {
            return points[k];
        }
        else if (pivotIndex > k) {
            return quickSelect(points, left, pivotIndex - 1, k);
        }
        else {
            return quickSelect(points, pivotIndex + 1, right, k);
        }
    }

    private int partition(int pivotIndex, Point[] points, int left, int right) {
        int i = left;
        int j = right;
        Point temp = points[pivotIndex];
        int tempDistance = distance(temp);
        swap(points, left, pivotIndex);
        while (i < j) {
            while (j > i && distance(points[j]) >= tempDistance) {
                j--;
            }
            points[i] = points[j];
            while (j > i && distance(points[i]) <= tempDistance) {
                i++;
            }
            points[j] = points[i];
        }
        points[i] = temp;
        return i;
    }

    private int distance(Point point) {
        return point.x * point.x + point.y * point.y;
    }

    private void swap (Point[] points, int index1, int index2) {
        Point temp = points[index1];
        points[index1] = points[index2];
        points[index2] = temp;
    }
}

2.1 LC40. Combination Sum II。. more info on 1point3acres.com
2.2 LC160. Intersection of Two Linked Lists。要考虑有环的情况。
这一轮的国人面试官也很nice。

public class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        helper(result, new ArrayList<Integer>(), 0, target, 0, candidates);
        return result;
    }
    
    private void helper(List<List<Integer>> result, List<Integer> subSum, int curSum, int target, int pos, int[] nums) {
        if (curSum == target) {
            result.add(new ArrayList<Integer>(subSum));
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            if (curSum + nums[i] > target || (i != pos && nums[i] == nums[i - 1])) {
                continue;
            }
            curSum += nums[i];
            subSum.add(nums[i]);
            helper(result, subSum, curSum, target, i + 1, nums);
            curSum -= nums[i];
            subSum.remove(subSum.size() - 1);
        }
    }
}

// follow up:
// 两个没有环，不想交
// 两个没有环，相交
// 两个有环，不想交
// 两个有环，相交
// when have cycle, first find the start of the cycle of each list
// it is possible that the intersection start before the cycle, at this situation, 
// try to do it as in the first question, if failed, then they might start in cycle
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = length(headA);
        int lenB = length(headB);
        while (lenA > lenB) {
            headA = headA.next;
            lenA--;
        }
        while (lenB > lenA) {
            headB = headB.next;
            lenB--;
        }
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }
    
    private int length(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }
}

class IntersectionOfList {
    public ListNode intersection(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode cycle1 = detectCycle(head1);
        ListNode cycle2 = detectCycle(head2);
        // one has cycle but another one does not, then no intersection
        if ((cycle1 == null && cycle2 != null) || (cycle1 != null && cycle2 == null)) {
            return null;
        }
        // if both no cirlce, then cycle is null and we check the length to the end
        // else we check the length before circle start point
        int len1 = checkLength(head1, cycle1);
        int len2 = checkLength(head2, cycle2);
        // check if before circle start point there is intersection
        while (len1 > len2) {
            head1 = head1.next;
            len1--;
        }
        while (len2 > len1) {
            head2 = head2.next;
            len2--;
        }
        for (int i = 0; i < len1; i++) {
            if (head1 == head2) {
                return head1;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        // if both no circle, then no intersection for now
        if (cycle1 == null && cycle2 == null) {
            return null;
        }
        // check if the intersection is in the circle
        if (cycle1 == cycle2) {
            return cycle1;
        }
        ListNode mover = cycle1.next;
        while (mover != cycle1) {
            if (mover == cycle2) {
                return mover;
            }
            mover = mover.next;
        }
        return null;

    }

    private int checkLength(ListNode head, ListNode end) {
        int len = 0;
        while (head != end) {
            head = head.next;
            len++;
        }
        return len;
    }

// when fast and slow meet in the circle
// the distance of head to the begin of cirle is A,
// the meet point to the begin of the cirle is B,
// N means the length of the cirle, we have A + B + N(slow take) = 2A + 2B(fast take)
// thus N = A + B, so the slow will take A steps to reach the begin of cirle again
// we can use another mover from head to move one step each time, so they will meet at the begin of the cirle
    private ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode slower = head;
                while (slow != slower) {
                    slow = slow.next;
                    slower = slower.next;
                }
                return slow;
            }
        }
        return null;
    }
}

3. 午饭

4. 经理，纯行为问题。有一个问题是如果你来lead一个项目开发新产品，你会怎么做。还有以前项目中遇到的tradeoff之类。

5.1 设计word里面提示错别字的功能。follow up是可以加什么advanced features。
5.2 top k visited URLs in last 24 hr/1 hr/5min。最后有个follow up是怎么保证对全球各地用户的响应速度。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218646&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


3.
maximum subarray / two sum interface
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218504&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
// 2 sum interface
public class TwoSum {
    // when more add less find, where find takes O(n)
    HashMap<Integer, Integer> content = new HashMap<>();
	public void add(int number) {
	    if (content.containsKey(number)) {
	        content.put(number, 2);
	    }
	    else {
	        content.put(number, 1);
	    }
	}

	public boolean find(int value) {
	    for (int number : content.keySet()) {
	        int other = value - number;
	        if (content.containsKey(other) && (number != other || content.get(other) == 2)) {
	            return true;
	        }
	    }
	    return false;
	}

    // when more find less add, where add takes O(n)
    Set<Integer> sum = new HashSet<>();
    Set<Integer> numbs = new HashSet<>();
    public void add(int number) {
        if (numbs.contains(number)) {
            sum.add(number * 2);
        }
        else {
            for (int other : numbs) {
                sum.add(other + number);    
            }
            numbs.add(number);
        }
    }
    
    public boolean find(int value) {
        return sum.contains(value);
    }
    
}


4.
刚刚面完L家电面，感觉已跪。。。准备了基本上所有的面经，只有这一道没有准备有没有。。。 多线程的题目。。虽然感觉不难但是真真没有多线程
经验，就写了一道题目，面试官说准备了两道。。。是地理出现过的。。 面的是System & infrastucture 组，貌似这组要求挺高的。
题目：
Blocking Bounded Queue. 
实现一个线程安全的put和get。
我说我用C++, 但是面试官给我这个接口。。写着写着感觉这不应该是Java 的接口。。（PS：临时换了一个面试官，本以为烙印换成老美会好一些，但是感觉好像并没有。）
/*
*   threadSafe bounded blocking queue implementation.
*   Expected to be used in a Producer->Consumer pattern
*/
public interface BlockingBoundedQueue
{
  /*. 
   * Sets the capacity of the buffer. Can be called only once.. more info on 1point3acres.com
   * If called more than once or if the passed capacity is <= 0,
   * throw an exception.
   */
  public void init(int capacity) throws Exception; 
  /*
   * Get the next item from the queue
   * throws Exception if not initialized
   */
  public Object get() throws Exception;

  /*
   * Put the item to the tail of the queue.
   * throws Exception if not initialized
   */
  public void put(Object obj) throws Exception;
}
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218470&page=1#pid2718204

5.
 power(a, b) 这个是leetcode原题， 一遍bug free写完了也没问followup
2， 版上常见的那个interval题目， 要实现一个接口，实现addInterval和getTotalCoverage两个函数。
 基本算是leetcode的两个interval题目的变种， 第一遍bug free写了一个add O(n)和get O(1)的方法， 
 然后交流了一下之后做了些小优化。 followup是问有没有让add方法更efficient的写法, 然后我说了下add O(1),
  get O(nlogn)的方法， 然后又讨论了一些优化的问题。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198750&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
// power a of b
public class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double result = myPow(x, n / 2);
        if (n % 2 == 0) {
            return result * result;
        }
        else if (n < 0) {
            return result * result / x;
        }
        return result * result * x;
        
        // iterative way
        // everytime we square x so we need to find the square root of the n
        // convert n into binary, when the last digit is 1, which means current x should be in the answer
        // if n is an odd, we times this x into the anwser
        if(n==0) return 1;
        if(n<0) {
            n = -n;
            x = 1/x;
        }
        double ans = 1;
        while(n>0){
            if((n&1) == 1) ans *= x;
            x *= x;
            n >>= 1;
        }
        return ans;
    }
}



6.
word distance 2
我太老实，告诉面试官这题很眼熟。
我问面试官要换题吗？他说就这题吧。. from: 1point3acres.com/bbs 
然后follow up没答出。. more info on 1point3acres.com
follow up是，要查3个单词的word distance。
我没想出O（n）的解法，只给出了n^2的
最后面试官提示我用Manhattan距离，然后因为时间关系写伪代码就好了。
因为不会用sliding window，最后写不出来。
面试官冷冷地说了一句，谢谢你的时间就立刻挂了电话。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218458&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        int dis = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            }
            else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                dis = Math.min(dis, Math.abs(index1 - index2));
            }
        }
        return dis;
    }
}

public class WordDistance2 {
    HashMap<String, List<Integer>> map = new HashMap<>();
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<>());
            }
            map.get(words[i]).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        if (!map.containsKey(word1) && !map.containsKey(word2)) {
            return -1;
        }
        int dis = Integer.MAX_VALUE;
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int poi1 = list1.get(index1);
            int poi2 = list2.get(index2);
            if (poi1 > poi2) {
                dis = Math.min(dis, poi1 - poi2);
                index2++;
            }
            else {
                dis = Math.min(dis, poi2 - poi1);
                index1++;
            }
        }
        return dis;
    }
}


class WordDistance2.5 {
    HashMap<String, List<Integer>> wordIndex = new HashMap<>();
    public WordDistance(String[] words) {
        for (int i = 0; i < words.length; i++) {
            if (!wordIndex.containsKey(words[i])) {
                wordIndex.put(words[i], new ArrayList<>());
            }
            wordIndex.get(words[i]).add(i);
        }
    }

    public int minDistance(String word1, String word2, String word3) {
        if (!wordIndex.containsKey(word1) || !wordIndex.containsKey(word2) || !wordIndex.containsKey(word3)) {
            return -1;
        }
        List<Integer> indexs1 = wordIndex.get(word1);
        List<Integer> indexs2 = wordIndex.get(word2);
        List<Integer> indexs3 = wordIndex.get(word3);
        int poi1 = 0;
        int poi2 = 0;
        int poi3 = 0;
        int minDis = Integer.MAX_VALUE;
        while (poi1 < indexs1.size() && poi2 < indexs2.size() && poi3 < indexs3.size()) {
            int index1 = indexs1.get(poi1);
            int index2 = indexs2.get(poi2);
            int index3 = indexs3.get(poi3);
            if (index1 < index2 && index1 < index3) {
                int totalDis = index2 - index1 + index3 - index1 + Math.abs(index2 - index3);
                minDis = Math.min(totalDis, minDis);
                poi1++;
            }
            else if (index2 < index1 && index2 < index3) {
                int totalDis = index3 - index2 + index1 - index2 + Math.abs(index1 - index3);
                minDis = Math.min(minDis, totalDis);
                poi2++;
            }
            else {
                int totalDis = index2 - index3 + index1 - index2 + Math.abs(index2 - index1);
                minDis = Math.min(minDis, totalDis);
                poi3++;
            }
        }
        return minDis;
    }
}

public class Solution {
    public int shortestWordDistance3(String[] words, String word1, String word2) {
        int index1 = -1;
        int index2 = -1;
        boolean isSame = word1.equals(word2);
        int dis = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (isSame) {
                    index2 = index1;
                    index1 = i;
                }
                else {
                    index1 = i;
                }
            }
            else if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                dis = Math.min(dis, Math.abs(index1 - index2));
            }
        }
        return dis;
    }
}

7.
电面：
一共45分钟，就问了3道题：
1. 找出一个数组里面只出现过一次的数字；
2. 合并两个有序数组；
3. 合并K个有序数组。

Onsite:. 1point3acres.com/bbs
1. coding，实现字符串替换函数；leetcode 57 insert-interval，面试官说不需要维护区间有序，只需返回interval总长度。. Waral 鍗氬鏈夋洿澶氭枃绔�,
2. system design, 设计一个系统来监控各个应用以及服务器产生的异常。
3. coding，给定某应用的默认配置和期望配置，合并这两个配置文件，期望配置可以覆盖默认配置，如果期望配置里面没有的就使用默认配置（
其实就是合并两个字典）；follow-up是如果配置文件里面的变量可以相互引用改怎么处理，比如 foo='123' bar=$foo baz=$bar，需要写程序来解析并生成最终配置文件。
4. behavior, 面试官介绍了一下他们的组，正在做的项目，问了我的背景，都做过什么有意思的项目，以后想做manager还是developer， 最后吹了一下linkedin发展前途。.鏈枃鍘熷垱鑷�1point3acres璁哄潧
5. project, 假设两个面试官是新来的组员，要向他们介绍自己目前正在做的项目，这过程中他们会问很多细节上的实现以及能承载多少QPS，怎么计算耗费的资源，如何排查问题等等。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218010&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class Merge {
    public List<Integer> mergeTwoList(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < list1.size() && index2 < list2.size()) {
            int number1 = list1.get(index1);
            int number2 = list2.get(index2);
            if (number1 <= number2) {
                result.add(number1);
                index1++;
            }
            else {
                result.add(number2);
                index2++;
            }
        }
        while (index1 < list1.size()) {
            result.add(list1.get(index1++));
        }
        while (index2 < list2.size()) {
            result.add(list2.get(index2++));
        }
        return result;
    }

    public List<Integer> mergeKList(List<List<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).size() == 0) {
                continue;
            }
            minNumber.add(new Number(input.get(i).get(0), 0, i));
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            result.add(min.value);
            if (min.index < input.get(min.listIndex).size() - 1) {
                minNumber.add(new Number(input.get(min.listIndex).get(min.index + 1), min.index + 1, min.listIndex));
            }
        }
        return result;

    }

    class Number {
        int value;
        int index;
        int listIndex;
        public Number(int value, int index, int listIndex) {
            this.value = value;
            this.index = index;
            this.listIndex = listIndex;
        }
    }

    public List<Integer> mergeKListWithIterator(List<Iterator<Integer>> input) {
        PriorityQueue<Number> minNumber = new PriorityQueue<>(input.size(), new Comparator<Number>() {
            @Override
            public int compare(Number num1, Number num2) {
                return num1.value - num2.value;
            }
        });
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i).hasNext()) {
                minNumber.add(new Number(input.get(i).next(), 0, i));
            }
        }
        while (!minNumber.isEmpty()) {
            Number min = minNumber.poll();
            if (input.get(min.listIndex).hasNext()) {
                minNumber.add(new Number(input.get(min.listIndex).next(), 0, min.listIndex));
            }
            result.add(min.value);
        }
        return result;
    }
}

class FindSingleNumber {
    public List<Integer> find(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> result = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                if (result.contains(num)) {
                    result.remove(num);
                }
            }
            else {
                set.add(num);
                result.add(num);
            }
        }
        return new ArrayList<>(result);
    }
}

class ReplaceString {
    public String replace(String origin, String from, String to) {
        if (origin.length() < from.length() || from.equals(to)) {
            return origin;
        }
        int len = from.length();
        int[] count = new int[26];
        for (char letter : from.toCharArray()) {
            count[letter - 'a']++;
        }
        int[] curCount = new int[26];
        int slow = 0;
        int fast = 0;
        while (fast < origin.length()) {
            if (fast - slow + 1 <= len) {
                curCount[origin.charAt(fast) - 'a']++;
            }
            else {
                boolean isSame = true;
                for (int i = 0; i <= 25; i++) {
                    if (curCount[i] == count[i]) {
                        isSame = isSame && true;
                    }
                    else {
                        isSame = false;
                    }
                }
                if (isSame && origin.substring(slow, fast).equals(from)) {
                    origin = origin.substring(0, slow) + to + origin.substring(fast);
                    Arrays.fill(curCount, 0);
                    fast = slow;
                    continue;
                }
                curCount[origin.charAt(slow) - 'a']--;
                slow++;
                curCount[origin.charAt(fast) - 'a']++;
            }
            fast++;
        }
        return origin;
    }
}



8.

design a system to get top k exceptions/errors within a system for the given period of time.

1. there are large number of servers. more info on 1point3acres.com
2. there are large number of exceptions
3. k is subject to change
4. time period is subject to change
5. granularity of time is minute level
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=218214&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

9.
第一轮电面问了两题.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
1. 写个IsNumber()，不是leetcode上那个hard题，只允许数字、.和-。edge case基本只有跟负号和点有关的。
2. 写two sum的class。follow up是如果存的操作多取的操作少该怎么实现，反过来取的操作多存的操作少又该怎么实现。

第二轮也是两个问题
1. 给一个由0和1构成的矩阵，数有多少个1的group被0包围（电面问的是有多少island，比较形象吧）
2. text justification，airbnb高频面试题，不过input给的是string，处理有点麻烦。

Leetcode 273，要考虑小数

class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean seenNum = false;
        boolean seenDot = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                seenNum = true;
            }
            else if (s.charAt(i) == '.') {
                if (seenDot) {
                    return false;
                }
                seenDot = true;
            }
            else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0)  {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return seenNum;
    }
}

public class Solution {
    private static final int[] X = {0, 0, 1, -1};
    private static final int[] Y = {1, -1, 0, 0};
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    helper(grid, row, col);
                    count++;
                }
            }
        }
        return count;
    }
    // dfs
    private void explore(char[][] grid, int row, int col) {
        grid[row][col] = 'x';
        for (int k = 0; k < 4; k++) {
            int nextX = row + X[k];
            int nextY = col + Y[k];
            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] != '1') {
                continue;
            }
            explore(grid, nextX, nextY);
        }
    }
    // bfs
    private void helper(char[][] grid, int row, int col) {
        Queue<Point> explore = new LinkedList<>();
        grid[row][col] = 'x';
        explore.offer(new Point(row, col));
        while (!explore.isEmpty()) {
            Point curPoi = explore.poll();
            for (int k = 0; k < 4; k++) {
                int nextX = curPoi.x + X[k];
                int nextY = curPoi.y + Y[k];
                if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] != '1') {
                    continue;
                }
                grid[nextX][nextY] = 'x';
                explore.offer(new Point(nextX, nextY));
            }
        }
    }
    class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

class TextJustify {
    // for each line, count the number of words we could have
    // if there is only one or this is the last, put it at left side
    // else, calculate the space we need to put in and there might be 
    // rest space cannot be evenly split into the gap, 
    // if there are, we give every gap one more space.
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int length = words[index].length();
            int last = index + 1;
            while (last < words.length) {
                // pre add one space for each word before it
                if (length + 1 + words[last].length() > maxWidth) {
                    break;
                }
                length += words[last].length() + 1;
                last++;
            }
            int number = last - index;
            StringBuilder line = new StringBuilder();
            if (number == 1 || last == words.length) {
                for (int i = index; i < last; i++) {
                    line.append(words[i] + " ");
                }
                line.deleteCharAt(line.length() - 1);
                for (int i = line.length(); i < maxWidth; i++) {
                    line.append(" ");
                }
            }
            else {
                int space = (maxWidth - length) / (number - 1);
                int rest = (maxWidth - length) % (number - 1);
                int mover = index;
                while (mover < last) {
                    line.append(words[mover++]);
                    if (mover < last) {
                        int actualSpace = space;
                        if (rest > 0) {
                            actualSpace += 1;
                            rest--;
                        }
                        // also put pre add space in, so the actual space should be 
                        // actualSpace + 1
                        for (int i = 0; i <= actualSpace; i++) {
                            line.append(" ");
                        }
                    }
                }
                
            }
            lines.add(line.toString());
            index = last;
        }
        return lines;
    }
}


10.
面试我的是国人大哥，感觉他一开始就准备放水。互相自我介绍后，就让我做题，全程基本都很随意，写完代码跑一下测试用例。下面是我的两道面试题：
1.Returns a^b, as the standard mathematical exponentiation function;
2.Shortest Word Distance II
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215629&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

11.
'电面：实现binary search tree 要求有find insert delete 
'
// when delete try to find the max node in the left subtree
// then delete max node, and replace target with this max node
class BinaryTree {
    TreeNode root = null;
    public BinaryTree() {
    }

    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }
        TreeNode mover = root;
        TreeNode prev = null;
        while (mover != null) {
            if (key < mover.val) {
                prev = mover;
                mover = mover.left;
            }
            else if (key > mover.val) {
                prev = mover;
                mover = mover.right;
            }
            else {
                return;
            }
        }
        if (prev.val < key) {
            prev.right = new TreeNode(key);
        }
        else {
            prev.left = new TreeNode(key);
        }
    }

    public boolean search(int key) {
        if (root == null) {
            return false;
        }
        TreeNode mover = root;
        while (mover != null) {
            if (key < mover.val) {
                mover = mover.left;
            }
            else if (key > mover.val) {
                mover = mover.right;
            }
            else {
                return true;
            }
        }
        return false;
    }
    
    public TreeNode delete(int key) {
        TreeNode[] find = find(root, key);
        if (find[1] == null) {
            return root;
        }
        TreeNode node = deleteHelper(find[1], find[0]);
        if (find[0] == null) {
            return node;
        }
        return root;
    }

    private TreeNode deleteHelper(TreeNode node, TreeNode prev) {
        if (node.left == null) {
            if (prev != null && prev.left == node) {
                prev.left = node.right;
            }
            else if (prev != null && prev.right == node) {
                prev.right = node.right;
            }
            TreeNode result = node.right;
            node.left = null;
            node.right = null;
            return result;
        }
        TreeNode[] max = findMax(node.left, node);
        deleteHelper(max[1], max[0]);
        max[1].left = node.left;
        max[1].right = node.right;
        node.left = null;
        node.right = null;
        if (prev != null && prev.left == node) {
            prev.left = max[1];
        }
        else if (prev != null && prev.right == node) {
            prev.right = max[1];
        }
        return max[1];
    }

    private TreeNode[] findMax(TreeNode root, TreeNode prev) {
        while (root.right != null) {
            prev = root;
            root = root.right;
        }
        return new TreeNode[]{prev, root};
    }

    private TreeNode[] find(TreeNode root, int key) {
        TreeNode prev = null;
        while (root != null && root.val != key) {
            prev = root;
            if (root.val < key) {
                root = root.right;
            }
            else {
                root = root.left;
            }
        }
        return new TreeNode[]{prev, root};
    }
}

onsite：
1 communication 介绍做过的project 然后问有什么难点. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴
2 hiring manager 基本就是聊聊过去的experience
3 design web游戏hangman
4 coding  一个pizza 切n刀 问最多能切几块 注意不一定要所有切线都在同一个交点
              leetcode98 valid binary search tree
              实现一个maxstack 功能有pop peek peekMax popMax, popMax要求O(1) 应该是挂在这上了 最后没想出来popMax O(1)怎么做
5  coding reverse words in a string 和 nestedlist iterator

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=212481&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MaxStackNotEfficent {
    private Node top;
    private List<Node> max;
    public MaxStack() {
        this.max = new ArrayList<>();
        this.top = new Node(0);
    }
    public void push(int val) {
        Node node = new Node(val);
        top.next = node;
        node.prev = top;
        top = node;
        if (max.isEmpty()) {
            max.add(node);
        }
        else {
            int insertPosition = Collections.binarySearch(max, node, new NodeComparator());
            // if not find, return index is -insert - 1
            if (insertPosition < 0) {
                insertPosition = -insertPosition - 1;
            }
            max.add(insertPosition, node);
        }
    }

    public Integer pop() {
        if (top.prev == null) {
            return null;
        }
        Node node = top;
        top = top.prev;
        node.prev = null;
        top.next = null;
        int pos = Collections.binarySearch(max, node, new NodeComparator());
        max.remove(pos);
        return node.val;
    }

    public Integer peek() {
        if (top.prev == null) {
            return null;
        }
        return top.val;
    }

    public Integer peekMax() {
        if (max.isEmpty()) {
            return null;
        }
        return max.get(max.size() - 1).val;
    }

    public Integer popMax() {
        if (max.isEmpty()) {
            return null;
        }
        Node maxNode = max.get(max.size() - 1);
        max.remove(max.size() - 1);
        if (maxNode == top) {
            top = top.prev;
        }
        maxNode.prev.next = maxNode.next;
        if (maxNode.next != null) {
            maxNode.next.prev = maxNode.prev;
        }
        maxNode.prev = null;
        maxNode.next = null;
        return maxNode.val;
    }

    class Node {
        Node prev = null;
        Node next = null;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }

    class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node node1, Node node2) {
            return node1.val - node2.val;
        }
    }
}

class MaxStack {
    private Node top;
    private PriorityQueue<Node> max;
    public MaxStack() {
        this.max = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node2.val - node1.val;
            }
        });
        this.top = new Node(0);
    }
    public void push(int val) {
        Node node = new Node(val);
        top.next = node;
        node.prev = top;
        top = node;
        max.add(node);
    }

    public Integer pop() {
        if (top.prev == null) {
            return null;
        }
        Node node = top;
        top = top.prev;
        node.prev = null;
        top.next = null;
        max.remove(node);
        return node.val;
    }

    public Integer peek() {
        if (top.prev == null) {
            return null;
        }
        return top.val;
    }

    public Integer peekMax() {
        if (max.isEmpty()) {
            return null;
        }
        return max.peek().val;
    }

    public Integer popMax() {
        if (max.isEmpty()) {
            return null;
        }
        Node maxNode = max.poll();
        if (maxNode == top) {
            top = top.prev;
        }
        maxNode.prev.next = maxNode.next;
        if (maxNode.next != null) {
            maxNode.next.prev = maxNode.prev;
        }
        maxNode.prev = null;
        maxNode.next = null;
        return maxNode.val;
    }

    class Node {
        Node prev = null;
        Node next = null;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
}


public class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        toLeft(stack, root);
        boolean first = true;
        int last = 0;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            toLeft(stack, cur.right);
            if (first) {
                last = cur.val;
                first = false;
                continue;
            } 
            if (cur.val <= last) {
                return false;
            }
            last = cur.val;
        }
        return true;
    }
    private void toLeft(Stack<TreeNode> stack, TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
}

12.
第一题leetcode 380，不过是generic type。楼主用了list+map的方法，但是面试官提出来有地方不是O(1), 让我再想办法。笨蛋楼主想了半天想不出来，他也不愿意给任何提示，
就是说不能用list。卡住不动也不是办法，于是楼主就把自己的做法list + map写出来了。 写完了对面说看起来OK, 但是你就是不能用list,怎么办。楼主到最后也没想出来。。. 
第一题磨蹭完还有10分钟，面试官又把leetcode 297贴出来了，说我们没时间了，你把这题idea简单说一下。 楼主刚说完Serialize打算说Deserialize面试官就说时间到了不听了，你问问题吧。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214965&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


// a hashmap to map the value to index
// a hashmap to map the index to node
class RandomSet<T> {
    private HashMap<T, Integer> valueToIndex;
    private HashMap<Integer, T> indexToValue;
    private Random random;
    public RandomSet() {
        this.valueToIndex = new HashMap<>();
        this.indexToValue = new HashMap<>();
        this.random = new Random();
    }

    public boolean add(T value) {
        if (valueToIndex.containsKey(value)) {
            return false;
        }
        int index = valueToIndex.size();
        valueToIndex.put(value, index);
        indexToValue.put(index, value);
        return true;
    }

    public void remove(T value) {
        if (!valueToIndex.containsKey(value)) {
            return;
        }
        // if is the last one
        // directly remove it
        int index = valueToIndex.get(value);
        if (index == valueToIndex.size() - 1) {
            valueToIndex.remove(value);
            indexToValue.remove(index);
        }
        // if not, replace the index of last one with the current value
        // so we dont need to change other value's index
        else {
            int lastIndex = indexToValue.size() - 1;
            T lastOne = indexToValue.get(lastIndex);
            valueToIndex.put(lastOne, index);
            indexToValue.put(index, lastOne);
            valueToIndex.remove(value);
            indexToValue.remove(lastIndex);
        }
    }

    public T randomGet() {
        if (valueToIndex.size() == 0) {
            return null;
        }
        int index = random.nextInt(valueToIndex.size());
        return indexToValue.get(index);
    }

    public T randomRemove() {
        T value = randomGet();
        if (value == null) {
            return null;
        }
        remove(value);
        return value;
    }
}
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder result = new StringBuilder();
        if (root != null) {
            result.append(root.val + " ");
        }
        else {
            result.append("# ");
            return result.toString();
        }
        if (root.left != null) {
            result.append(serialize(root.left));
        }
        else {
            result.append("# ");
        }
        if (root.right != null) {
            result.append(serialize(root.right));
        }
        else {
            result.append("# ");
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    private int pos = -1;
    public TreeNode deserialize(String data) {
        String[] tree = data.split(" ");
        return helper(tree);
    }
    
    private TreeNode helper(String[] data) {
        pos++;
        if (data[pos].equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(data[pos]));
        node.left = helper(data);
        node.right = helper(data);
        return node;
    }
}


13.
lc187 DNA seq
加了点小变形，问的是repeated长度为10的substring，然后以大小输出所有它们
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=133637&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class RepeatDNA {
	public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) {
            return new ArrayList<>();
        }
        Set<String> seen = new HashSet<>();
        Set<String> repeat = new HashSet<>();
        StringBuilder buffer = new StringBuilder(s.substring(0, 10));
        // System.out.println(buffer.toString());
        seen.add(buffer.toString());
        for (int i = 10; i < s.length(); i++) {
            // System.out.println(buffer.toString());
            buffer.delete(0, 1);
            buffer.append(s.charAt(i));
            if (seen.contains(buffer.toString())) {
                repeat.add(buffer.toString());
            }
            else {
                seen.add(buffer.toString());
            }
        }
        return new ArrayList<>(repeat);
    }
    // output as in order and in O(n)
    // A -> 0
    // C -> 1
    // G -> 2
    // T -> 3
    public List<String> findDNA(Iterator<Character> input) {
        HashMap<Long, String> sequence = new HashMap<>();
        int[] number = new int[26];
        number[0] = 0;
        number['C' - 'A'] = 1;
        number['G' - 'A'] = 2;
        number['T' - 'A'] = 3;

        StringBuilder buffer = new StringBuilder();
        Set<String> seen = new HashSet<>();
        long maxIndex = 0;
        int count = 10;
        while (count > 0 && input.hasNext()) {
            buffer.append(input.next());
            count--;
        }
        if (buffer.length() < 10) {
            return new ArrayList<>();
        }
        seen.add(buffer.toString());
        while (input.hasNext()) {
            buffer.delete(0, 1);
            buffer.append(input.next());
            if (seen.contains(buffer.toString())) {
                long index = getIndex(buffer.toString(), number);
                maxIndex = Math.max(index, maxIndex);
                sequence.put(index, buffer.toString());
            }
            else {
                seen.add(buffer.toString());
            }
        }
        List<String> result = new ArrayList<>();
        for (long i = 0; i <= maxIndex; i++) {
            if (sequence.containsKey(i)) {
                result.add(sequence.get(i));
            }
        }
        return result;
    }

    private long getIndex(String dna, int[] number) {
        long index = 0;
        for (char letter : dna.toCharArray()) {
            index = index * 10 + number[letter - 'A'];
        }
        return index;
    }
}

14.
题目: Given一个array of non-negative integers, 找出3组数字可以组成3角形, 每个数字表示边长, 组成3角形的充要条件就是任2边的和要大于第3边

答案: 对这个array做sorting后, 从array的最小的element开始往最大去扫, 以连续3个数字一组确认是否可以构成3角形
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=217883&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


class Triangle {
    public boolean canFormTri(int[] input) {
        Arrays.sort(input);
        for (int i = input.length - 1; i >= 2; i--) {
            int number1 = input[i];
            int number2 = input[i - 1];
            int number3 = input[i - 2];
            if (number3 + number2 > number1) {
                return true;
            }
        }
        return false;
    }

    public int numberOfTri(int[] input) {
        int count = 0;
        Arrays.sort(input);
        for (int i = input.length - 1; i >= 2; i--) {
            int right = i - 1;
            int left = 0;
            while (left < right) {
                if (input[left] + input[right] > input[i]) {
                    count += right - left;
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return count;
    }

    public List<List<Integer>> allTri(int[] input) {
        int count = 0;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(input);
        for (int i = input.length - 1; i >= 2; i--) {
            int right = i - 1;
            int left = 0;
            while (left < right) {
                if (input[left] + input[right] > input[i]) {
                    for (int k = left; k < right; k++) {
                        List<Integer> tri = Arrays.asList(input[k], input[right], input[i]);
                        result.add(tri);
                    }
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        return result;
    }

}


15.
前两天刚面完发个面经回报地里，攒人品。
1. triangle ：given an positive integer array, check if there exists any combination of 3 integers which can construct a triangle nlogn. 
Followup: find all valid combination , O(n2）（我自己没进入状态，但是国人面试官很给力一直给我提示，谢啦）
follow up：1. 输出array能组成triangle的数目。2. 输出所有的能组成的triangle。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=156427&extra=page%3D5%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

第二道是新题，给multidimensional array，给一个function， 输入这个array以及各个dimension上的index，可以output这个位置上的数字。
写一个function，input是multidimensional array，以及array的dimensions，只能调用上面给的那个function，输出这个array里面所有的数字的和。
题不难，是我当时脑子懵了，一直在想怎么找这个array的各个dimension上的boundary，其实input就给了。和面试官一直在交流，但我没说好，十几分钟一直在纠结这个问题。
后来面试官举了个例子，立刻反应过来了。但也没有什么时间，就草草的说了下pseudo code，用dfs做所有dimension上的不同index的combination，然后调用那个function求和。

class MultidimensionalArray {
    public List<List<Integer>> getDimIndex(int[] dim) {
        List<List<Integer>> result = new ArrayList<>();
        helper(result, new ArrayList<>(), dim, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> path, int[] dim, int pos) {
        if (path.size() == dim.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < dim[pos]; i++) {
            path.add(i);
            helper(result, path, dim, pos + 1);
            path.remove(path.size() - 1);
        }
    }
}

2. Number of islands, max points on a line（很友善的国人）

3. Host manger

4. Sytem design, top 10 logs in last 60 min. Design the entire product, 
from how to get log data to how to show it in UI.（遇到很给力的国人）

5. Project deep dive. 

总的感觉很不错，开始不是那么想去，因为地点原因。但是面完一天觉得公司很不错，面试官给我的感觉都很年轻，积极。求个offer。

补充内容 (2016-12-7 13:31):

不好意思 不会怎么edit已经发布的帖子，第一轮的第一问是nlogn，不是o(n)
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215128&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

16.
1.host manager, project deep dive,design facebook message
2.coding, Q1:leetcode 366.  Q2:切圆n刀，最多切成多少块.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
3.coding, Q1:数组能否构成三角形. Q2:内存池. from: 1point3acres.com/bbs 
4.design tinny url
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=217245&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// cut cirlcle
f(1) = 2
f(2) = 2 + f(1) = 4
f(3) = 3 + f(2) = 7
f(n) = n + f(n - 1)
	= n + n - 1 + n - 2 + .. + 2 + f(1)
	= (n + 2) * (n - 1) / 2 + f(1) = (n ^ 2 - 2n - 2) / 2 + 2


17.
Coding  每一轮所有叶子脱落，直到root最后脱落，要求打印所有轮；
followup： 每个node还是有left，right指针，但是结构并非tree，而是graph怎么算， 考虑有/无circular dependency 两种情况；
方法是用hashmap 记录计算过的node level 和 一个Set 记录dependent node
3.    Design. 烙印 设计google/outlook calendar
create event
invite user
notify users at specific time or periodically
4.    Coding
linked list焦点问题，面经里很多，考虑相交不相交，有环无环
1. 无环不相交
2. 无环相交.
3. 有环不相交.
4. 有环相交

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215640&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
// print leaf
public class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, result);
        return result;
    }
    
    private int helper(TreeNode node, List<List<Integer>> result) {
        if (node == null) {
            return -1;
        }
        int left = helper(node.left, result);
        int right = helper(node.right, result);
        int height = Math.max(left, right) + 1;
        if (result.size() <= height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(node.val);
        return height;
    }
}

class PrintLeaves {
    // input is graph
    public List<List<Integer>> printLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, new HashMap<TreeNode, Integer>(), result);
        return result;
    }

    private int helper(TreeNode root, HashMap<TreeNode, Integer> nodeToDepth, List<List<Integer>> result) {
        if (root == null) {
            return -1;
        }
        if (nodeToDepth.containsKey(root)) {
            if (nodeToDepth.get(root) == -2) {
                throw new IllegalArgumentException("There is circle in the graph\n");
            }
            return nodeToDepth.get(root);
        }
        nodeToDepth.put(root, -2);
        int left = helper(root.left, nodeToDepth, result);
        int right = helper(root.right, nodeToDepth, result);
        int height = Math.max(right, left) + 1;
        nodeToDepth.put(root, height);
        if (height >= result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(root.val);
        return height;
    }
}



18.
第一题 求N的X赐方 要求iterative和recursive都写了遍
第二题 写一个求interval的总范围面积 提供API add和get面积 要求add会call很多次get很少 开始LZ开始merge interval但是面试官说不行 太慢 后来LZ说interval tree 大叔说太复杂
最后大叔意思是interval的重叠面积特别小 所有可以无脑add到list里面 求总长度的时候再去重就好
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216065&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

19.
1. Path Sum的变种：给一个二叉树，每个节点的value都是one digit，比如[1,2,3,null,4] (leetcode的表示法)，那path就是1->2->4和1->3，那return 124+13;
2. leetcode 90. Subsets II。Follow up: 时间复杂度。

1. leetcode 53. Maximum Subarray follow up：如果输入时Stream怎么办(DP)
2. leetcode 152. Maximum Product Subarray
3. leetcode 244. Shortest Word Distance II.		

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216770&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    public int maxSubArray(int[] nums) {
        
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            maxSum = Math.max(maxSum, sum);
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxSum;
    }
}

public class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = nums[0];   
        int min = nums[0];
        int cur = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = nums[i];
            int temp = max;
            max = Math.max(cur, Math.max(cur * min, cur * max));
            min = Math.min(cur, Math.min(cur * min, cur * temp));
            result = Math.max(result, max);
        }
        return result;
    }
}



class PathSumII {
    public int pathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> numbers = new ArrayList<>();
        helper(root, numbers, 0);
        int result = 0;
        for (int number : numbers) {
            result += number;
        }
        return result;
    }

    private void helper(TreeNode root, List<Integer> numbers, int cur) {
        cur = cur * 10 + root.val;
        if (root.left == null && root.right == null) {
            numbers.add(cur);
            return;
        }
        if (root.left != null) {
            helper(root.left, numbers, cur);
        }
        if (root.right != null) {
            helper(root.right, numbers, cur);
        }
    }
}





20.
第一轮中国人，在linkedin工作两年，第一道题valid num没有科学计数法，秒。第二道题permutation 2，
注意是generic类，没复习java generic怎么写的回去复习一下linkedin似乎很喜欢用接口啊generic等等的。
楼主沙比之前一直都没想起来用visited标记这种方法都是傻乎乎的搞了另一个set存现在还能用什么元素。写了这种set的方法，效率不够，问怎么能够优化，
才想起来可以用visited，说了思路。大叔（小哥？）就说好了不用写了，庆幸啊！回去仔细想了一下还真没那么简单的，现场改很可能改错。
大叔人很好，明显感觉放水（其实放水不一定要题很简单，你在写的时候他会给你feedback，有可能遇到的坑他会及时把你带出来就很好了）在hr给的feedback也很好，非常感谢！

第二轮，韩国人+中国人shadow，就写了一道题，intersection and union of two list（sorted），
注意参数给的是List<Integer> ，不知道实现的话get方法可能是o（n）的。一开始写了个用get的，
doubt 我的时间复杂度，我说这个可能是o（n），那可能需要用iterator。
考官说对那就写吧，然后秒写，这里楼主就突然傻逼了。这题相当于peeking iterator，双指针（双iterator）遍历两个list时，
iter.hasNext()是不能作为跳出循环的依据的，因为有一个元素是缓存起来的，只有当那个缓存的头元素也用完了之后才能跳出循环。所以这个bug卡了十分钟都没查出来，
其实非常明显的但就是先入为主觉得这怎么可能错没有注意检查这个边界条件。最后给了个test case我才恍然卧槽，这智商真是坑了坑了。. 鐣欏鐢宠璁哄潧-涓€浜╀笁鍒嗗湴

建议再有这种要在iterator上搞peek的，不要嫌费事，写一个peekiterator的类包装一下原来的iter。
然后时间空间复杂度再问
写完了这个，问union多个list的话怎么办，我说那就merge吧，面试官说不行这个空间复杂度很高。我说那就priorityqueue吧，面试官表示满意，问了下空间时间复杂度。
然后继续follow up问那就俩list union，你搞个parallel算法。楼主蒙了一下，想了两分钟，说那就第一个数组分n份，找到pivotal点在第二个数组上二分搜索该元素对应的位置，
得到这些位置传给并行算法就可以了吧。面试官说行，问了下空间复杂度（我觉得并没有卵区别，还是和以前一样）。. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
面试过了大概45分钟（晚打来了6分钟），就直接问问题了。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214086&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class Permutation<T extends Comparable<T>> {
    public List<List<T>> permutation(T[] input) {
        List<List<T>> result = new ArrayList<>();
        if (input == null || input.length == 0) {
            return result;
        }
        Arrays.sort(input, new Comparator<T>() {
            @Override
            public int compare(T t1, T t2) {
                if (t1.compareTo(t2) > 0) {
                    return 1;
                }
                else if (t1.compareTo(t2) < 0) {
                    return -1;
                }
                return 0;
            }
        });
        helper(result, new ArrayList<>(), new HashSet<>(), input);
        return result;
    }

    private void helper(List<List<T>> result, List<T> path, Set<Integer> visited, T[] input) {
        if (path.size() == input.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < input.length; i++) {
            if (visited.contains(i) || (i > 0 && input[i].equals(input[i - 1]) && !visited.contains(i - 1))) {
                continue;
            }
            path.add(input[i]);
            visited.add(i);
            helper(result, path, visited, input);
            path.remove(path.size() - 1);
            visited.remove(i);
        }
    }
}

// next permutation
// find the sequence that is acending from the tail
public class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int first = 0;
        int second = 0;
        for (int i = nums.length - 1; i >= 1; i--) {
            if (nums[i] > nums[i - 1]) {
                first = i - 1;
                second = i;
                break;
            }
        }
        int bigger = 0;
        for (int i = nums.length - 1; i >= second; i--) {
            if (nums[i] > nums[first]) {
                bigger = i;
                break;
            }
        }
        swap(nums, first, bigger);
        reverse(nums, second, nums.length - 1);
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}

// The idea is that find the last two adjacent number that the first one is beigger than the second one
// Then the question come to that find the previous permutation of the nums[first-end]
// Then sequence after second must be acending, so the previous permutation must comes from 
// the number that is samller than the nums[first] at the position first with a decending sequence after it
// Exp: 5, 4, 1, 2, 3 previous -> 5, 3, 4, 2, 1 
// the num[first] = 4, nums[second] = 1, nums[smaller] = 3 
class PreviousPermutation {
    public int[] previous(int[] input) {
        if (input.length <= 1) {
            return input;
        }
        int first = 0;
        int second = 0;
        for (int i = input.length - 1; i >= 1; i--) {
            if (input[i] < input[i - 1]) {
                first = i - 1;
                second = i;
                break;
            }
        }
        int smaller = 0;
        for (int i = input.length - 1; i >= second; i--) {
            if (input[i] < input[first]) {
                smaller = i;
                break;
            }
        }
        swap(input, first, smaller);
        reverse(input, second, input.length - 1);
        return input;
    }
    private void swap(int[] input, int index1, int index2) {
        int temp = input[index1];
        input[index1] = input[index2];
        input[index2] = temp;
    }
    private void reverse(int[] input, int start, int end) {
        while (start <= end) {
            swap(input, start++, end--);
        }
    }
}


class UnionAndIntersection {
    public List<Integer> intersection(List<Integer> list1, List<Integer> list2) {
        if (list1 == null || list2 == null) {
            return new ArrayList<>();
        }
        Iterator<Integer> iter1 = list1.iterator();
        Iterator<Integer> iter2 = list2.iterator();
        List<Integer> result = new ArrayList<>();
        Number num1 = new Number(null, iter1);
        Number num2 = new Number(null, iter2);
        if (iter1.hasNext()) {
            num1.val = iter1.next();
        }
        if (iter2.hasNext()) {
            num2.val = iter2.next();
        }
        while (num1.val != null && num2.val != null) {
            if (num1.val > num2.val) {
                getNext(num2);
            }
            else if (num2.val > num1.val) {
                getNext(num1);
            }
            else {
                result.add(num2.val);
                getNext(num2);
                getNext(num1);
            }
        }
        return result;
    }

    private void getNext(Number number) {
        if (number.iterator.hasNext()) {
            number.val = number.iterator.next();
        }
        else {
            number.val = null;
        }
    }

    public List<Integer> union(List<Integer> list1, List<Integer> list2) {
        if (list1 == null && list2 != null) {
            return new ArrayList<>(list2);
        }
        else if (list1 != null && list2 == null) {
            return new ArrayList<>(list1);
        }
        else if (list1 == null && list2 == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iter1 = list1.iterator();
        Iterator<Integer> iter2 = list2.iterator();
        Number num1 = new Number(null, iter1);
        Number num2 = new Number(null, iter2);
        if (iter1.hasNext()) {
            num1.val = iter1.next();
        }
        if (iter2.hasNext()) {
            num2.val = iter2.next();
        }
        while (num1.val != null || num2.val != null) {
            if (num1.val == null || (num2.val != null && num1.val > num2.val)) {
                result.add(num2.val);
                getNext(num2);
            }
            else if (num2.val == null || (num1.val != null && num2.val > num1.val)) {
                result.add(num1.val);
                getNext(num1);
            }
            else {
                result.add(num2.val);
                getNext(num1);
                getNext(num2);
            }
        }
        return result;
    }

    class Number {
        Integer val;
        Iterator<Integer> iterator;
        public Number(Integer val, Iterator<Integer> iterator) {
            this.val = val;
            this.iterator = iterator;
        }
    }
}



21.
一棵树，不限子节点。重构树，输入子节点数和Head节点。给出的Node Class没有写Child的方法，所以一直考虑从后往前生成子树。
反正没有做完。后来烙印去开会，泰国裔的哥们说可以ClearChildrean，还给了一些操作Childeren Node的方面。我说Class里没有这些方面，他没有理我，感觉题目都换了

BoundedBlockingQueue. 
GenericClass. 
LinkedListSingle2Double
MaximumDepthBinaryTree
MaximumIntervalsOverlap
MirrorTree
MultiSum. 
NearestPoints2D
QuickSort
RightRotateTree
ThreeClosestElements.
TinyURL
Triangle
TriangleII

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=197890&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class BuildNaryTree {
    public NaryTreeNode rebuild(NaryTreeNode root, int k) {
        if (k == 0 || root == null) {
            return null;
        }
        NaryTreeNode newRoot = new NaryTreeNode(root.val);
        List<Integer> values = new ArrayList<>();
        Queue<NaryTreeNode> explore = new LinkedList<>();
        explore.offer(root);
        while (!explore.isEmpty()) {
            NaryTreeNode node = explore.poll();
            values.add(node.val);
            for (NaryTreeNode child : node.children) {
                if (child != null) {
                    explore.offer(child);
                }
            }
        }
        int index = 1;
        Queue<NaryTreeNode> building = new LinkedList<>();
        building.offer(newRoot);
        while (index < values.size()) {
            NaryTreeNode node = building.poll();
            int numberOfChildren = Math.min(k, values.size() - index);
            for (int i = 0; i < numberOfChildren; i++) {
                NaryTreeNode child = new NaryTreeNode(values.get(index++));
                node.children.add(child);
                building.offer(child);
            }
        }
        return newRoot;
    }
}

22.
code1: 1. 给一个tree，按照离叶子的距离partition出节点，就是返回List<List<Integer>>. 每个List<Integer> 是一层，但距离叶子的距离分别是0，1，2 。。。
           2. 找平面内过最多点的直线（老题了）
code2: 1. 返回sorted数组里，是否存在三角形三边。（老提了）
           2. String （e.g. “251745002”）插入＋－＊／，返回可以变成target （123）的组合。（没在面镜见过）答得一般。只把＋－写完了，讨论了乘除就到时间了。
host： 烙印manager全程不笑。介绍简历，从国内本科开始介绍，到怎么出国的，做的最牛逼的project，等等。
design1: design 3层connections的functions和存储方式。. from: 1point3acres.com/bbs 
design2: design 一个monitering system。没什么要求，一直让我遐想。
design3: design 多线程的functions window(Msec msec)，可以返回这段时间内的平均值。add（int val）时可以调用一个给好的getTime function得到时间。
午饭。国人妹子。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216630&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    // T(N) = 3 * (T(N - 1) + T(N -2) + .... + T(1))
    // T(N - 1) = 3 * (T(N - 2) + T(N - 3) + .... + T(1))
    // T(N) = (4 * T(N -1))
    // T(N) = 4 ^ (N -1)
    // time complexity: O(4 ^ n)
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.length() == 0) {
            return result;
        }
        helper(num, (long)target, 0L, "", result, 0, 0L);
        return result;
    }
    private void helper(String num, long target, long curSum, String path, List<String> result, int pos, long last) {
        if (pos == num.length() && curSum == target) {
            result.add(path);
            return;
        }
        for (int i = pos; i < num.length(); i++) {
            if (i != pos && num.charAt(pos) == '0') {
                return;
            }
            long number = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) {
                helper(num, target, number, path + Long.toString(number), result, i + 1, number);
            }
            else {
                helper(num, target, curSum + number, path + "+" + Long.toString(number), result, i + 1, number);
                helper(num, target, curSum - number, path + "-" + Long.toString(number), result, i + 1, -number);
                helper(num, target, curSum - last + last * number, path + "*" + Long.toString(number), result, i + 1, last * number);
                if (number != 0) {
                	helper(num, target, curSum - last + last / number, path + "/" + Long.toString(number), result, i + 1, last / number);
                }
            }
        }
    }
}

23.
一面：
nice国人小哥，写完题目还聊了一会代码风格，强调了一下代码的可读性。 leetcode原题： 
ltc 102:level order traversal, ltc 339. Nested List Weight Sum. 无followup。 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 

// weight nested list sum
public class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        int result = 0;
        for (NestedInteger nest : nestedList) {
            result += helper(nest, 0);
        }
        return result;
    }
    
    private int helper(NestedInteger nest, int depth) {
        depth++;
        if (nest.isInteger()) {
            return nest.getInteger() * depth;
        }
        int levelSum = 0;
        for (NestedInteger next : nest.getList()) {
            levelSum += helper(next, depth);
        }
        return levelSum;
    }
}
//reversed weight nested list sum
public class Solution {
    // Use BFS to traverse the nestedList
    // use two variable to record the sum of each level and total sum
    // every time finish a level, add this level sum to the SUM, 
    // then add SUM to total sum, so the sum will be the sum of all levels
    // and every time we add SUM to total sum, we actually make each level sum add itself again
    // which means they times the depth weight + 1
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) {
            return 0;
        }
        Queue<NestedInteger> explore = new LinkedList<>();
        int total = 0;
        int prevSum = 0;
        for (NestedInteger nest : nestedList) {
            explore.offer(nest);
        }
        while (!explore.isEmpty()) {
            int size = explore.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger nest = explore.poll();
                if (nest.isInteger()) {
                    levelSum += nest.getInteger();
                }
                else {
                    for (NestedInteger next : nest.getList()) {
                        explore.offer(next);
                    }
                }
            }
            prevSum += levelSum;
            total += prevSum;
        }
        return total;
    }
}

public class NestedIterator implements Iterator<Integer> {
    Stack<NestedInteger> content;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.content = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            this.content.push(nestedList.get(i));
        } 
    }

    @Override
    public Integer next() {
        return content.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!content.isEmpty()) {
            NestedInteger curNested = content.peek();
            if (curNested.isInteger()) {
                return true;
            }
            content.pop();
            for (int i = curNested.getList().size() - 1; i >= 0; i--) {
                content.push(curNested.getList().get(i));
            }
        }
        return false;
    }
}


二面：
本来是国人小哥，不过因为有事临时换了一位。小插曲是电话刚接通时感觉声音有点杂有点远，沟通之后面试官换了一个房间接电话继续了面试，声音质量也好多了。晚五分钟打来，简单介绍了一下他所在的组，然后我也简单介绍了一下自己。
一共面了三道题，
ltc 380 - 要求实现一个能insert/remove/getrandom的set，楼主一开始问了一下是不是会有duplicates(想确认一下是不是ltc381),然后被反问了set是否允许重复，
这下我才发现我题目没看明白... 然后就顺利的做了。Corner case中被问到了如果remove的元素不在set中以及getRandom时如果set中没元素该怎么办，回答了throws exception。
第二题：原题ltc 205，用两个数组的解法解决了,follow up问了一下如果input是很多string呢
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=207985&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
第三题：205的follow up，给一个string数组，将iso的词归在一组 {'fff','abc','foo','haa','www','vvv'}-> { {'fff','www','vvv'} , {'haa','foo'} , {'abc'} }
借了group anagram的方法把所有的string都换成a开头的iso string，然后用hashmap<String,Set<String>>解了。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214590&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] mapIndexS = new int[256];
        int[] mapIndexT = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char letterS = s.charAt(i);
            char letterT = t.charAt(i);
            if (mapIndexS[letterS] != mapIndexT[letterT]) {
                return false;
            }
            mapIndexS[letterS] = i + 1;
            mapIndexT[letterT] = i + 1;
        }
        return true;
    }
}

// follow up
// transfer word into same isomorphic partten
// every word start with 'a', every time meet a new letter
// map it to cur char, and increase the value of cur char the then if same letter show up again, use the value in map
// ex. foo -> abb
// ex. gjk -> abc
// ex. pkk -> abb
class Isomorphic {
    public List<List<String>> findIsomorphic(String[] input) {
        HashMap<String, Set<String>> wordToIso = new HashMap<>();
        List<List<String>> result = new ArrayList<>();
        for (String word : input) {
            String newWord = transfer(word);
            if (!wordToIso.containsKey(newWord)) {
                wordToIso.put(newWord, new HashSet<>());
            }
            wordToIso.get(newWord).add(word);
        }
        for (String isomorphic : wordToIso.keySet()) {
            result.add(new ArrayList<>(wordToIso.get(isomorphic)));
        }
        return result;
    }

    private String transfer(String word) {
        HashMap<Character, Character> map = new HashMap<>();
        StringBuilder result = new StringBuilder();
        char cur = 'a';
        for (char letter : word.toCharArray()) {
            if (!map.containsKey(letter)) {
                map.put(letter, cur);
                cur++;
            }
            result.append(map.get(letter));
        }
        return result.toString();
    }
}

24.
1. manager聊天，简单问了下设计一个key-value storage. Waral 鍗氬鏈夋洿澶氭枃绔�,
2. 算法，见http://www.1point3acres.com/bbs/thread-159920-1-1.html 第五题
3. 算法，sqrt() + linked list焦点问题，面经里很多，考虑相交不相交，有环无环
4. 午饭，表现算在面试里，我感觉只要相谈甚欢就行 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
5. design，design api。给定一个get_friends_lists() 问怎么判断两个是1 degree friends，2 degree friends 还是 3 degree friends。面经里也有类似的
6. design，design monitor system，比较麻烦，考虑了partition，replication，easy to use，还有一些其他乱七八糟的。面试的时候一定要小心shadow。。。
因为shadow没经验，肯定会问些乱七八糟的东西，这一轮的那个shadow不问死我我算完
7. design， 见http://www.1point3acres.com/bbs/thread-147555-1-1.html 第三轮，这一轮的时候太累了，脑子抽了，直接导致面试官给提示都听不懂，搞了个比较麻烦的方法来解决，会用额外的空间。。。但是后来想一下可能会更快

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208020&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// sqrt
public class Solution {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        long low = 0;
        long high = num;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            if (num == mid * mid) {
                return true;
            }
            else if (num < mid * mid) {
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        return false;
    }
}


25.
 word ladder 1.5 如果被challenge用26个char iteration太慢的话，大家怎么optimize？
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213577&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// first build the buckets
// for each word, we replace each letter as '_' to get the bucket
// ex. "HITS" -> "_ITS" and "H_TS" and "HI_S" and "HIT_" then put the word into the bucket
// so the words in each bucket are the next word to each other, only differs in one letter
// then use BFS to traverse from the begin word, each time we meet a word, get all the buckets it could have and find the next word
// Time complexity: O(mn) + O(mn), n- number of words, m- average length of word, first one is build buckets, second is BFS

class WordLadder3 {
    public List<String> wordLadder(Set<String> wordList, String beginWord, String endWord) {
        HashMap<String, Set<String>> buckets = new HashMap<>();
        wordList.add(beginWord);
        wordList.add(endWord);
        for (String word : wordList) {
            char[] chs = word.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                char origin = chs[i];
                chs[i] = '_';
                String bucket = String.valueOf(chs);
                if (!buckets.containsKey(bucket)) {
                    buckets.put(bucket, new HashSet<>());
                }
                buckets.get(bucket).add(word);
                chs[i] = origin;
            }
        }
        Set<String> visited = new HashSet<>();
        Queue<String> explore = new LinkedList<>();
        HashMap<String, String> step = new HashMap<>();
        step.put(beginWord, "");
        explore.offer(beginWord);
        visited.add(beginWord);
        boolean isFound = false;
        while (!explore.isEmpty() && !isFound) {
            int size = explore.size();
            for (int i = 0; i < size; i++) {
                String curWord = explore.poll();
                char[] chs = curWord.toCharArray();
                for (int k = 0; k < chs.length && !isFound; k++) {
                    char origin = chs[k];
                    chs[k] = '_';
                    String bucket = String.valueOf(chs);
                    for (String next : buckets.get(bucket)) {
                        if (next.equals(curWord)) {
                            continue;
                        }
                        if (next.equals(endWord)) {
                            step.put(endWord, curWord);
                            isFound = true;
                            break;
                        }
                        if (!visited.contains(next)) {
                            visited.add(next);
                            explore.offer(next);
                            step.put(next, curWord);
                        }
                    }
                    chs[k] = origin;
                }
            }
        }
        List<String> result = new ArrayList<>();
        String mover = endWord;
        while (!mover.equals("")) {
            result.add(0, mover);
            mover = step.get(mover);
        }
        return result;
    }
}

26.
刚面完Linkedin的电面一面，中国大哥人真的超级nice，一共三道题：1：有点像valid parenthese，看括号是否是闭合的
()()()()    ---->   true
(+1^$#)(#$)  ----> true
)(   ----->false
(()#%33  ----->false
2: Leet102 Binary Tree Level Order Traversal 不过不是存在list里是直接打印出来
3. 2的follow up，这次打印是zigzag方式打印，
比如. 1point 3acres 璁哄潧
1
35
2468
12345678
变成
1
53
2468
87654321
面试流程基本就是先上来5分钟自我介绍+45分钟coding+10分钟提问
希望大家offer多多，楼主水平还不够先刷leetcode去了
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210612&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class ValidParenthesis {
    public boolean isValid(String input) {
        int count = 0;
        for (char letter : input.toCharArray()) {
            if (letter == '(') {
                count++;
            }
            else if (letter == ')') {
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }
        return count == 0;
    }
}

public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> explore = new LinkedList<>();
        explore.add(root);
        while (!explore.isEmpty()) {
            int size = explore.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = explore.poll();
                level.add(node.val);
                if (node.left != null) {
                    explore.add(node.left);
                }
                if (node.right != null) {
                    explore.add(node.right);
                }
            }
            result.add(level);
        }
        return result;
    }
}

class LevelOrderDfs {
    public List<String> printTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<StringBuilder> levels = new ArrayList<>();
        helper(root, 0, levels);
        List<String> result = new ArrayList<>();
        for (StringBuilder builder : levels) {
            result.add(builder.toString());
        }
        return result;
    }

    private void helper(TreeNode root, int depth, List<StringBuilder> result) {
        if (depth >= result.size()) {
            result.add(new StringBuilder());
        }
        result.get(depth).append(root.val);
        if (root.left != null) {
            helper(root.left, depth + 1, result);
        }
        if (root.right != null) {
            helper(root.right, depth + 1, result);
        }
    }
}

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        boolean isOdd = true;
        Queue<TreeNode> explore = new LinkedList<>();
        explore.offer(root);
        while (!explore.isEmpty()) {
            int size = explore.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = explore.poll();
                if (node.left != null) {
                    explore.offer(node.left);
                }
                if (node.right != null) {
                    explore.add(node.right);
                }
                if (isOdd) {
                    level.add(node.val);
                }
                else {
                    level.add(0, node.val);
                }
            }
            isOdd = !isOdd;
            result.add(level);
        }
        return result;
    }
}


27.
第一轮： 一个白人staff engineer聊了快一个小时的project，我的project，他的project，问了些behavior问题，如果被hire打算怎么上手，
上手后打算怎么贡献，以后什么打算等等。聊的甚开心！
第二轮：国人美眉+国人小孩  LC  text justification原题。这位美眉为了给俺省白板空间，挺着大肚子还要爬椅子，好把问题写到白板顶部！感动的痛哭流涕。。。同时高度紧张，随时准备当肉垫
第三轮：lunch。又聊的甚欢，饭很一般般
第四轮：中亚人+国人shadown。要求设计一个calendar system。要求1000用户，并可能拓展到100M+（我xxx）。这轮应该是挂了，作死的选了个cassandra.1point3acres缃�
第五轮：白人小孩+国人小孩   tech communication。要求假装他们是我现在组里的新雇员，要用一个小时把组里做的项目挑一个讲清楚
第六轮：国人美眉+国人大哥   #1   给BT，每一轮所有叶子脱落，直到root最后脱落，要求打印所有轮
#2  LC find min window in S that has every char in T
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=174614&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// minimum window
public class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> countT = new HashMap<>();
        HashMap<Character, Integer> subCount = new HashMap<>();
        for (char letter : t.toCharArray()) {
            if (countT.containsKey(letter)) {
                countT.put(letter, countT.get(letter) + 1);
            }
            else {
                countT.put(letter, 1);
            }
        }
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        int slow = 0;
        int fast = 0;
        int curLen = 0;
        while (fast < s.length()) {
            char letter = s.charAt(fast);
            if (countT.containsKey(letter)) {
                if (subCount.containsKey(letter)) {
                    subCount.put(letter, subCount.get(letter) + 1);
                }
                else {
                    subCount.put(letter, 1);
                }
                if (subCount.get(letter) <= countT.get(letter)) {
                    curLen++;
                }
            }
            if (curLen >= t.length()) {
                while (slow <= fast 
                && (!countT.containsKey(s.charAt(slow)) || subCount.get(s.charAt(slow)) > countT.get(s.charAt(slow)))) {
                    if (subCount.containsKey(s.charAt(slow))) {
                        subCount.put(s.charAt(slow), subCount.get(s.charAt(slow)) - 1);
                    }
                    slow++;
                }
                int len = fast - slow + 1;
                if (len < minLen) {
                    minLen = len;
                    start = slow;
                }
                subCount.put(s.charAt(slow), subCount.get(s.charAt(slow)) - 1);
                slow++;
                curLen--;
            }
            fast++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}


28.
https://instant.1point3acres.com/thread/202469

29.
第一轮tech communication, 主要是介绍你现在做的project。不是特别technical.
第二轮是host manager面试，behavior
第三轮吃饭， linkedin吃饭轮也会被evaluate的
第四轮system design: hangman
第五轮：coding: 1. number of islands 2. k nearest point
第六轮： coding: 1. Minimum window index in string x that contains all characters in string y (http://stackoverflow.com/questio ... racters-in-string-y)
2. 有点像这道题， 要求求difference之和， 然后两个array都不是sorted http://comproguide.blogspot.com/ ... een-two-sorted.html
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214713&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


// the idea is that use a array to record all the result we have
// array[i][j] mean when we reach a[i] we try to chose b[j]
// thus array[i][j] = min(array[i - 1][j - 1] + abs(a[i] - b[j]), array[i - 1][j])
// then array[a.length][b.length] will be the result
class MiniDifference {
    public int miniDifference(int[] a, int[] b) {
        long[][] helper = new long[a.length + 1][b.length + 1];
        for (int i = 1; i <= a.length; i++) {
            helper[i][0] = Integer.MAX_VALUE;
        }
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length - (a.length - i); j++) {
                helper[i][j] = Math.min(helper[i - 1][j - 1] + Math.abs(a[i - 1] - b[j - 1]), helper[i][j - 1]);
            }
        }
        return (int)helper[a.length][b.length];
    }

    // b is super long
    public int miniDiff2(int[] a, int[] b) {
        long[] helper = new long[a.length + 1];
        for (int i = 1; i <= a.length; i++) {
            helper[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < b.length; i++) {
            long[] temp = new long[a.length + 1];
            for (int j = 1; j <= a.length; j++) {
                // we can stop if the number of remain numbers in a is more than numbers in b
                // which means cannot make a match
                if ((b.length - i - 1) < (a.length - j)) {
                    continue;
                }
                temp[j] = Math.min(helper[j - 1] + Math.abs(a[j - 1] - b[i]), helper[j]);
            }
            helper = temp;
        }
        return (int)helper[a.length];
    }
}


30.
Leetcode 205. Follow up: 如果是三个String 怎么做
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213305&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

31.
第一题是判断两个输入的string是否是isomorphic， leetcode原题。不多说。

第二题是design a data structure, support add(val), remove(val), removeRandom.
step1: design  add = O(1), remove O(1), removeRandom, O(N). => hashset only, removeRandom的时候把东西全放进一个array, 然后再删。这样有O(N)的memory.不过也符合要求啦。. more info on 1point3acres.com
step2: design  add = O(1), remove O(N), removeRandom, O(1). => hashset + array... 这个简单。. Waral 鍗氬鏈夋洿澶氭枃绔�,
step3: design  add = O(1), remove O(1), removeRandom, O(1). => hashset + array .. 用下面说的那个trick做remove.

关键是要想到如何在O(1)时间里，从一个array里面删除一个元素。
就是把这个元素和array的最后一个元素交换，然后删除最后一个元素，同时减小array的size。这样就O(1)完成了删除。思想感觉和quick sort的in-place partition非常像。
小哥这个三步走循循善诱，不然估计还想不出来嗯。
最后直接实现最后一个，前两个都是在讨论。
design题目是那个write/get/getRandom/deleteRandom都是O(1)的题目，我开始写的code和他想要的略有不同，
最正确解法是vector里面同时存key/val，hashtable里面存key，value是vector的index。我的vector里面只存了key，所以多用了一个hashtable，不过最后也经过提示找到了这种方法。


补充内容 (2016-12-17 12:38):. 
Step3: 打错拉。是hashmap + array.
Hashmap用来做value -> array index的mapping.

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=216384&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

32.
第一题： 是replace string 把一个string里出现的某一个string 用另外一个string取代，
然后最后扔了个KMP给我，平常做题的时候还是要把所有follow up都做了啊 至少要想下才行

第二题：deisng a max stack that supports all regular stack operations and peekMax popMax in log time. 1point 3acres 璁哄潧
对这种题不擅长，也硬着头皮上了，最后是需要用一个linked和一个maxheap来做，然后keep pointers。
基本上implementation都没有写。。。。 因为时间最后不够，也因为花了很多时间讨论。不过最后有个面试官
说了基本上是把trade off of design choices都讲清楚了，嗯我比较能讲。。。。不会写。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=212946&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MaxStack {
    private Node top;
    private PriorityQueue<Node> max;
    public MaxStack() {
        this.max = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node node2) {
                return node2.val - node1.val;
            }
        });
        this.top = new Node(0);
    }
    public void push(int val) {
        Node node = new Node(val);
        top.next = node;
        node.prev = top;
        top = node;
        max.add(node);
    }

    public Integer pop() {
        if (top.prev == null) {
            return null;
        }
        Node node = top;
        top = top.prev;
        node.prev = null;
        top.next = null;
        max.remove(node);
        return node.val;
    }

    public Integer peek() {
        if (top.prev == null) {
            return null;
        }
        return top.val;
    }

    public Integer peekMax() {
        if (max.isEmpty()) {
            return null;
        }
        return max.peek().val;
    }

    public Integer popMax() {
        if (max.isEmpty()) {
            return null;
        }
        Node maxNode = max.poll();
        if (maxNode == top) {
            top = top.prev;
        }
        maxNode.prev.next = maxNode.next;
        if (maxNode.next != null) {
            maxNode.next.prev = maxNode.prev;
        }
        maxNode.prev = null;
        maxNode.next = null;
        return maxNode.val;
    }

    class Node {
        Node prev = null;
        Node next = null;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
}




33.
1. implement pow(double a, int b) (calculate a^b without using any built-in library)
2. two sum i/nterface LC 170
写了两种版本，一个是insert很快，一个是find 很快
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=209820&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

34.
第一轮：系统设计
已知一个函数，输入用户ID，可以返回该用户的所有友好（degree 1 friends），按好友ID从小到大排序。
要求实现函数来输出返回一个用户的所有好友的好友(degree 2 friends), 以及 degree 3 friends。

这里感觉主要是聊天看思路，中间会临时加一些限制条件，来进行时间或者空间的优化。. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴

class FindFriends {
    public List<Integer> secondDegree(int id) {
        if (id <= 0) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Set<Integer> friends = new HashSet<>(getFriend(id));
        for (int friend : friends) {
            List<Integer> 3rdDegree = getFriend(friend);
            for (int 3rd : 3rdDegree) {
                if (friends.contains(3rd) || 3rd == id) {
                    continue;
                }
                result.add(3rd);
            }
        }
        return result;
    }
}


午饭：一个小时，随便聊 
. more info on 1point3acres.com

第二轮： coding
1.implement a c/lass to finish data operation methods: add， delete， random delete. 
2. repeated DNA sequence (leetcode)
3. binary tree upside down (leetcode). 1point3acres.com/bbs


第三轮：系统设计
对于key，value pairs， 在给定的文件系统中实现 put，get，delete 的方法。其中key比较小，全部key可以放在内存中，value有的会比较大
已知一个文件系统，可以
create files, delete files, sequentially scan file content, read file content randomly, append file content. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴

第四轮：coding
1. 求平方根
2. 双向链表，但是每一个点还可以有up，down pointer， 已知一个链表里没有环，要求把这个链表变成标准双向链表，
每个点的具体位置排列无所谓。楼主开始反应是递归，写好后面试官说优化一下，，空间要求是constant space，
然后尽管面试官一直在提示tail recursion，还是没想出来（据说地里有原题，可惜当时楼主没看到。。。跪了= =！）

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=147555&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// every time we meet a node check if it has up/down node
// if it has, connect it to the tail of the list
// and keep traversing
class FlattenMetaList {
    public MetaNode flatten(MetaNode input) {
        if (input == null) {
            return null;
        }
        MetaNode head = getHead(input);
        MetaNode tail = getTail(input);
        MetaNode mover = head;
        while (mover != null) {
            if (mover.up != null) {
                MetaNode up = mover.up;
                MetaNode subHead = getHead(up);
                tail.next = subHead;
                subHead.prev = tail;
                mover.up = null;
                tail = getTail(tail);
            }
            if (mover.down != null) {
                MetaNode down = mover.down;
                MetaNode subHead = getHead(down);
                tail.next = subHead;
                subHead.prev = tail;
                mover.down = null;
                tail = getTail(tail);
            }
            mover = mover.next;
        }
        return head;
    }

    private MetaNode getHead(MetaNode node) {
        while (node.prev != null) {
            node = node.prev;
        }
        return node;
    }

    private MetaNode getTail(MetaNode node) {
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }
}

class MetaNode {
    MetaNode up = null;
    MetaNode down = null;
    MetaNode prev = null;
    MetaNode next = null;
    int val;
    public MetaNode(int val) {
        this.val = val;
    }
}

35.
１.ｄｅｓｉｇｎ，给一个ｍａｐ函数输入一个值输出一个值，一个ｒｅｄｕｃｅ函数输入２个参数返回一个值，
ｎ个ｔｈｒｅａｄ，设计一个函数先ｍａｐ再ｒｅｄｕｃｅ，最大化利用所有ｔｈｒｅａｄｓ
２．ｄｅｉｓｇｎ，地里有，求１ｓｔ，２ｎｄ，３ｒｄ，ｃｏｎｎｅｃｔｉｏｎｓ. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
３．ｄｅｓｉｇｎ，推荐过去５ｍｉｎ，１ｈｏｕｒ，２４ｈｏｕｒｓ　的ｔｏｐ１００　ｓｈａｒｅｄ文章
４.　ｄｉｒｅｃｔｏｒ，　问ｂｅｈａｖｉｏｒ
５.　ｃｏｄｉｎｇ：　ａｌｌ　ｃｏｍｂｉｎａｔｉｏｎ　ｏｆ　ｆａｃｔｏｒｓ. visit 1point3acres.com for more.
６．　ｗｏｒｄ　ｌａｄｄｅｒ　输出ｐａｔｈ
７．　午餐
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=209589&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

36.
lc原题Integer to Roman。

public class Solution {
    public String intToRoman(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] rom = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int value = num;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            while (value >= nums[i]) {
                value -= nums[i];
                sb.append(rom[i]);
            }
        }
        return sb.toString();
    }
}

public class Solution {
    public int romanToInt(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int pivot = map.get(s.charAt(s.length() - 1));
        res = pivot;
        for (int i = s.length() - 2; i >= 0; i--) {
            int cur = map.get(s.charAt(i));
            if (cur >= pivot) {
                res += cur;
            }
            else {
                res -= cur;
            }
            pivot = cur;
        }
        return res;
    }
}

37.
reverse consecutive repeat string 
  input: abcdd
  output: dcba 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=215073&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


38.
Coding 1insert interval
一个描述非常复杂的dfs, parse string， 很简单

Coding 2
remove/add/getRandom O(1)
用这个解法做的, feedback 是这个不是O(1), 面试官没听明白
https://discuss.leetcode.com/top ... -follow-up-131-ms/2
多维矩阵求和. 1point 3acres 璁哄潧

Design 
设计一个找系统里面所有符合要求文件的function, 要求能不停的添加新的条件
type linkedin.com url 后发生什么, 这轮说概念上有些confusion.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=204503&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

39.
r1: add/delete/random delete
     leetcode DNA原题

r2: K nearest points in 2D

r3: tiny URL

r4: mgr

r5: tech communucation
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171681&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

40.
然后做题 leetcode 47，写了一半让我先做 leetcode 46，跑个test case，然后做完47之后大概还有五分钟

41.
Tournament tree 找secMin;. 

Tournament tree 的定义是parent 是孩子node的最小值， 如下例 return 5

                2
              /  \
            2    7
          /  \    | \
        5    2    8  7. 
        然后我问小哥要提示，小哥说第二名只能被最后的冠军打败。
所以我就想到只需要考虑被root打败过的node就可以了，就想到了O(logn)的解法，写出来了
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=141428&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// follow up: find kth smallest
// after find the 2nd, change the smallest to 2nd mini
// then do find secmini again to find 3rd ..
class SecondMinTournamentTree {
    public int secondMin(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        }
        int secondMin = Integer.MAX_VALUE;
        while (root.left != null && root.right != null) {
            if (root.val == root.right.val) {
                secondMin = Math.min(secondMin, root.left.val);
                root = root.right;
            }
            else {
                secondMin = Math.min(secondMin, root.right.val);
                root = root.left;
            }
        }
        return secondMin;
    }
}

42.
'design
important'
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=175538&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

43.
1. 烙印女
'Edit distance'
2. 华人
Design a system to report top N 500 server exceptions.. visit 1point3acres.com for more.

3. 国男1
1. Isomorphic words, follow-up if given you a list of words, and how to group the input into each isomorphic groups.

4. 烙印经理.鏈枃鍘熷垱鑷�1point3acres璁哄潧
Culture/Behavior
中间问了一点类似typeahead/自动补全的功能怎么设计的问题。

5. 国男2. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
Technical Communication. 将自己做过的一个项目。.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198297&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// edit distance
public class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] helper = new int[m + 1][n + 1];
        for (int i = 0; i < helper.length; i++) {
            helper[i][0] = i;
        }
        for (int i = 0; i < helper[0].length; i++) {
            helper[0][i] = i;
        }
        for (int i = 1; i < helper.length; i++) {
            for (int j = 1; j < helper[0].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    helper[i][j] = helper[i - 1][j - 1];
                }
                else {
                    //helper[i - 1][j - 1]: replace
                    //helper[i - 1][j]: delete
                    //helper[i][j - 1]: insert
                    helper[i][j] = 1 + Math.min(Math.min(helper[i - 1][j - 1], helper[i - 1][j]), helper[i][j - 1]);
                }
            }
        }
        return helper[m][n];
    }
}


44.
一面（一个烙印）：
pow(m, n)
写了一个naive的O(n)方法和二分递归的O(logn)的方法. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴

two sum data structure
两种场景，insertion多的和query多的
follow up是算k sum，不过只讲了下思路没写.1point3acres缃�

二面（一个烙印+一个中国人）：
level order traverse (print result)

。。。
用queue很快写完，结果那个面试官沉默了很久。。。不知道是在想什么。。。。反复和他check我哪里有问题，他并没有说什么。。。有点虚

tournament tree (父节点是左右子节点中较小的）
找第二小的
思路就是第二小的一定是和最小的有一次比较，所以只遍历有最小值的那条路径，找到路径里面除了这个最小的之外，其他最小的就好了
follow up是找第三小的和找任意第k小的。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213012&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311



45.
第一轮两个萌萌的小哥，和我面到一半好像还被从本来呆的会议室赶出去了，折腾了一会又继续面......Valid BST和Nested Integers Weighted Sum那道LC原题。问了问问题，四十五分钟结束。
第二轮一个小姐姐，先问了Factor Combination(LC254)，然后又问了两个sorted arrays of integers的union。比如[1, 1, 1, 2 ]和[1, 1 , 3]要return [1, 1,1, 2, 3]。我一开始没注意是sorted，用hashMap做了....（觉得这个地方跪了）. 鍥磋鎴戜滑@1point 3 acres
做完小姐姐说哎不是说sorted么， 我：哦...........
然后又用了两个pointer的方法做的
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213211&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
// factor combination

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            return result;
        }
        helper(result, new ArrayList<>(), n, 2);
        return result;
    }
    
    private void helper(List<List<Integer>> result, List<Integer> path, int factor, int pos) {
        if (path.size() > 0 && factor != 1) {
            path.add(factor);
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
        }
        for (int i = pos; i <= Math.sqrt(factor); i++) {
            if (factor % i != 0) {
                continue;
            }
            path.add(i);
            helper(result, path, factor / i, i);
            path.remove(path.size() - 1);
        }
    }
}

class CombinationFactor2 {
    public List<List<Integer>> get(int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (target == 0) {
            return result;
        }
        helper(result, 0, new ArrayList<Integer>(), target, 1);
        return result;
    }

    private void helper(List<List<Integer>> result, int sum, List<Integer> path, int target, int cur) {
        if (sum == target) {
            if (path.size() != 1) {
                result.add(new ArrayList<Integer>(path));
            }
            return;
        }
        for (int i = cur; i <= target; i++) {
            if (sum + i > target) {
                continue;
            }
            path.add(i);
            helper(result, sum + i, path, target, i);
            path.remove(path.size() - 1);
        }
    }
}


46.
1. Isomorphic String，我写了two way mapping那种做法，没有问follow-up (经典的3个string怎么比较)，让我跑了两个test case
2. Intersection and Union of two sorted lists，网上有two arrays的做法，lists基本一样，two pointers. Follow-up是如果输入lists里有重复，
但是输出结果有重复怎么做？我就加了个while loop跳过重复元素。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=212421&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

47.
 lc151 刚开始不用考虑前后有空格以及每个word之间大于一个空格情况，很快写出来了
接着followup 如果word之间大于一个空格保留空格数，可能因为太紧张调来调去都不对，然后问小哥有没有hint
结果小哥说了一个很好的方法 把中间的空格也按照word一样reverse 然后在整体reverse.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214042&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class ReverseString {
    public String reverseString(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        char[] letters = input.toCharArray();
        reverse(0, letters.length - 1, letters);
        int slow = 0;
        while (slow < letters.length) {
            if (letters[slow] == ' ') {
                slow++;
            }
            else {
                int fast = slow + 1;
                while (fast < letters.length && letters[fast] != ' ') {
                    fast++;
                }
                reverse(slow, fast - 1, letters);
                slow = fast;
            }
        }
        return String.valueOf(letters);
    }

    private void reverse(int start, int end, char[] input) {
        while (start <= end) {
            char temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            start++;
            end--;
        }
    }
}

48.
题目很简单的，
1. 罗马字母转数字. more info on 1point3acres.com
2. 数字转罗马字母

49.
number of Island和Search Range

50.
罗马字转数字，数字转罗马。先给出我个人最喜欢的写法。考官不喜欢，非要用他的，纠结了一下。. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
4.四向链表（上下左右）转双向链表（左右）。输入是一头一尾。放平链表后（中间可以乱序序），保证头尾元素还是所给的指针。
先说了BFS的想法。然后要求用O（1）做空间。就是不管一切的放平了。然后把头尾挑出来，放到列首和列尾。.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�
5.设计短网址服务器构架。讨论流量，服务器架构和数据库架构。纯设计，半点代码都不碰。各种挑战。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=203816&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

51.
第一题：union/intersect list(此处为arraylist)，我用的hashset做的，回来跟同学商量才发现list是已经排好序的，最优解是应该two pointers，虽然当时小哥什么也没说，就开始下一道题了。
. 鍥磋鎴戜滑@1point 3 acres
第二题: 相当于实现Microsoft里面的查找替换的功能，给一个string article, 一个string find,一个string replace,
把文章里所有的find都替换成replace，例如abcdbc把bc换成e ---->aede。思路比较简单，直接indexOf做了，
只不过本来应该是用indexOf("bc",index)，我给写成indexOf(index,"bc")了（这个是错的）。写完小哥让我口头跑test case，发现一个bug修复，然后剩下5分钟了。


http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=212201&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

52.
是设计一个 mid stack。  例如push 1，3，6，5，4.  返回6

刚开始理解错题目了，以为是要返回value的中值（这样就比较复杂累）。 但要求是返回index的中值。上个例子的top是4， mid 是6
鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
不过幸亏想到用双链表做。 
Solution: http://www.geeksforgeeks.org/design-a-stack-with-find-middle-operation/
follow up是写popMid()函数。不仅返回，同时删除中值。 最后设计test case。

其实follow up由于比较紧张，也没答好，有一个bug，不过好像面试官也没看出来，我是面完之后才想起来的
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=143532&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MidStack {
    private Node tail;
    private Node mid;
    private int totalLen;
    public MidStack() {
        this.tail = new Node(0);
        this.totalLen = 0;
        this.mid = tail;
    }

    public void push(int val) {
        Node node = new Node(val);
        node.prev = tail;
        tail.next = node;
        tail = node;
        totalLen++;
        if (totalLen % 2 == 1) {
            mid = mid.next;
        }
    }

    public Integer pop() {
        if (totalLen == 0) {
            return null;
        }
        Node node = tail;
        tail = node.prev;
        totalLen--;
        if (totalLen % 2 == 0) {
            mid = mid.prev;
        }
        node.prev = null;
        tail.next = null;
        return node.val;
    }

    public Integer peekMid() {
        if (totalLen == 0) {
            return null;
        }
        return mid.val;
    }

    public Integer popMid() {
        if (totalLen == 0) {
            return null;
        }
        Node result = mid;
        Node prev = mid.prev;
        mid.next.prev = prev;
        prev.next = mid.next;
        mid.next = null;
        mid.prev = null;
        totalLen--;
        if (totalLen % 2 == 0) {
            mid = prev;
        }
        else {
            mid = prev.next;
        }
        return result.val;
    }

    class Node {
        Node prev = null;
        Node next = null;
        int val;
        public Node(int val) {
            this.val = val;
        }
    }
}


53.
第一题：
 boolean canPlaceFlowers(List<Boolean> flowerbed, int numberToPlace)
如果flowerbed当中为true，说明已经栽过花了，附近两个不能再栽花。numberToPlace代表想再栽多少花到flowerbed里。
让return是不是还能栽那么多谢花进去。. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷

class PlaceFlower {
    public boolean canPlaceFlower(boolean[] flowerBed, int numberOfPlace) {
        if (numberOfPlace == 0) {
            return true;
        }
        int index = 0;
        while (index < flowerBed.length) {
            if (flowerBed[index]) {
                index += 2;
                continue;
            }
            boolean left = false;
            boolean right = false;
            if (index == 0) {
                left = true;
            }
            else {
                left = !flowerBed[index - 1];
            }
            if (index == flowerBed.length - 1) {
                right = true;
            }
            else {
                right = !flowerBed[index + 1];
            }
            if (right && left) {
                numberOfPlace--;
                index = index + 2;
            }
            else {
                index++;
            }
        }
        return numberOfPlace <= 0;
    }
}

第二题：
 int distance (List<String> words, String wordOne, String wordTwo). 1point 3acres 璁哄潧
给一个string list，可能存在重复，给两个word，让return这两个word在list中的最短距离

第三题：
输入是一个array stream，在任何时候call你的method，给一个input value，让返回之前输入过的数字有没有两个加起来等于这个value的（2sum稍稍变形）

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=129336&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class PlaceFlower {
    public boolean canPlaceFlower(boolean[] flowerBed, int numberOfPlace) {
        if (numberOfPlace == 0) {
            return true;
        }
        int index = 0;
        while (index < flowerBed.length) {
            if (flowerBed[index]) {
                index += 2;
                continue;
            }
            boolean left = false;
            boolean right = false;
            if (index == 0) {
                left = true;
            }
            else {
                left = !flowerBed[index - 1];
            }
            if (index == flowerBed.length - 1) {
                right = true;
            }
            else {
                right = !flowerBed[index + 1];
            }
            if (right && left) {
                numberOfPlace--;
                index = index + 2;
            }
            else {
                index++;
            }
        }
        return numberOfPlace <= 0;
    }
}

54.
一个参加工作没多久的中国小哥，先互相介绍，然后2道题，最后QA。
1. LC 170, Two SumIII, 先讨论了哪个操作多，然后说的是find操作多，用的set
2. LC 187, DNA, 唯一不同是要求输出是in order的，而且时间O(n)，
这个让我想了很久。最后用了不是很好的办法做的，把所有可能按从小到大loop一遍，
存在结果里的就输出来。因为前提说了数据量很大。不知道大家有没有更好的办法。小哥提示了说在中间过程就已经是ordered，但是一直没get到点。

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213817&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

55.
Add Interval + DNA sequence 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
Interval是要你implement一个class，两个function，addInterval(int from, int to)和getTotalCoveredTime()。。脑子里一直都是lc原题一开始有点思想僵化卡壳。。。还好后来重新理了下思路写好了。。
DNA sequence和lc不一样的地方是input不是一个string而是一个scanner，相当于只能一个一个character读。。. visit 1point3acres.com for more.
两题写完都有点小bug烙印很nice让我go through一些test case然后改好了。。。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213836&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

56.
lc53 跟follow up lc152
104        Maximum Depth of Binary Tree
150        Evaluate Reverse Polish Notation  input 是double，原题是int。
第二题有follow up，加一个 factorial “！”。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210035&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class HandlePRN {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("+")) {
                checkValid(stack);
                int number1 = stack.pop();
                int number2 = stack.pop();
                stack.push(number1 + number2);
            }
            else if (tokens[i].equals("-")) {
                checkValid(stack);
                int number1 = stack.pop();
                int number2 = stack.pop();
                stack.push(number2 - number1);
            }
            else if (tokens[i].equals("*")) {
                checkValid(stack);
                int number1 = stack.pop();
                int number2 = stack.pop();
                stack.push(number1 * number2);
            }
            else if (tokens[i].equals("/")) {
                checkValid(stack);
                int number1 = stack.pop();
                int number2 = stack.pop();
                stack.push(number2 / number1);
            }
            else if (tokens[i].equals("!")) {
                if (stack.size() < 1) {
                    throw new IllegalArgumentException("Invalid input.\n");
                }
                int number = stack.pop();
                int result = 1;
                while (number > 0) {
                    result *= number;
                    number--;
                }
                stack.push(result);
            }
            else {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("The input in not valid.\n");
        }
        return stack.pop();
    }

    private void checkValid(Stack<Integer> stack) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("The input in not valid.\n");
        }
    }
}

57.
第一个是nested list sum I， 第二题shortest distanceII， 第三个是two sum interface，follow up是trade off 存储和判断two sum哪个多，降低调用的多的function的效率。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210994&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311



58.
是让完成两个method，addPoint(Point point)和findNearestKPoints(Point center, int k)。我就用了个arraylist，
实现了O(1)的addPoint，然后O(nlogk)的maxHeap实现了第二个method。我解释完思路就开始写了，本来早就写完了，
但是面试官让我讲了半天maintain这个maxHeap的algo是怎样work的，我感觉讲得很清楚了但却讲了好几次。
然后又让我把comparator的code简化一下又用了老半天。写完了也无bug但是却只做了一题，毕竟做题时间就30分钟还让我解释好久
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210814&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


59.
1 text justification，输入输出略有不同，基本做法一样。

2 factor combinations。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214239&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

60.
题目：Permutations I+II. 

61.
第一题shuffle array，我用了princeton 公开课的版本，
面试官觉得我是错的，给了我另一个版本的写法，然而本渣又是只记住了方法不知道怎么证明，
事后查了一下这不就是同一种算法不同方向shuffle吗，所以就没有然后了。。。. 1point 3acres 璁哄潧
第二题 upside down
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214200&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }
}

// prev 1
//     / \
//curr 2  3 <- temp
//    / \
//next4  5

public TreeNode upsideDownBinaryTree(TreeNode root) {
    TreeNode curr = root;
    TreeNode next = null;
    TreeNode temp = null;
    TreeNode prev = null;
    
    while(curr != null) {
        next = curr.left;
        
        // swapping nodes now, need temp to keep the previous right child
        curr.left = temp;
        temp = curr.right;
        curr.right = prev;
        
        prev = curr;
        curr = next;
    }
    return prev;
}  

class Shuffle {
    public int[] shuffle(int[] input) {
        Random random = new Random();
        for (int i = input.length - 1; i > 0; i--) {
            int index = random.nextInt(i);
            swap(input, index, i);
        }
        return input;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

62.
题目leetcode 236+254，236

class FindLCA {
    public TreeNodeWithParent findLCA(TreeNodeWithParent root, TreeNodeWithParent a, TreeNodeWithParent b) {
        if (root == null || a == null || b == null) {
            return null;
        }
        int depthA = depth(a);
        int depthB = depth(b);
        while (depthA > depthB) {
            a = a.parent;
            depthA--;
        }
        while (depthB > depthA) {
            b = b.parent;
            depthB--;
        }
        while (a != null && b != null) {
            if (a == b) {
                return a;
            }
            a = a.parent;
            b = b.parent;
        }
        return null;
    }

    private int depth(TreeNodeWithParent node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.parent;
        }
        return depth;
    }
}



63.
第一轮：(1) Number of Islands，(2) boolean canIWin(int maxNum, int target)，从1,2...maxNum的数组里两个玩家轮流选数，
第一个达到sum>=target的玩家获胜，问如何判断先选的玩家能获胜。数字可以重复取
然后第二题他一出我就傻了，因为我刷的时候看到这题真的就直接跳过了。. 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=102637&extra=page%3D8%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// use a hashmap to record every possible state of chose number
// state could be a string "10011" 1 means being chosen, 0 means not chosen
// time complexity: O(2^n)
public class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger * (maxChoosableInteger + 1) / 2 < desiredTotal) {
            return false;
        }
        if (desiredTotal <= 0) {
            return true;
        }
        HashMap<String, Boolean> win = new HashMap<>();
        boolean[] chose = new boolean[maxChoosableInteger + 1];
        return helper(desiredTotal, win, chose);
    }
    
    private boolean helper(int desiredTotal, HashMap<String, Boolean> win, boolean[] chose) {
        if (desiredTotal <= 0) {
            return false;
        }
        String key = format(chose);
        if (win.containsKey(key)) {
            return win.get(key);
        }
        for (int i = 1; i < chose.length; i++) {
            if (!chose[i]) {
                chose[i] = true;
                if (!helper(desiredTotal - i, win, chose)) {
                    win.put(key, true);
                    chose[i] = false;
                    return true;
                }
                chose[i] = false;
            }
        }
        win.put(key, false);
        return false;
    }
    
    private String format(boolean[] chose) {
        StringBuilder result = new StringBuilder();
        for (boolean b : chose) {
            if (b) {
                result.append("1");
            }
            else {
                result.append("0");
            }
        }
        return result.toString();
    }
}

第二轮：(1) Same Tree， (2) Word Ladder 1.5 只输出一条最短路径. 
好了第二题也是在面经里有，但是自己只做了Word Ladder 1 & 2，在面试的时候被要求不能用Word Ladder 2 的解法来求一条最短路径，然后就没想出来怎么做。
现在我们再来看另一个帖子
http://www.1point3acres.com/bbs/thread-200217-1-1.html
第二轮是不是和我的题目一模一样？我可以告诉你面我的那两个人都是同两个人。一个老中一个韩国白胖小哥。
所以他们套路很明确，固定人员搭配和固定题目。因为他们都是问同一个题目，所以哪个解法好哪个解法差他们心里清楚的很，如果不想被刁难，最好查好最优解。
比如说我那个Word Ladder的题目，面试的人竟然和我说用'a' -> 'z' 替换的方法复杂度太高，叫我优化，我说了Trie，但是似乎不是他想要的答案。。。
其中那个韩国白胖子全程对复杂度各种不满。


首先我们先看下另一位地里网友的无私分享。我就是按照这位网友的帖子刷的题目。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198750&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3086%5D%5Bvalue%5D%3D6%26searchoption%5B3086%5D%5Btype%5D%3Dradio%26searchoption%5B3087%5D%5Bvalue%5D%3D3%26searchoption%5B3087%5D%5Btype%5D%3Dradio%26searchoption%5B3089%5D%5Bvalue%5D%5B3%5D%3D3%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=209605&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

64.

1. max depth of a binary tree

2. search in a rotated sorted array

2.5 search in a rotated sorted array II

这两道题都问了我用什么test cases测，我说了 一般的case 再加上所有能想到的corner cases.1point3acres缃�

第二题面试官问我input array 是null怎么处理，我说我用的C++ 所以null就是empty array。. 1point 3acres 璁哄潧

面试官最后还问了我服务器cache用什么数据结构实现，我一开始说priority queue，后来在面试官的帮助下答出了hashmap
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=214016&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    public int searchRotated(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] >= nums[left]) {
                if (target >= nums[left] && target <= nums[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                if (target <= nums[right] && target >= nums[mid]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}


public class Solution {
    public boolean searchRotated2(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        //check each num so we will check start == end
        //We always get a sorted part and a half part
        //we can check sorted part to decide where to go next
        while(start <= end){
            int mid = start + (end - start)/2;
            if(nums[mid] == target) return true;
            
            //if left part is sorted
            if(nums[start] < nums[mid]){
                if(target < nums[start] || target > nums[mid]){
                    //target is in rotated part
                    start = mid + 1;
                }else{
                    end = mid - 1;
                }
            }else if(nums[start] > nums[mid]){
                //right part is rotated
                
                //target is in rotated part
                if(target < nums[mid] || target > nums[end]){
                    end = mid -1;
                }else{
                    start = mid + 1;
                }
            }else{
                //duplicates, we know nums[mid] != target, so nums[start] != target
                //based on current information, we can only move left pointer to skip one cell
                //thus in the worest case, we would have target: 2, and array like 11111111, then
                //the running time would be O(n)
                start ++;
            }
        }
        
        return false;
    }
}

public class Solution {
    public int findMinInRotated(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[nums.length - 1]) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        return nums[high] > nums[low] ? nums[low] : nums[high];        
    }
}



65.
两轮coding（都是题库题，但是会问各种细节，边界条件，为什么这么做，最后还跑了test case）：
1LCA（和lc不太一样，是那种自己带父节点指针的那种）
2重复dna
3求平方根
4数据流中返回最大的k个数. Waral 鍗氬鏈夋洿澶氭枃绔�,
系统设计：
设计日历（不用考虑重复事件，用户少）
tech communication，hiring manager和lunch全程聊天+behavior
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=204207&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

66.
1. Shortest Word Distance II + III - Leetcode 244, 245的集合，函数会被调用多次，也会存在word1 = word2的情况。
2. Nested List Weight Sum - Leetcode 339
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=213857&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

67.
2. Java里final，finally， finallize分别解释， cc150有详细介绍
what is garbage collector of java
还问了关于database的。什么是transaction

Final is a keyword.
Final is used to apply restrictions on class, method and variable. 
Final class can't be inherited, final method can't be overridden and final variable value can't be changed.	
Finally is a block.
Finally is used to place important code, it will be executed whether exception is handled or not.	
Finalize is a method.
Finalize is used to perform clean up processing just before object is garbage collected.

// finalize:
finalize() method In Java:

finalize() method is a protected and non-static method of java.lang.Object class. 
This method will be available in all objects you create in java. This method is used 
to perform some final operations or clean up operations on an object before it is removed from the memory.
you can override the finalize() method to keep those operations you want to perform before an object is destroyed. 
Here is the general form of finalize() method.

// GC: garbage collector:
The task of garbage collector thread is to sweep out abandoned objects from the heap memory. 
Abandoned objects or dead objects are those objects which does not have live references. 
Garbage collector thread before sweeping out an abandoned object, it calls finalize() method of that object.
After finalize() method is executed, object is destroyed from the memory.
That means clean up operations which you have kept in the finalize() method are executed before an object is destroyed from the memory.

Garbage collector thread does not come to heap memory whenever an object becomes abandoned. 
It comes once in a while to the heap memory and at that time if it sees any abandoned objects, 
it sweeps out those objects after calling finalize() method on them. 
Garbage collector thread calls finalize() method only once for one object.


// transcation
A transaction is a sequence of operations performed as a single logical unit of work. 
A logical unit of work must exhibit four properties, called the atomicity, consistency, 
isolation, and durability (ACID) properties, to qualify as a transaction.

3.超水的算法题
// input contains only a-z
// return a string sorted in alphabetically. Waral 鍗氬鏈夋洿澶氭枃绔�,
// input: zhcbca  output: abcchz
public String sortString(String input) {
    if (input == null || input.length() <= 1) return input;
    int[] count = new int[26];
    for (int i=0; i<input.length(); i++) {
        count[input.charAt(i)-'a']++;
    }
    StringBuilder sb = new StringBuilder();
    for (char c='a'; c <='z'; c++) {. From 1point 3acres bbs
        if (count[c-'a']==0) continue;
        for (int j = 0; j<count[c-'a']; j++)
            sb.append(c);
    }
    return sb.toString();
}


'4.design blockedQueue （忽略注释，注释是国人大哥后来讨论如何优化的时候写的）'
public interface BlockedQueue <T> {
  BlockedQueue(int capacity);. more info on 1point3acres.com
  void put(T data);
  T poll();
}

public class myBlockedQueue <T> implements BlockedQueue {
    private Queue<T> myQueue;
    private int capacity = 20;

    public myBlockedQueue(int capacity) {
        myQueue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void put (T data) 
            throws InterruptedException{
        while (myQueue.size() == capacity) {
            wait();  // this.wait();
        }. 1point3acres.com/bbs

        myQueue.offer(data);
        notifyAll();  // this.notifyAll(); notify one of threads blocked at poll();
    }

    public synchronized T poll() 
            throws InterruptedException {
        while (myQueue.size() == 0) {. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
            wait();  // this.wait();
        }

        T data = myQueue.poll();
        notifyAll();  // this.notifyAll();  notify one of threads blocked at put();

        return data;
    }
}.



interface BlockedQueue<T> {
    void put(T t) throws InterruptedException ;
    T poll() throws InterruptedException ;
}

class BlockedQueueInstance<T> implements BlockedQueue<T> {
    private Queue<T> queue;
    private int capacity;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;
    public BlockedQueueInstance(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }

    @Override
    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                System.out.println("Queue is full");
                notFull.await();
            }
            queue.offer(t);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T poll() throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("Queue is empty");
                notEmpty.await();
            }
            T result = queue.poll();
            notFull.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }
}

class BlockedQueueInstanceWithMultiWriter<T> {
    private Queue<T> queue;
    private int capacity;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;
    public BlockedQueueInstanceWithMultiWriter(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
    }
    
    public void put(List<T> ts) throws InterruptedException {
        lock.lock();
        try {
            for (T t : ts) {
                while (queue.size() == capacity) {
                    System.out.println("Queue is full");
                    notFull.await();
                }
                queue.offer(t);
                notEmpty.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
    
    public T poll() throws InterruptedException{
        lock.lock();
        try {
            while (queue.size() == 0) {
                System.out.println("Queue is empty");
                notEmpty.await();
            }
            T result = queue.poll();
            notFull.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }
}

class BlockedQueueWithSemaphore<T> implements BlockedQueue<T>{
    private Queue<T> queue;
    private Semaphore slots;
    private Semaphore mutex;
    private Semaphore empty;
    public BlockedQueueWithSemaphore(int limit) {
        this.queue = new LinkedList<>();
        this.slots = new Semaphore(limit);
        this.mutex = new Semaphore(1);
        this.empty = new Semaphore(0);
    }

    @Override
    public void put(T t) throws InterruptedException{
        slots.acquire();
        try {
            mutex.acquire();
            queue.offer(t);
            empty.release();
        } finally {
            mutex.release();
        }

    }

    @Override
    public T poll() throws InterruptedException{
        empty.acquire();
        try {
            mutex.acquire();
            T result = queue.poll();
            slots.release();
            return result;
        } finally {
            mutex.release();
        }
    }
}



http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210297&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


67.
就是面经里的那道nestedInteger, 只不过我的题目是个变形. 当时要求多加一层就是求得是ReverseDepthSum. 也就是层数越深，所获得的weight value越低. 举例就是 {{1,1},2,{1,1}}
the function should return 8 (four 1s at weight 1, one 2 at depth 2)
* Given the list {1,{4,{6}}} the function should return 17 (one 1 at weight 3, 
one 4 at depth 2, and one 6 at depth 1).先得走通一遍得到层数，然后对应上乘出来才可以得到结果.


http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=123288&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

68.
1. 判断一个字符串是不是合法的double数字，不用考虑科学计数法. 
2. 最大区间和
follow up. 最大区间积
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210042&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

69.
第一题243，244，245一起考。
第二题，Lowest Common Ancestor, 给parent指针。followup是尽早返回，而不是非要找到root，用set保留即可。
LC 138
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode mover = head;
        while (mover != null) {
            RandomListNode copy = new RandomListNode(mover.label);
            RandomListNode next = mover.next;
            mover.next = copy;
            copy.next = next;
            mover = copy.next;
        }
        RandomListNode fakeHead = new RandomListNode(0);
        RandomListNode copyMover = fakeHead;
        mover = head;
        while (mover != null) {
            if (mover.random != null) {
                mover.next.random = mover.random.next;
            }
            mover = mover.next.next;
        }
        mover = head;
        while (mover != null) {
            copyMover.next = mover.next;
            mover.next = mover.next.next;
            mover = mover.next;
            copyMover = copyMover.next;
        }
        return fakeHead.next;
    }
}


70.
Deepest comman ancestor (with parent nodes). LinkedIn这题都快问烂了...我说了用hashmap的方法，他说你能不能不用额外空间，
我想了一下又说了那个把两个节点调到一个高度的做法。
2. 实现一个Max Stack， 支持peekMax() 和popMax(). 很自然地用两个栈去做，但是这样popMax的时候很费时间。
然后我又加了一个stack存的是max value的index, 把stack全变成ArrayList, 然后就开始纠结pop了。。。结果corner case太多没写完。。。不过他也说这题要简单clean是比较难

71.
设计一个generic class，要求支持add(T), remove(T), randomRemove(T)三个操作，并且必须保证都是O(1)。比如说add(cat), add(dog), add(pig), add(cat), remove(cat), remove(dog)等等，
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=156459&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

72.
刚面完Linkedin电面，贴个面经。遇到的事国人哥哥，人特别好。我觉得我当时题没有做好但他也让我过了。 
1. 给一个数，求所有factor的组合 Eg. 4: (1, 4) (2, 2) 

2. 两个人玩游戏， 给n个数，1到n， 每个数只能选一次。 给一个target， 如果在你玩的那一轮pool里的sum达到或者超过target就算赢。两个人轮流选数加到pool里，求第一个玩的人怎么赢。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208671&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

73.
只写了一个longest palindrome subsequence, 一开始DP思路反了，花了很多时间改正，之后优化，只写了一个题，有可能是negative

74.
给两个单词， 比如head,  tail: 找到一个最短的转换，从head到tail，每
次只能变一个字母，path上的word都必须是有效的英文单词，我用的Graph shortest
path

第二个: memcpy:  源区域和目标区域可能有重叠
   BST 插入和删除操作实现
   BST iterator 实现
'3: 实现两个函数: H() and O(), 这两个函数会被多线程调用。当一个线程调用H或O时
，如果当前已经有至少两个线程call H和一个线程call O。那么让两个call H和一个
call O的线程返回（产生一个水分子），其他的都block。'

4: 'Given a social graph, find if there is a path between two persons with at
most 2 steps (3rd level connection), how to handle it in distributed way (
large graph stored at a large number of nodes, minimize cross-communication)'


class H2O {
    private Lock lock;
    private Condition enoughO;
    private Condition enoughH;
    private int countH;
    private int countO;
    public H2O() {
        this.lock = new ReentrantLock();
        this.enoughH = lock.newCondition();
        this.enoughO = lock.newCondition();
        this.countH = 0;
        this.countO = 0;
    }

    // cur -> true call from H()
    // cur -> false call from O()
    private boolean check(boolean cur) {
        if (countO >= 1 && countH >= 2) {
            countO -= 1;
            countH -= 2;
            System.out.println("H2O");
            enoughH.signal();
            if (cur) {
                enoughO.signal();
            }
            else {
                enoughH.signal();
            }
            return true;
        }
        return false;
    }

    public void H() throws InterruptedException{
        lock.lock();
        try {
            countH++;
            if (!check(true)) {
                enoughH.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void O() throws InterruptedException{
        lock.lock();
        try {
            countO++;
            if (!check(false)) {
                enoughO.await();
            }
        } finally {
            lock.unlock();
        }
    }
}


5: 设计题:  
a restful server with 4GB,  
given a request such as: http://seq=4?len=60?xxxxdata
the system will store the binary data with that sequence number.
given a request: http://startseq=3?maxLen=100, 
the system returns all data objects with sequence >= 3 with total data length less equal than 100.

multiple clients calling simutaneous
what data structure, concurrency, locking, etc..

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=99469&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

75.
code questions: given a stream of integer, randomly choose k elements from n, ensure each value among k has equal probability.  
Given a mutlidenmentional arrays, compute the sum of all values. Given API getValue(dn, dn-1.... d0) dn = index at denmention.

serialize and deserialize a BST
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=198457&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class ReserviorSample {
    public int[] smaple(Iterator<Integer> stream, int k) {
        int[] values = new int[k];
        int count = 0;
        while (count < k && stream.hasNext()) {
            values[count++] = stream.next();
        }
        if (count < k) {
            return values;
        }
        Random random = new Random();
        while (stream.hasNext()) {
            int index = random.nextInt(count + 1);
            int cur = stream.next();
            if (index <= k - 1) {
                values[index] = cur;
            }
            count++;
        }
        return values;
    }
}


76.
Question:Get the intersection of two sorted array. Input interface:
Iterable intersection(Iterator a, Iterator b);
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148493&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

77.
题目就是nested sum的reverse版本，一开始大概10分钟写完了以后，follow up叫用一次pass做，.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=210034&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

78.
 LC 199 binary tree right side view，改成了left side view，做法基本一样

public List<Integer> rightSideView(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    List<Integer> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
            if (i == size - 1) {
                res.add(temp.val);
            }
        }
    }
    return res;
}

79.
题目是Shortest Word Distance

followup: two word distance 改成three word distance, 目的是让三个word相互之间的distance和最小. 
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=205086&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

80.
第一面只有一道coding，给一个iterator，求mean和variance。follow up说如果数特别多存sum的话会溢出，怎么弄。
假设之前见过的值叫elements，那么. 1point 3acres 璁哄潧

mean = sum(elements)／ len(elements)
variance = sum([elem^2 for elem in elements]) / len(elements) - mean^2  

要实现的方法是用上一步的mean和variance，和一个新的element，求出来新的mean和variance。
newMean = mean / (n_elements + 1) * (n_elements) + new_element / (n_elements + 1)
newVariance = (variance - mean^2) / (n_elements + 1) * (n_elements) + new_element^2 / (n_elements + 1) - newMean^2. 

本质上就是用variance = E(X^2) - (E(X))^2 推一下，十分感谢之前分享的人！

然后对方说对NN不熟，就问了我熟悉什么regression model，然后问了为什么会overfitting和如何处理overfitting

. 鍥磋鎴戜滑@1point 3 acres
补充内容 (2016-11-4 09:00):
newVariance = (variance + mean^2) / (n_elements + 1) * (n_elements) + new_element^2 / (n_elements + 1) - newMean^2
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=208976&extra=page%3D3%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class GetMeanVar {
    public double[] get(Iterator<Double> stream) {
        int len = 0;
        double sum = 0;
        double squareSum = 0;
        double mean = 0;
        double variance = 0;
        while (stream.hasNext()) {
            double cur = stream.next();
            sum += cur;
            squareSum += cur * cur;
            len++;
        }
        mean = sum / len;
        variance = squareSum / len - mean * mean;
        return new double[]{mean, variance};
    }

    public double[] getWithLarge(Iterator<Double> stream) {
        int len = 0;
        double mean = 0;
        double variance = 0;
        if (stream.hasNext()) {
            double cur = stream.next();
            mean = cur;
            variance = cur * cur - mean* mean;
            len++;
        }
        while (stream.hasNext()) {
            double cur = stream.next();
            double newMean = mean * len / (len + 1) + cur / (len + 1);
            double newVar = (variance + mean * mean) * len / (len + 1) + cur * cur / (len + 1) - newMean * newMean;
            len++;
            mean = newMean;
            variance = newVar;
        }
        return new double[]{mean, variance};
    }
}



81.
Implement hash table. Follow up is multi-threading.鐣欏
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=200345&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

82.
第二题tree level traversal， follow up 是带间距的输出
/**
* Sample input:
*
*          1
*         / \
*        3   5. 鍥磋鎴戜滑@1point 3 acres
*       /   / \
*      2   4   7
*     / \   \
*    9   6   8
*
* Expected output:
*    1. 鐗涗汉浜戦泦,涓€浜╀笁鍒嗗湴
*    3 5
*    2 4 7
*    9 6 8
*    ==========
* follow up expected output 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
*          1
*        3   5
*      2   4   7
*    9   6   8
*/
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=190637&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class LevelPrintTree {
    public List<String> levelPrint(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int col = 0;
        List<List<Pair>> levels = new ArrayList<>();
        Queue<Pair> explore = new LinkedList<>();
        explore.offer(new Pair(0, root));
        while (!explore.isEmpty()) {
            int size = explore.size();
            List<Pair> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Pair pair = explore.poll();
                col = Math.min(col, pair.col);
                level.add(pair);
                if (pair.node.left != null) {
                    explore.offer(new Pair(pair.col - 1, pair.node.left));
                }
                if (pair.node.right != null) {
                    explore.offer(new Pair(pair.col + 1, pair.node.right));
                }
            }
            levels.add(level);
        }
        for (List<Pair> level : levels) {
            int lastIndex = col;
            StringBuilder levelOutput = new StringBuilder();
            for (Pair node : level) {
                int diff = (node.col - lastIndex - 1) * 2 + 1;
                if (diff > 0 && levelOutput.length() == 0) {
                    levelOutput.append(" ");
                }
                for (int i = 0; i < diff; i++) {
                    levelOutput.append(" ");
                }
                levelOutput.append(node.node.val);
                lastIndex = node.col;
            }
            result.add(levelOutput.toString());
        }
        return result;
    }

    class Pair {
        int col;
        TreeNode node;
        public Pair(int col, TreeNode node) {
            this.col = col;
            this.node = node;
        }
    }
}


83.
desig设计过去5min 1hr 24hr各类系统的exception-
http://www.mitbbs.com/article_t/JobHunting/33226795.html

84.
paint house 1&2

// paint house painthoues
public class Solution {
    public int minCost(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        int[][] result = new int[costs.length][costs[0].length];
        result[0][0] = costs[0][0];
        result[0][1] = costs[0][1];
        result[0][2] = costs[0][2];
        for (int i = 1; i < result.length; i++) {
            result[i][0] = Math.min(result[i - 1][1], result[i - 1][2]) + costs[i][0];
            result[i][1] = Math.min(result[i - 1][0], result[i - 1][2]) + costs[i][1];
            result[i][2] = Math.min(result[i - 1][0], result[i - 1][1]) + costs[i][2];
        }
        return Math.min(result[costs.length - 1][0], Math.min(result[costs.length - 1][1], result[costs.length - 1][2]));
    }
}

public class Solution {
    public int minCostII(int[][] costs) {
        if (costs.length == 0) {
            return 0;
        }
        for (int row = 1; row < costs.length; row++) {
            int[] minIndex = findMin(costs, row - 1);
            for (int col = 0; col < costs[0].length; col++) {
                if (col != minIndex[0]) {
                    costs[row][col] += costs[row - 1][minIndex[0]];
                }
                else {
                    costs[row][col] += costs[row - 1][minIndex[1]];
                }
            }
        }
        return costs[costs.length - 1][findMin(costs, costs.length - 1)[0]];
    }
    
    private int[] findMin(int[][] costs, int row) {
        int minIndex = 0;
        int secondIndex = 0;
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        for (int i = 0; i < costs[row].length; i++) {
            if (costs[row][i] <= min) {
                minIndex = i;
                min = costs[row][i];
            }
        }
        for (int i = 0; i < costs[row].length; i++) {
            if (costs[row][i] <= secondMin && i != minIndex) {
                secondIndex = i;
                secondMin = costs[row][i];
            }
        }
        return new int[]{minIndex, secondIndex};
    }
}


85.
leaf print tree: 给一个树，每一轮把所有的叶子输出出来，并且假象拿掉，然后再次输出，再次假象拿掉叶节点，直到全树输出完成为止。要求不能改变树本身
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=182068&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


86.
问了skiplist。以前大概知道这个东西但是从来没了解过其implementatiion。先写了个查找的API，还比较好写，然后让写插入，于是就跪了。一直想着每一层insert node之后就把上下左右都连好，
但这样写着很复杂。最后没写出来，好像应该每一层插入新node时只连左右，然后用个stack存新node，最后再把stack里的node上下连起来。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=201663&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class SkipList {
    private Node head;
    private Node tail;
    private int height;
    private Random flip;
    public SkipList() {
        this.height = 1;
        this.flip = new Random();
        this.head = new Node(Integer.MIN_VALUE, 0);
        this.tail = new Node(Integer.MAX_VALUE, 0);
        this.head.isStart = true;
        this.tail.isEnd = true;
        this.head.next = tail;
        this.tail.prev = head;
    }

    public Integer search(int key) {
        Node result = searchHelper(key);
        return result == null ? null : result.val;
    }

    public void insert(int key, int val) {
        Node exist = searchHelper(key);
        if (exist != null) {
            while (exist != null) {
                exist.val = val;
                exist = exist.up;
            }
            return;
        }

        Stack<Node> lastNodes = new Stack<>();
        Node mover = head;
        while (true) {
            while (!mover.next.isEnd && mover.next.key <= key) {
                mover = mover.next;
            }
            lastNodes.push(mover);
            if (mover.down != null) {
                mover = mover.down;
            }
            else {
                break;
            }
        }
        int level = 1;
        Node node = new Node(key, val);
        Node last = lastNodes.pop();
        // node.next = last.next;
        insertNode(last, node);
        while (flip.nextInt(2) == 0) {
            if (level >= height) {
                Node newHead = new Node(Integer.MIN_VALUE, 0);
                Node newTail = new Node(Integer.MAX_VALUE, 0);
                newHead.next = newTail;
                newTail.prev = newHead;
                newHead.isStart = true;
                newTail.isEnd = true;
                head.up = newHead;
                newHead.down = head;
                tail.up = newTail;
                newTail.down = tail;
                head = newHead;
                tail = newTail;
                lastNodes.push(head);
                height++;
            }
            Node newNode = new Node(key, val);
            newNode.down = node;
            node.up = newNode;
            Node lastNode = lastNodes.pop();
            insertNode(lastNode, newNode);
            node = newNode;
            level++;
        }
    }

    public void remove(int key) {
        Node node = searchHelper(key);
        if (node == null) {
            System.out.println("no such key");
            return;
        }
        while (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
            node = node.up;
        }
    }

    private void insertNode(Node last, Node node) {
        node.next = last.next;
        last.next.prev = node;
        node.prev = last;
        last.next = node;
    }

    private Node searchHelper(int key) {
        Node mover = head;
        while (true) {
            while (!mover.next.isEnd && mover.next.key <= key) {
                mover = mover.next;
            }
            if (mover.down != null) {
                mover = mover.down;
            }
            else {
                break;
            }
        }
        if (!mover.isEnd && !mover.isStart && mover.key == key) {
            return mover;
        }
        return null;
    }

    class Node {
        Node prev;
        Node next;
        Node up;
        Node down;
        int val;
        int key;
        boolean isEnd;
        boolean isStart;
        public Node(int key, int val) {
            this.isEnd = false;
            this.isStart = false;
            this.key = key;
            this.val = val;
        }
    }
}

87.
其实就是leetcode上的题，求整数x的平方根，当时不该嘴贱问一句，结果是不是也要求整数. 1point3acres.com/bbs
然后那个面试官说，她本来想的是结果也要求整数的，但看你这么问，那我们保留两位小数如何
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=200410&extra=page%3D4%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

88.
Single Valid Tree，这个题刚查了一下似乎只有career cup上有，之前我没有见过，因为大家（包括给我refer的朋友，还有我自己看面经）
都说linkedin有题库重复度特高，所以我最后一周也主要是以准备linkedin题库里的题，和system design为主。这个题我是用hash做的。
我是先列出了三种反例的test cases，然后大概写code（面试官说主要看思路，code不用写得很详细）。
国人大哥从一开始就似乎比较不喜欢hash的做法，一直觉得这个不work，一直质疑。我第一次写完也没有后续再遍历，用自己的test 
cases检验的时候竟然也没发现对一种特例（有环，而且不联通）不work，后来是白人小哥发现了这个问题，
但是已经没有时间了不让继续做了就让问问题，但我还是说可以遍历一遍看是否有环（其实更好的说法是看遍历一遍是否能遍历到所有的点），
国人大哥说即便这个work你这个太麻烦了，用了很多extra space还要很多特殊判断。。。问问题吧

Convex hull： 真的是平生第一次看到这个题，看到之后傻了半天，后来给了O(n^2)的算法，manager问time complexity，然后说有更好的算法O(nlogn)。
我说看起来像divide-and-conquer，可是也没有想明白具体怎么做。我说能给点提示吗？manager说他也不确定这样work不work。然后说不能给提示，但是过了5秒，
manager自己走到白板前，刷刷刷的从头到尾把算法讲出来了。。。这几天在家看书发现好几本算法书上都有讲这个（不过都在很后面的章节），愧叹还是用功不到位！

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146970&extra=page%3D5%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class SingleValidTree {
    public boolean isValid(List<GraphNode> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return true;
        }
        HashMap<GraphNode, Integer> inDegree = new HashMap<>();
        for (GraphNode node : nodes) {
            if (!inDegree.containsKey(node)) {
                inDegree.put(node, 0);
            }
        }
        for (GraphNode node : nodes) {
            for (GraphNode neighbor : node.friend) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }
        Queue<GraphNode> explore = new LinkedList<>();
        Set<GraphNode> visited = new HashSet<>();
        for (GraphNode node : nodes) {
            if (inDegree.get(node) > 1) {
                return false;
            }
            else if (inDegree.get(node) == 0) {
                explore.offer(node);
            }
        }
        if (explore.size() >= 2 || explore.size() == 0) {
            return false;
        }
        while (!explore.isEmpty()) {
            GraphNode node = explore.poll();
            for (GraphNode neighbor : node.friend) {
                if (visited.contains(neighbor)) {
                    return false;
                }
                explore.offer(neighbor);
                visited.add(neighbor);
            }
        }
        return visited.size() == nodes.size();
    }
}


// convex hull
// http://www.geeksforgeeks.org/convex-hull-set-2-graham-scan/
// find the most left buttom point, 
// find all the remain points that three of them form a counter clockwise line
class ConvexHull {
    public List<Point> convexHull(Point[] points) {
        List<Point> hull = new ArrayList<>();
        if (points == null && points.length == 0) {
            return hull;
        }
        int yMin = points[0].y;
        int minIndex = 0;
        for (int i = 1; i < points.length; i++) {
            if (points[i].y < yMin || (points[i].y == yMin && points[i].x < points[minIndex].x)) {
                yMin = points[i].y;
                minIndex = i;
            }
        }
        Point temp = points[minIndex];
        points[minIndex] = points[0];
        points[0] = temp;

        Arrays.sort(points, 1, points.length, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int orientation = orientation(points[0], p1, p2);
                if (orientation == 1) {
                    return -1;
                }
                else if (orientation == 2) {
                    return 1;
                }
                return 0;
            }
        });
        int maxIndex = 1;
        for (int i = 1; i < points.length; i++) {
            while (i < points.length - 1 && orientation(points[0], points[i], points[i + 1]) == 0) {
                i++;
            }
            points[maxIndex++] = points[i];
        }
        if (maxIndex < 3) {
            return hull;
        }
        Stack<Point> stack = new Stack<>();
        for (int i = 0; i < 3; i++) {
            stack.push(points[i]);
        }
        for (int i = 3; i < maxIndex; i++) {
//            prev = nextTop(stack);
//            cur = stack.peek();
//            next = points[i];
            while (orientation(nextTop(stack), stack.peek(), points[i]) != 1) {
                stack.pop();
            }
            stack.push(next);
        }
        while (!stack.isEmpty()) {
            hull.add(stack.pop());
        }
        return hull;
    }

    private Point nextTop(Stack<Point> stack) {
        Point top = stack.pop();
        Point result = stack.peek();
        stack.push(top);
        return result;
    }
    // linear -> 0
    // p, q, r form a counterClock -> 1
    // p, q, r form a clockwise -> 2
    // k1 = (p.y - q.y) / (p.x - q.x) k2 = (q.y - r.y) / (q.x - r.x)
    // k2 == k1 -> linear
    // k2 > k1 -> counterClockWise, turn left
    // k2 < k1 -> clockWise, turn right
    private int orientation(Point p, Point q, Point r) {
        int value = (p.y - q.y) * (q.x - r.x) - (q.y - r.y) * (p.x - q.x);
        if (value == 0) {
            return 0;
        }
        return value > 0 ? 2 : 1;
    }

}


89.
有n个学生（编号为sid = [1..n]）依次走进有n个锁柜的房间（锁的编号为lockid = [1..n])，
该学生将会打开或锁上lockid可以被sid整除的锁，写一个打印所有在第n个学生操作后处于打开的状态的lockid。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=192087&extra=page%3D5%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class OpenLocker {
    // all the number which has odd number of factors
    // which is squera number
    public List<Integer> openLocker(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int root = (int)Math.sqrt(i);
            if (root * root == i) {
                result.add(i);
            }
        }
        return result;
    }
}

90.
'implement singleton pattern，要注意constrctor是private'

class SingletonClass {
    private static SingletonClass instance = null;
    private SingletonClass(){}
    public static SingletonClass getInstance() {
        if (instance == null) {
            instance = new SingletonClass();
        }
        return instance;
    }

    public void method() {
        System.out.println("this is singleton class");
    }
}

class SingletonInitiator {
    public SingletonClass getSingleton() {
        // SingletonClass.getInstance().method();
        return SingletonClass.getInstance();
    }
}

91.
然后来了个linked list找intersection~本来窃以为不要太简单~没想到他居然无数个follow-up~汗~~
分情况讨论：
两个没有环，不想交
两个没有环，相交
两个有环，不想交
两个有环，相交
http://www.1point3acres.com/bbs/thread-143335-1-1.html

92.
public interface PointsOnAPlane {
    /**
     * Stores a given point in an internal data structure-google 1point3acres
     */
    void addPoint(Point point);

    /**
     * For given 'center' point returns a subset of 'p' stored points
     * that are closer to the center than others.
     *. 鍥磋鎴戜滑@1point 3 acres
     * E.g.
     * Stored:
     * (1, 1)
     * (0, 3)
     * (0, 4)
     * (0, 5)
     * (0, 6)
     * (0, 7) 
     *
     * findNearest(new Point(0, 0), 3) -> (1, 1), (0, 3), (0, 4)
     */
     n * log p

    Collection<Point> findNearest(Point center, int p);

    static class Point ｛
        final int x;. 
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        } 
    }
}
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=171927&extra=page%3D5%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
93.
一题是isIntegerSquareRoot(int n)，就是判断一个数有没有整数的square root
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=168500&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

94.
Blocking Bounded Queue
实现一个线程安全的put和get，这个之前看面经的时候练习过，不管怎么样写出来了 － － 
Follow up是 multi-put，这个没写出来，之后的30分钟都是在纠结Mutex和condition variable，
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=166399&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

95.
ind range of a number in an array with possible duplicates, 我写的其实有bug, 但是小哥欣然放过，感谢。
2.2 find all palidrome string by deleting any letter from the given string. 这题比较难，我只做了dfs的bf解, 
稍微加了个map trim branch一下。最优解在mitbbs有讨论，大家自己坐电梯去看
http://www.mitbbs.com/article/JobHunting/33053715_3.html
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=145037&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }
        int start = binarySearch(nums, target);
        if (start == nums.length || nums[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, binarySearch(nums, target + 1) - 1};
    }
    
    private int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid;
            }
            else {
                low = mid;
            }
        }
        if (nums[high] < target || nums[low] > target) {
            return high + 1;
        }
        else if (nums[low] == target) {
            return low;
        }
        return high;
    }
}

class FindPalindrome {
    public List<String> find(String input) {
        HashMap<Character, int[]> prevLookup = new HashMap<>();
        HashMap<Character, int[]> nextLookup = new HashMap<>();
        for (char letter : input.toCharArray()) {
            if (!prevLookup.containsKey(letter)) {
                prevLookup.put(letter, new int[input.length()]);
                nextLookup.put(letter, new int[input.length()]);
            }
        }
        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            for (char ch : prevLookup.keySet()) {
                if (ch == letter) {
                    prevLookup.get(ch)[i] = i;
                }
                else {
                    if (i == 0) {
                        prevLookup.get(ch)[i] = -1;
                    }
                    else {
                        prevLookup.get(ch)[i] = prevLookup.get(ch)[i - 1];
                    }
                }
            }
        }
        for (int i = input.length() - 1; i>= 0; i--) {
            char letter = input.charAt(i);
            for (char ch : nextLookup.keySet()) {
                if (ch == letter) {
                    nextLookup.get(ch)[i] = i;
                }
                else {
                    if (i == input.length() - 1) {
                        nextLookup.get(ch)[i] = -1;
                    }
                    else {
                        nextLookup.get(ch)[i] = nextLookup.get(ch)[i + 1];
                    }
                }
            }
        }
        List<String> result = new ArrayList<>();
        helper(result, 0, input.length() - 1, "", prevLookup, nextLookup);
        return result;
    }

    private void helper(List<String> result, int left, int right, String half,
                        HashMap<Character, int[]> prevLookup, HashMap<Character, int[]> nextLookup) {
        if (half.length() > 0) {
            result.add(half + (new StringBuilder(half)).reverse().toString());
        }
        if (left > right) {
            return;
        }
        for (char ch : prevLookup.keySet()) {
            int mostLeft = nextLookup.get(ch)[left];
            int mostRight = prevLookup.get(ch)[right];
            if (mostLeft != -1 && mostLeft <= right) {
                result.add(half + ch + (new StringBuilder(half)).reverse().toString());
                if (mostLeft < mostRight) {
                    helper(result, mostLeft + 1, mostRight - 1, half + ch, prevLookup, nextLookup);
                }
            }
        }
    }
}

96.
Update a set of (upto) K elements, when you see a new element from an incoming stream,
 to ensure that every element seen so far, has an equal chance of making it into the set of K elements that we are maintaining.

// newElem is the N+1th element. 1point3acres.com/bbs

stream = [1, 2, 3, 4, 5, 6, 7]
K = 1. 

currentK = Any of the 7C1 possible combinations, e.g., 1 or 2 or 3 ...

newElem = 8
currentK = Any of the 8C1 possible combinations

public elem[] updateK( int K, int N, elem newElem, elem[] currentK) {
}

Example:

K = 2;
elem = [1,2,3,4,5,....]

for (int i = 0; i < N; i++) 
    currentK = updateK(K, i, elem[i], currentK);

这题太confused了, 楼主读题和理解题意用了二十分钟。。。。。。 
然后开始code, 我新建了一个 数组等于 stream, 然后加入新的newElem, 组成新的要操作的stream。然后用DFS，产生新的combination序列。
然后三哥说  stream could not be accessed。我心想，我去。。。。 
我说可以用从 currentK 里边不断读取元素，recover 这个 stream 里边的元素。用一个Hashtable 存元素，如果Hashtable size 等于N，就停止。然后做DFS产生新序列。
三哥说，make sense. 但是 我忘了说一个前提，N个元素是内存存不下的。。。。。。  我心想，我日了狗了。。。。
最后三哥告诉了我答案，这个可以用 reservoir sampling. 具体的可以Google 维基百科，就是通过有概率证明，N 足够大的话，随机选 N-k 就能实现了。这是coding 题么

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=157922&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


97.
'问题是DelayedSchduled，Java来实现。'
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=158571&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

98.
Given an array of positive integers and two players. In each turn, one player picks up one number and 
if the sum of all the picked up numbers is greater than a target number, the player wins. Write a program canIWin() to print the result.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=102721&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

99.
Given  a root of a tree. The tree may be of any depth and width. 
Transform it in a way that each node(except probably one) would either have N or 0 children
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=106498&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

100.
minimum window substring, 有点小bug,基本秒杀, follow up: 如果string 很长很长怎么办， test cases怎么写

101.
第一个是计算器，类似leetcode上的，第二个是求下一个二进制为0，1间隔的数，如10（1010），16（10000），17（10001），18（10010），20（10100），
要过大数据，考察bit操作
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=139015&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// if current position is not avaliable, if it is 1, make it 0, move left
class NextNumber {
    public int next(int input) {
        int mask = -1;
        for (int i = 0; i <= 32; i++) {
            if (((input >>> i) & 1) == 0 && ((input >>> (i + 1)) & 1) == 0) {
                input += (1 << i);
                return input;
            }
            else if (((input >>> i) & 1) == 1) {
                input = input & (mask << (i + 1));
            }
        }
        return input;
    }
}

102.
 Implement Hash Table 我记得有 实现Put, Get Method。要求避免Collision。
2. Hop Iterator, 类似于这个
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=160428&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MyHashMap<T, V> {
    private List<Pair>[] entries;
    public MyHashMap() {
        this.entries = new List[31];
        for (int i = 0; i < 31; i++) {
            this.entries[i] = new LinkedList<Pair>();
        }
    }

    public void put(T key, V value) {
        synchronized(entries) {
            int hashcode = key.hashCode() % 31;
            for (Pair other : entries[hashcode]) {
                if (other.key.equals(key)) {
                    other.value = value;
                    return;
                }
            }
            entries[hashcode].add(new Pair(key, value));
        }
    }
    
    public V get(T key) {
        synchronized(entries) {
            int hashcode = key.hashCode() % 31;
            for (Pair other : entries[hashcode]) {
                if (other.key.equals(key)) {
                    return other.value;
                }
            }
        }
        return null;
    }
    
    class Pair {
        T key;
        V value;
        public Pair(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}


class HopIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private int hop;
    private boolean first;
    public HopIterator(int hop, Iterator<Integer> iterator) {
        this.hop = hop;
        this.iterator = iterator;
        this.first = true;
    }

    @Override
    public boolean hasNext() {
        if (first) {
            first = false;
            return iterator.hasNext();
        }
        int count = hop;
        while (count > 0 && iterator.hasNext()) {
            iterator.next();
            count--;
        }
        return iterator.hasNext();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }
}


103.
1) For an application, it has 2 configurations, a global configuration and a app configuration, 
a configuration is just a map with key and value. If the app configuration has some keys that 
exist in the global configuration, it should overwrite the value in the global configuration. 
write a function to return the final configuration. 
follow up: if value of some keys has references: e.g.
key1: $key2
key2: $key3
key3: "foo"
rewrite the function that return the final configuration, resolve all the references. 
Notice that assume global configuration has no knowledge about app configuration, 
so references in global config cant refer to values in app config
2) for each library, it has some dependency:
"foo": ["bar", "abc"]
"bar": ["abc", "cde"]
....
give you the dependency map, and a query library, return all the dependent libraries of the query library.
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148877&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class WriteConfiguration {
    public void rewrite(HashMap<String, String> global, HashMap<String, String> local) {
        for (String localKey : local.keySet()) {
            if (global.containsKey(localKey)) {
                global.put(localKey, local.get(localKey));
            }
        }
    }

    public HashMap<String, String> rewriteWithReference(HashMap<String, String> global, HashMap<String, String> local) {
        HashMap<String, Set<String>> graph = new HashMap<>();
        Set<String> root = new HashSet<>();
        for (String globalKey : global.keySet()) {
            if (!graph.containsKey(globalKey)) {
                graph.put(globalKey, new HashSet<>());
                root.add(globalKey);
            }
        }
        for (String globalKey : global.keySet()) {
            if (global.containsKey(global.get(globalKey))) {
                graph.get(global.get(globalKey)).add(globalKey);
                root.remove(globalKey);
            }
        }
        Queue<Pair> explore = new LinkedList<>();
        for (String globalKey : root) {
            explore.offer(new Pair(globalKey, global.get(globalKey)));
        }
        HashMap<String, String> result = new HashMap<>();
        while (!explore.isEmpty()) {
            Pair pair = explore.poll();
            String value = pair.value;
            if (local.containsKey(pair.key)) {
                value = local.get(pair.key);
            }
            result.put(pair.key, value);
            for (String next : graph.get(pair.key)) {
                explore.offer(new Pair(next, value));
            }
        }
        return result;
    }

    class Pair {
        String value;
        String key;
        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}


104.
2. 中国大哥（斯坦福毕业的大牛）+加拿大白人（步步追问，不问出最优设计不甘心） system design http://www.1point3acres.com/bbs/thread-147555-1-1.html 第三题 
3. 俩白人小哥，很幽默。。。气氛轻松活跃 system design http://www.1point3acres.com/bbs/thread-147555-1-1.html 第一题 . visit 1point3acres.com for more.

午饭.鐣欏璁哄潧-涓€浜�-涓夊垎鍦�


4. coding， 三哥， 判断两个tree是否相同，如何用多线程去解决？（答案：Thread pool，先甩题目，然后问对Java多线程熟不熟，答曰不，就直接换下一题了）
maximum points in a line. 这题答得不好。用的HashMap<Double, Integer>去做的，面试官直接问了这里Double可不可以做hashmap的key，
答曰会有斜率误差，然后问了如何不算斜率来做（没想出好的方法），中间还讨论了double可以不可以拿“==”判断的事情，
我说C里面一般用 abs(a-b) < epsiplon判断，Java里面我不清楚，直接用“==”没出过啥问题（这里答错了，其实是一样的。。。，会有问题）。 
最后还是让用HashMap<Double, Integer>去做了。面完完后查了一下用HashMap用Double是没问题的 （
hashcode已经做过处理了，可以google），但是算斜率可能会出现误差导致不在一条线上的最后到一条线上去了，所以最好还是不要用除法算斜率的方法做
5. coding，小三哥。 给定一个abstract class，实现其中的3个function加constructor （题目不难，但是一整个白板都写不下。。。）
用户可以load一个file（给定的函数），这个file里存的是一个1024 block （每个block 大小 1024byte）的数组，完成以下函数：. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
1.返回一个没用过的block的位置 （自己定义位置）
2.写函数：往这个block里写数据
3.delete函数，删除某个位置的block，使其重新可用
用户操作完以后会自动调用finish函数，将数组写入文件。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=159920&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

105.
'1. What is write-through cache? What is write-back (copy-back) cache? 
2. Stack vs Heap, which is allocated in run time, which is in complile time.
什么是thread什么是process，有什么区别，做一个browser每个tab是用thread还是process。
什么是virtual memory，有什么好处和坏处。
知不知道semaphore，有没有用过。
Difference between ArrayList and LinkedList
1. process thread 区别
2. stack heap区别. 
3. 什么是virtual memory.
4. 两个thread抢mutex时候具体发生了什么
2. Difference between thread and process'
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146199&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// Thread and Process
The processes and threads are independent sequences of execution, 
the typical difference is that threads run in a shared memory space,
while processes run in separate memory spaces.

A process has a self contained execution environment that means it
has a complete, private set of basic run time resources purticularly 
each process has its own memory space. Threads exist within a process 
and every process has at least one thread.

Each process provides the resources needed to execute a program. 
Each process is started with a single thread, known as the primary thread. 
A process can have multiple threads in addition to the primary thread.

106.
第三题有点tricky. 
// [ 1, -2, 3, 4, -5, 6 ], 12 (3 *4) => true
//                       , 20 => false
//                       , -20 (4 * -5) => true
.1point3acres缃�
. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
意思是说given an array and the target, find the subarray with the product equals to the target number. If yes, return true; else return false;

这题只是让说一下思路。。一开始没想出来，提示了一下。。
其实可以先不考虑正负，维护一个窗。。这样子一路向下扫结果肯定越来越大。。我们还需要维护窗里面现在是正还是负数。。如果刚好等于　并且考虑正负，就返回true.　 如果小于，
就从后面减小窗直到相等或者小于。。这样子可以做到O(n) 时间。。。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=148791&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// use fast and slow pointer to maintain a window
// check absolute value of product
// if samller than target, keep times array[fast++]
// if equals to 0, check target, if also 0, return true
// else move window forward
// when bigger or equale, compare to target, if equals, find it
// else move slow pointer forwards
class ProductSubarray {
    public boolean subarrayTarget(int target, int[] input) {
        if (input == null || input.length == 0) {
            return false;
        }
        int cur = input[0];
        int slow = 0;
        int fast = 1;
        while (slow < fast || fast < input.length) {
            if (slow == fast) {
                fast++;
                cur = input[slow];
            }
            while (cur != 0 && fast < input.length && Math.abs(cur) < target) {
                cur *= input[fast++];
            }
            if (cur == 0) {
                if (target == 0) {
                    return true;
                }
                else if (fast < input.length){
                    slow = fast;
                    fast = slow + 1;
                    cur = input[slow];
                }
                else {
                    return false;
                }
            }
            else if (cur == target) {
                return true;
            }
            else {
                cur /= input[slow++];
            }
        }
        return false;
    }
}

107.
bounded blocking queue, 这道题有个extension，希望大家注意，他要求你实现multiplewriter()，就是说，如果一个writer一次想写入more than 1
 element怎么做，注意，如果当时没有空间写，这个writer需要在那里等待reader，同时block其它writer。我当时没准备好，纠结了半天，其实很好handle。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=156169&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

108.
Given a tree string expression in balanced parenthesis format:
[A[B[C][D]][E][F]]
Construct a tree and return the root of the tree.
                A. 1point3acres.com/bbs
            /   |  \
          B    E   F
         / \
       C   D
两天后通知onsite. 发来攒人品，onsite后一定发详细面经。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=143522&extra=page%3D6%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class BuildTree {
    public NaryTreeNode build(String input) {
        if (input == null || input.length() <= 2) {
            return null;
        }
        Pair pair = helper(input, 1);
        return pair.node;
    }

    private Pair helper(String input, int pos) {
        NaryTreeNode node = null;
        String value = "";
        while (input.charAt(pos) != ']' && input.charAt(pos) != '[' && Character.isDigit(input.charAt(pos))) {
            value += input.charAt(pos);
            pos++;
        }
        if (!value.equals("")) {
            node = new NaryTreeNode(Integer.parseInt(value));
        }
        else {
            return new Pair(node, pos);
        }
        while (pos < input.length()) {
            if (input.charAt(pos) == '[') {
                Pair pair = helper(input, pos + 1);
                node.children.add(pair.node);
                pos = pair.pos + 1;
            }
            else {
                break;
            }
        }
        return new Pair(node, pos);
    }

    class Pair {
        NaryTreeNode node;
        int pos;
        public Pair(NaryTreeNode node, int pos) {
            this.node = node;
            this.pos = pos;
        }
    }
}

109.
给一个路径，删掉所有路径下创建时间小于某个时间的file，如果一个目录下面所有的文件都被删了，也删掉这个文件夹。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=146421&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

110.
1. Given a target and an array, return the number of combinations from the array that can add to the given number 写了之后他说array里可以有负数，
于是稍微改了一下就好
2. Given a string and some characters, return the minimum window in the string that contains all characters. 注意characters里可以有重复。反正我就傻了，
他也没给提示，我到最后也没想出最佳解法……
3. Compare if two binary trees are the same 我也不知道为何他最后给我出了道那么简单的……
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=145426&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311


public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if ((p == null && q != null) || (p != null && q == null) || (p != null && q != null && p.val != q.val)) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }
}

class CombinationSum {
    public int combination(int[] input, int target) {
        if (input == null || input.length == 0) {
            return 0;
        }
        Arrays.sort(input);
        return helper(new HashMap<Integer, HashMap<Integer, Integer>>(), 0, input, target);
    }

    private int helper(HashMap<Integer, HashMap<Integer, Integer>> map, int pos, int[] input, int sum) {
        if (map.containsKey(sum) && map.get(sum).containsKey(pos)) {
            return map.get(sum).get(pos);
        }

        if (!map.containsKey(sum)) {
            map.put(sum, new HashMap<>());
        }
        int count = 0;
        if (sum == 0 && pos != 0) {
            count++;
        }
        for (int i = pos; i < input.length; i++) {
            if (i != pos && input[i] == input[i - 1]) {
                continue;
            }
            count += helper(map, i + 1, input, sum - input[i]);
        }
        map.get(sum).put(pos, count);
        return count;
    }
}

111.
然后开始做题。第一题三哥出的，给了一堆server，然后让找出first available server，我想这不是first missing positive吗，leetcode原题，但L家面试题里从来没见过，虽然最近没 写，
但方法是记得的，但开始还是假装说最intuitive的办法就说sort，但肯定有更好的办法，容我想想！三哥很急的说，ok ok let me give you a hint, 
我当时就急了心中大喊我不要hint!我这演技已经是影后的水平了吗！大概三哥听到我心里的呐喊，就说我再等你想想。我哪还敢等，马上开始写。
写完过了test case，这时已经33分钟了。. 
英国人开始出第二题。他一开始先问我记得iterator吗，我想起准备过的什么nested integer iterator, merge array iterator,就说有点知识
，然后他说，写一个text file iterator, 每次return one line in the file...我就傻眼了，没见过啊，
而且我也忘了好reader的syntax了。想了一下我觉得可以有个temp string记录curr line, call next()的时候再call一下readline(), 
return temp之后，temp指向下一行。大概讲了一下思路就开始写，坑爹的是collabedit一直不能sync up，我自己写了半天，
他们还是最近的4,5行都看不到，三哥要换到stypi去，英国人说没时间换了凑合着吧，于是就是我写一段，自己存下来，refresh screen,再贴上去我写的，
写完过了几个case: null file, large file, grep(就是一直call next())，这时已经55分钟了，于是我开始问问题。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=132118&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
Implement a (Java) Iterable object that iterates lines one by one from a text file.. more info on 1point3acres.com

** A reference to a file. */
public class TextFile implements Iterable<String>
{
  public TextFile(String fileName) { // please implement this

  /** Begin reading the file, line by line. The returned Iterator.next() will return a line. */
  @Override
  public Iterator<String> iterator() { // please implement this-
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=94989&extra=page%3D8%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311





public class Solution {
    public int firstMissingPositive(int[] nums) {
        /*
        The key here is to use swapping to keep constant space and also make use
        of the length of the array, which means there can be at most n positive integers. 
        So each time we encounter an valid integer, find its correct position and swap. 
        Otherwise we continue.
        // ignore all the negative, > n
        // put the other value back to its order position A[A[i]-1]
        */
        if (nums.length == 0) {
            return 1;
        }
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1 && nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
            else {
                i++;
            }
        }
        i = 0;
        while(i < nums.length && nums[i] == i+1) i++;
        return i + 1;
    }
     private void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}

class FileReaderIterator {
    BufferedReader reader = null;
    public FileReaderIterator(String file) {
        try {
            this.reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public Iterator<String> getIterator() {
        int count = 1000;
        String line = null;
        List<String> buffer = new ArrayList<>();
        try {
            while (count > 0 && ((line = reader.readLine()) != null)) {
                buffer.add(line);
                count--;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return buffer.iterator();
    }
}




112.
接着第二个小哥一上来一道猛题，直接崩溃，思路全无，白白浪费了近30分钟
一颗树，树的长度深度任意，写个方法讲树转为完全N叉树，要求转化过后子节点不能是父节点的parent，可以是siblings

-google 1point3acres
     * A               A                 A                         A
     *  |               |                 |_B                       |_B. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
     *  |_B             |_B                  |_C                    |
     *     |            |  |                    |_D                 |_C
     *     |            |  |_D                     |_E              |
     *     |            |  |                          |_F           |_D
     *     |_C          |  |_E                           |_G        |
     *     | |_D        |    |_H                            |_H     |_E
     *     |    |_F     |                                           |
     *     |            |_C                                         |_F
     *     |_E            |                                         |
     *       |_G          |_F                                       |_G 鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
     *       |            |                                         |
     *       |_H          |_G                                       |_H

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=133459&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

113.
第二题 Find the size of longest subset of an array
注意是subset而不是subarray。不能改变order。所以[1, 2, 2, 0, 1]的longest palindrome subset是[1, 2, 2, 1]，应该返回4。
感觉就是leetcode Palindrome Partitioning的变形啊public static int longestPalindrome(int[] nums) {
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=140246&extra=page%3D7%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// find the longest common subsequence between the input and the reverse of the input
class LongestPalindromeSubsequence {
    public int longestPalindrome(String input) {
        int len = input.length();
        int[][] countLength = new int[len + 1][len + 1];
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= len; j++) {
                if (input.charAt(i - 1) == input.charAt(len - j)) {
                    countLength[i][j] = 1 + countLength[i - 1][j - 1];
                }
                else {
                    countLength[i][j] = Math.max(countLength[i - 1][j], countLength[i][j - 1]);
                }
            }
        }
        return countLength[len][len];
    }
}

114.
cubic root
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=132658&extra=page%3D8%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

// sqrt
// cube root
class FindRoot {
    public double findCubic(double x) {
        double start = Math.min(x, 0);
        double end = Math.max(x, 0);
        double e = 0.0000000000000001;
        while (true) {
            double mid = start + (end - start) / 2.0;
            if (Math.abs(mid * mid * mid - x) <= e) {
                return mid;
            }
            else if (mid * mid * mid > x) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
    }

    public double findSquareRoot(double x) {
        if (x <= 0) {
            return 0;
        }
        double start = 0;
        double end = x;
        double e = 0.0000000000000005;
        while (true) {
            double mid = start + (end - start) / 2.0;
            // System.out.println(Math.abs(mid * mid - x));
            if (Math.abs(mid * mid - x) <= e) {
                return mid;
            }
            else if (mid * mid > x) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
    }
}


115.
'1. Implement a semaphore. .
'C的语法想不起来，用Java写了，用了一些concurrency的关键词
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=111156&extra=page%3D8%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

116.
convert bst to doubly linked list
 find a balance point in an array, array不是sorted的，里面可能有负数。
解法也是从两边往中间搞，用两个数组left和right存原数组从左向右和从右向左的元素和。然后按index来比较left和right两个数组里的元素，有相等的就是balance point.
1， 给一个array，生产一个新的array，新array中的每个元素都是上一个array中除了这个位置之外其他的元素的乘积。
原题链接：http://www.geeksforgeeks.org/a-product-array-puzzle/

这个题从原始array的两边往中间搞，把乘的结果存起来，然后新的array利用左右的结果相乘得出，时间复杂度O(n).

http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=115893&extra=page%3D8%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class TreeToDoubleLinkedList {
    public DoubleLinkedList toDoubleLinkedList(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> inorder = new Stack<>();
        DoubleLinkedList fakeHead = new DoubleLinkedList(0);
        DoubleLinkedList previous = fakeHead;
        toLeft(inorder, root);
        while (!inorder.isEmpty()) {
            TreeNode node = inorder.pop();
            DoubleLinkedList curNode = new DoubleLinkedList(node.val);
            curNode.pre = previous;
            previous.next = curNode;
            previous = curNode;
            if (node.right != null) {
                toLeft(inorder, node.right);
            }
        }
        DoubleLinkedList head = fakeHead.next;
        // head.pre = previous;
        // previous.next = head;
        return head;
    }

    private void toLeft(Stack<TreeNode> inorder, TreeNode root) {
        while (root != null) {
            inorder.push(root);
            root = root.left;
        }
    }
}

public class Solution {
    // calculate the product from right to left then from left to right like :
    // left {nums[0], nums[0] * nums[1] ...}, right {...nums[len - 2] * nums[len - 1], nums[len - 1]
    //  result[i] = left[i - 1] * right[i + 1]
    public int[] productExceptSelf(int[] nums) {
        int[] right = new int[nums.length];
        int[] left = new int[nums.length];
        int[] result = new int[nums.length];
        int product = 1;
        for (int i = 0; i < nums.length; i++) {
            product *= nums[i];
            left[i] = product;
        }
        product = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            product *= nums[i];
            right[i] = product;
        }
        for (int i = 0; i < nums.length; i++) {
            int leftSide = i > 0 ? left[i - 1] : 1;
            int rightSide = i < nums.length -1 ? right[i + 1] : 1;
            result[i] = leftSide * rightSide;
        }
        return result;
    }
}


117.
flatten 4-direction doubly linked list.. 1point 3acres 璁哄潧
就是有上下左右四个方向的子node，每个子node也可以有上下左右四个方向的子node，要求把这玩意儿转换成正常的doubly linkedlist，O(1) space，所以不能recursive，因为有stack space。. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
当时就懵逼了，讨论了很久才搞清楚思路，基本没什么时间写code，于是挂了（当然是主观判断，也可能是吃饭时候菜叶塞牙缝了嘛哈哈，开个玩笑...）
. From 1point 3acres bbs
思路是这样：
1. 给定任意一个node，往left走到head，往右一直走到tail（这个操作要重复很多遍所以可以定义getHead getTail两个function）；
2. 从head开始走，判断有没有up node，有的话，从up node一直往左走到新的head，一直往右走到新的tail，把这个head链接到原来的tail，把旧的tail的值赋成新值；
3. 把up node和刚才那个node之间的up & down link断掉，有小trick，不细讲了；
4. 判断有没有down node，有的话，重复上面两步操作；
5. 操作结束或者本来就没有up down node的话就往下，一直走到那个被更新过很多遍的tail...-google 1point3acres
鏉ユ簮涓€浜�.涓夊垎鍦拌鍧�. 
当然不一定全都挪到tail，也可以挪到node.next，但个人觉得挪到tail比较好visualize。

这个是O(1) space, 基本上是O(N) time, 虽然getHead和getTail两个function可能跑很多遍，最终应该也还是O(N)吧？不过最后还讨论到这一步。同学们可以在此继续讨论。
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=222643&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

118.
Given three sorted arrays A[], B[] and C[], find 3 elements i, j and k from A, B and C respectively such that 
max(abs(A – B[j]), abs(B[j] – C[k]), abs(C[k] – A)) is minimized. Here abs() indicates absolute value.
Example : 
Input: A[] = {1, 4, 10}  B[] = {2, 15, 20} C[] = {10, 12} Output: 10 15 10。 10 from A, 15 from B and 10 from C.1point3acres缃�
Input: A[] = {20, 24, 100} B[] = {2, 19, 22, 79, 800} C[] = {10, 12, 23, 24, 119} Output: 24 22 23。24 from A, 22 from B and 23 from C
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=165529&extra=page%3D2%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

class MinDifference {
    public List<Integer> getMin(int[] A, int[] B, int[] C) {
        if (A == null || B == null || C == null || A.length == 0 || B.length == 0 || C.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = null;
        int min = Integer.MAX_VALUE;
        int posA = 0;
        int posB = 0;
        int posC = 0;
        while (posA < A.length && posB < B.length && posC < C.length) {
            int a = A[posA];
            int b = B[posB];
            int c = C[posC];
            int sumDiff = Math.abs(a - b) + Math.abs(b - c) + Math.abs(a - c);
            if (sumDiff < min) {
                min = sumDiff;
                result = Arrays.asList(a, b, c);
            }
            if (a <= b && a <= c) {
                posA++;
            }
            else if (c <= b && c <= a) {
                posC++;
            }
            else {
                posB++;
            }
        }
        return result;
    }
}

119.
linkedin 有两类用户，普通user和influencer，数据量都很大，写一个类，
要求O(1)的 get(user), set(user, type), getAllInfluencer. 我一开始用两个
hashset，问我有没更好的办法，后来问明白他其实就是想要bitset， 写出搞定


二题比较复杂，说有一个电子支票，用户输入一个float类型的数之后，把这个数转换成英文单词，比如123.45，转换成" one hundred twenty three and 45 / 100 “ 这种形式"
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=223667&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D6%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311

public class Solution {
    private String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};    
    private String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder result = new StringBuilder();
        int index = 0;
        while (num > 0) {
            if (num % 1000 != 0) {
                result.insert(0, makeNumber(num % 1000) + thousands[index] + " ");
            }
            index++;
            num /= 1000;
        }
        return result.toString().trim();
    }
    
    
    private String makeNumber(int num) {
        if (num == 0) {
            return "";
        }
        else if (num < 20) {
            return lessThan20[num] + " ";
        }
        else if (num < 100) {
            return tens[num / 10] + " " + (num % 10 == 0 ? "" : lessThan20[num % 10] + " ");
        }
        else {
            return lessThan20[num / 100] + " Hundred " + makeNumber(num % 100);
        }
    }
}

如果两个用户id有同样的邮箱说明是同一个人，input有共同邮箱的人们和n个id，输出有多少人，bfs或者unionfind。searchrange。

class FindSamePerson {
    public List<List<Integer>> find(HashMap<Integer, List<String>> contacts) {
        if (contacts == null || contacts.size() == 0) {
            return new ArrayList<>();
        }
        HashMap<Integer, Integer> personToRoot = new HashMap<>();
        HashMap<String, Integer> emailToPerson = new HashMap<>();
        for (int person : contacts.keySet()) {
            personToRoot.put(person, person);
            int curRoot = person;
            for (String email : contacts.get(person)) {
                if (!emailToPerson.containsKey(email)) {
                    emailToPerson.put(email, person);
                    continue;
                }
                int newRoot = emailToPerson.get(email);
                newRoot = findRoot(newRoot, personToRoot);
                if (newRoot != curRoot) {
                    personToRoot.put(curRoot, newRoot);
                    curRoot = newRoot;
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> roots = new HashMap<>();
        for (int person : contacts.keySet()) {
            int root = findRoot(person, personToRoot);
            if (!roots.containsKey(root)) {
                roots.put(root, new ArrayList<>());
            }
            roots.get(root).add(person);
        }
        for(int root : roots.keySet()) {
            result.add(new ArrayList<>(roots.get(root)));
        }
        return result;
    }
    private int findRoot(int root, HashMap<Integer, Integer> personToRoot) {
        while (root != personToRoot.get(root)) {
            root = personToRoot.get(root);
        }
        return root;
    }
}

给一个数组， 给一个数字 k. 问能不能把数组里面的数字分到k个桶里面， 使得每个桶里面所有的数字和相同。 DFS暴力解。
// sort the array, always take the current number into the bucket whose sum is the smallest
class DivideKParts {
    public boolean divide(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return false;
        }
        PriorityQueue<Integer> buckets = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer bucket1, Integer bucket2) {
                return bucket1 - bucket2;
            }
        });
        for (int i = 0; i < k; i++) {
            buckets.add(0);
        }
        Arrays.sort(nums);
        int index = nums.length - 1;
        int sum = 0;
        while (index >= 0) {
            int bucket = buckets.poll();
            bucket += nums[index];
            sum += nums[index];
            buckets.add(bucket);
            index--;
        }
        if (sum % k != 0) {
            return false;
        }
        while (!buckets.isEmpty()) {
            if (buckets.poll() != sum / k) {
                return false;
            }
        }
        return true;
    }
}

