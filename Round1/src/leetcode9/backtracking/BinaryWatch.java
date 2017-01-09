package leetcode9.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 401. A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<String>();
        int[] hours = {1, 2, 4, 8};
        int[] mins = {1, 2, 4, 8, 16, 32};
        
        
        for (int i = 0; i <= num; i++) {
            List<Integer> list1 = generate(hours, i);
            List<Integer> list2 = generate(mins, num - i);
            
            for (int h : list1) {
                if (h >= 12) {
                    continue;
                }
                for (int m : list2) {
                    if (m >= 60) {
                        continue;
                    }
                    String str = h + ":" + (m < 10 ? "0" + m : m); 
                    res.add(str);
                }
            }
        }
        
        return res;
    }
    
    List<Integer> generate(int[] arr, int count) {
        List<Integer> list = new ArrayList<Integer>();
        helper(list, arr, count, 0, 0);
        return list;
    }
    
    void helper(List<Integer> list, int[] arr, int count, int pos, int num) {
        if (count == 0) {
            list.add(num);
            return;
        }
        
        for (int i = pos; i < arr.length; i++) {
            helper(list, arr, count - 1, i + 1, num + arr[i]);
        }
    }
}
