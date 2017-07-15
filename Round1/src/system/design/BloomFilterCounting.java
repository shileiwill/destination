package system.design;
import java.util.*;
/**
 * 除了存在false positive这个问题之外，传统的Bloom Filter还有一个不足：无法支持删除操作。而Counting Bloom Filter(CBF)就是用来解决这个问题的。
在CBF中，维护的不是单纯的标示0或者1的比特位，而是计数器counter。对于集合中的每个元素，利用k个哈希函数，对它哈希得到k个位置，将对应的k个位置上的k个counter都加1。
删除时，只需要把k个counter都减1即可
 */
public class BloomFilterCounting {

    public int[] bits; // 存count, 不能用BitSet了
    public int k;
    public List<HashFunction> hashFunc;

    public BloomFilterCounting(int k) {
        this.k = k;
        this.hashFunc = new ArrayList<HashFunction>();
        
        for (int i = 0; i < k; ++i) {
        	hashFunc.add(new HashFunction(100000 + i, 2 * i + 3));
        }
        
        this.bits = new int[100000 + k];
    }

    public void add(String word) {
        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            bits[position] += 1;
        }
    }

    public void remove(String word) {
        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            bits[position] -= 1;
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            if (bits[position] <= 0) {
            	return false;
            }
        }
        
        return true;
    }
}
