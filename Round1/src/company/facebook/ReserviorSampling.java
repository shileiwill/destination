package company.facebook;

import java.util.ArrayList;
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
//		double[] res = new ReserviorSampling().distribute(5, 100);
//		for (double val : res) {
//			System.out.println(val);
//		}
		ReserviorSampling reserviorSampling = new ReserviorSampling();
		int[][] grid = reserviorSampling.getGrid(5, 5, 5);
		System.out.println();
		reserviorSampling.countLandmine(grid);
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
    
    /**
     * 扫雷地图，给一个地图的长和宽，以及雷的数量，要求返回一个让雷随机分布的grid，雷用-1表示，随机函数直接用random函数就行
     * follow up: 如果一个位置没有雷，要求在没有雷的地方标出周围雷的数量，周围就是指上下左右对角线一共八个位置

		我就写了一个遍历整个地图的function，面试官又问：如果是sparse grid该怎么样？当时很紧张，时间还剩5分钟没有答出来，然后就是问还有没有问题，我说没有就挂了电话。
		
		挂了电话才想出来，如果是sparse grid可以把随机生成的雷的坐标保存在一个list里面，这样标雷数量的时候就只用遍历雷的坐标就可以了
     */
    int[][] getGrid(int rowNum, int colNum, int landmineNum) {
    	int[][] M = new int[rowNum][colNum];
    	int count = 0;
    	Random ran = new Random();
    	// If it is sparse
    	List<int[]> list = new ArrayList<int[]>();
    	
    	while (count < landmineNum) {
    		int X = ran.nextInt(rowNum);
    		int Y = ran.nextInt(colNum);
    		
    		if (M[X][Y] != -1) {
    			M[X][Y] = -1;
    			count++;
    			list.add(new int[]{X, Y});
    		}
    	}
    	
    	for (int i = 0; i < rowNum; i++) {
    		for (int j = 0; j < colNum; j++) {
    			System.out.print(M[i][j] + " ");
    		}
    		System.out.println();
    	}
    	
    	return M;
    }
    
    void countLandmine(int[][] M) {
    	int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    	
    	for (int i = 0; i < M.length; i++) {
    		for (int j = 0; j < M[i].length; j++) {
    			if (M[i][j] == -1) { // A landmine
    				continue;
    			}
    			
    			int count = 0;
    			
    			for (int[] dir : directions) {
    				int x = i + dir[0];
    				int y = j + dir[1];
    				
    				if (x >= 0 && x < M.length && y >= 0 && y < M[0].length && M[x][y] == -1) {
    					count++;
    				}
    			}
    			
    			M[i][j] = count;
    		}
    	}
    	
    	for (int i = 0; i < M.length; i++) {
    		for (int j = 0; j < M[i].length; j++) {
    			System.out.print(M[i][j] + " ");
    		}
    		System.out.println();
    	}
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