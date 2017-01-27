package leetcode19.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 190. Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
If this function is called many times, how would you optimize it?

Related problem: Reverse Integer
 */
public class ReverseBits {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += ((n >>> i) & 1); // Care only the last digit of n
            // n >>>= 1; // Logical right shift 1 digit
            if (i < 31) { // Dont shift the last digit
                res <<= 1;
            }
        }
        
        return res;
    }
    
    Map<Byte, Integer> map = new HashMap<Byte, Integer>();
    public int reverseBits2(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            bytes[i] = (byte)((n >>> 8 * i) & 0xFF); // 0000..0011111111
        }
        
        int res = 0;
        for (int i = 0; i < 4; i++) {
            res += reverseByte(bytes[i]);
            if (i < 3) {
                res <<= 8;
            }
        }
        
        return res;
    }
    
    int reverseByte(byte b) {
        Integer cache = map.get(b);
        if (cache != null) {
            return cache;
        }
        
        int res = 0;
        for (int i = 0; i < 8; i++) {
            res += ((b >>> i) & 1);
            if (i < 7) {
                res <<= 1;
            }
        }
        
        map.put(b, res); // Dont change b, keep it as it is
        return res;
    }
    
    // val is either 1 or 0
    int updateBit(int num, int i, int val) {
        int mask = ~(1 << i);
        int res = (num & mask) | (val << i);
        return res;
    }

}
