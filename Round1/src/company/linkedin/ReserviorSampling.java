package company.linkedin;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * given a stream of integer, randomly choose k elements from n, ensure each value among k has equal probability.  
 * 
 * Given a function Rand01Uniform() which generates a random integer number from [0,1] with a uniform distribution, 
 * write a function Rand06Uniform() which generates a random integer number from [0,6] with a uniform distribution.
 * 
 * Flip Rand01Uniform 3 times, and associate each result as follows:
000: 0
001: 1
010: 2
011: 3
100: 4
101: 5
110: 6
111: reflip

# R = Rand01Uniform
def Rand06Uniform():
  while True:
    roll = 4 * R() + 2 * R() + R()
    if roll < 7: return roll
 */
public class ReserviorSampling {

	public static void main(String[] args) {
		double[] res = new ReserviorSampling().distribute(5, 100);
		for (double val : res) {
			System.out.println(val);
		}
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