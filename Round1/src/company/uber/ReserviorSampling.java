package company.uber;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * given a stream of integer, randomly choose k elements from n, ensure each value among k has equal probability.  
 */
public class ReserviorSampling {

	public static void main(String[] args) {
		double[] res = new ReserviorSampling().distribute(5, 100);
		for (double val : res) {
			System.out.println(val);
		}
		
		new ReserviorSampling().unitCircle();
	}

	int[] randomK(Iterator<Integer> it, int k) {
		int[] res = new int[k];
		
		int count = 0;
		while (count < k && it.hasNext()) {
			res[count++] = it.next();
		}
		
		if (!it.hasNext()) {
			return res;
		}
		
		Random ran = new Random();
		while (it.hasNext()) {
			int curVal = it.next();
			int ranIndex = ran.nextInt(++count);

			if (ranIndex <= k - 1) {
				res[ranIndex] = curVal;
			}
		}
		
		return res;
	}
	
	// 398 Random pick index
    public int pick(int target, int[] nums) {
        int res = -1;
        int count = 0; // 每一个target出现的概率都是一样的
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                if (ran.nextInt(++count) == 0) { // The probability of 0 is 1/N
                    res = i;
                }
            }
        }
        
        return res;
    }
    
    // 随机返回一个单位圆内的点，要求返回所有点的概率是一致的。 
    void unitCircle() {
    	//Random ran = new Random();
    	//ran.nextDouble() is same with Math.randome [0, 1)
    	
    	double x = 0.0d;
    	double y = 0.0d;
    	double squareSum = 0;
    	do {
    		x = Math.random() * 2 - 1; // [-1, 1)
    		y = Math.random() * 2 - 1;
    		squareSum = Math.pow(x, 2) + Math.pow(y, 2);
    	} while (squareSum > 1);
    	
    	System.out.println(x + "=but this doesnt include 1=" + y);
    }
    
    // What if each element is given a weight
    int pickByWeight(int[] weights) {
    	int sum = 0;
    	
    	for (int weight : weights) {
    		sum += weight;
    	}
    	
    	Random ran = new Random();
    	double ranWeight = ran.nextDouble() * sum;
    	
    	for (int i = 0; i < weights.length; i++) {
    		ranWeight -= weights[i];
    		
    		if (ranWeight <= 0) {
    			return i;
    		}
    	}
    	
    	return -1;// Should never come here
    	
    	/**
    	 * Another thought:
    	 * 给了一个array 的城市名，和对应的城市人口数量，写一个方法，要求能够以人口数量为权重返回城市名。
    	 * 要求O(1) time complexty。 
    	 * O(logn) Solution: 将人口数量的array 求和，随机产生一个在0-人口总数之间的数，然后在用二分搜索在array 里面找到相应的index。 
    	 * O(1) Solution: 按照人口数量，讲城市重复添加到一个list 里面，比如,, 则list 为; 直接随机返回list 的一个值。
    	 */
    	
    	// List<String> largeListWithDuplicatedCities = null; // 权重为5 就put 5 次
    }
    
    // Distribute 100 dollars to N people
    double[] distribute(int N, int amount) {
    	double[] res = new double[N];
    	Random ran = new Random();
    	double sum = 0;
    	
    	for (int i = 0; i < N; i++) {
    		int ranVal = ran.nextInt(10); // Any number
    		res[i] = ranVal;
    		sum += ranVal;
    	}
    	
    	for (int i = 0; i < N; i++) {
    		double thisPerson = (res[i] / sum) * amount;
    		// 取整， 但是如果是两个.5, 那就都进位了 ：（
//    		res[i] = Math.round(thisPerson);
    		// 保留两位小数
    		res[i] = Math.round(thisPerson * 100) / 100.0;
    	}
    	
    	return res;
    }
}

// Design, use TreeMap
class RandomByWeight<E> {
	TreeMap<Double, E> treeMap = new TreeMap<Double, E>();
	double sum = 0;
	Random ran = new Random();
	
	void add(double weight, E element) {
		sum += weight;
		treeMap.put(sum, element);
	}
	
	E pick() {
		double ranIndex = ran.nextDouble() * sum;
		Map.Entry<Double, E> entry = treeMap.ceilingEntry(ranIndex);
		return entry.getValue();
	}
}