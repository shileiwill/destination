package system.design;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/*
Implement a standard bloom filter. Support the following method:
1. StandardBloomFilter(k),The constructor and you need to create k hash functions.
2. add(string). add a string into bloom filter.
3. contains(string). Check a string whether exists in bloom filter.

Example
StandardBloomFilter(3)
add("lint")
add("code")
contains("lint") // return true
contains("world") // return false

// http://willwhim.wpengine.com/2011/09/03/producing-n-hash-functions-by-hashing-only-once/
There’s a good paper that reminds us that you can easily simulate n hash functions by having just two hash functions around. 
This can be as simple as this function to create the ith hash of a key, given the results a and b of hashing a key with these two functions:

hash(i) = (a + b * i ) % m
where m is the maximum value of the hash (for example, the number of buckets in a bloom filter).

But here’s a good trick not really worth a paper–but it’s still a good trick. 
Typically, it’s totally reasonable to limit the size to under the maximum size of an unsigned 32-bit number. 
These days, at least, it’s probably cheaper to calculate a base hash function on unsigned 64-bit numbers. 
So, you can take the upper half and the lower half of the 64-bit hashed value and return them as two 32 bit numbers.
 * */
class HashFunction {
    public int capacity, seed;

    public HashFunction(int capacity, int seed) {
        this.capacity = capacity;
        this.seed = seed;
    }

    public int hash(String content) {
        int res = 0;
        int len = content.length();
        
        for (int i = 0; i < len; ++i) {
            res += seed * res + content.charAt(i);
            res %= capacity;
        }
        
        return res;
    }
}

public class BloomFilterStandard {

    public BitSet bits; // BitSet正好使
    public int numOfHash;
    public List<HashFunction> hashList;

    public BloomFilterStandard(int numOfHash) {
        this.numOfHash = numOfHash;
        hashList = new ArrayList<HashFunction>();
        
        for (int i = 0; i < numOfHash; ++i) {
        	hashList.add(new HashFunction(100000 + i, 2 * i + 3));
        }
        
        bits = new BitSet(100000 + numOfHash); // argument is the size of bitset
    }

    public void add(String word) {
        for (int i = 0; i < numOfHash; i++) {
            int position = hashList.get(i).hash(word);
            bits.set(position);
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < numOfHash; i++) {
            int position = hashList.get(i).hash(word);
            if (!bits.get(position)) {
            	return false; // 只要有一位为空，就绝对不存在
            }
        }
        
        return true;
    }
}
