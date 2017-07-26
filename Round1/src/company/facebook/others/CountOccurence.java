package Facebook;

import java.util.*;

/**
 * Created by weixwu on 7/10/2017.
 * Do binary search using a running pointer
 */
public class CountOccurence {

    public List<Pair> getCount(int[] arr) {
        List<Pair> list = new ArrayList<>();
        if (arr == null || arr.length == 0) {
            return list;
        }
        int start = 0;
        while (start < arr.length) {
            int end = findLast(start, arr, arr[start]);
            list.add(new Pair(arr[start], end - start + 1));
            start = end + 1;
        }

        return list;
    }

    private int findLast(int s, int[] nums, int target) {
        int e = nums.length - 1;
        while(s < e){
            int mid = (s + e + 1) / 2 ;
            if (nums[mid] > target) e = mid - 1;
            else s = mid;
        }
        return s;
    }

    class Pair{
        int val;
        int count;
        public Pair(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,2,2,2,2,2,2,2,2,3};
        CountOccurence d = new CountOccurence();
        List<Pair> res = d.getCount(arr);
        for (Pair p : res) {
            System.out.println(p.val + ": " + p.count);
        }
    }
}
