package company.snapchat;

import java.util.Stack;

/**
 * 42. Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

For example, 
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * @author Lei
 *
 */
public class TrappingRainWater {
    // Stack, 利用Largest Rectangle的思想
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        //Stack中存的是递减的，碰到大数，就开始计算
        Stack<Integer> stack = new Stack<Integer>();
        int vol = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int mid = stack.pop();
                // current is i
                if (!stack.isEmpty()) { // 保证左边有挡着的. 宽度直接用i-mid-1,不用考虑最左边，因为水都漏了
                    int pre = stack.peek(); 
                    vol += ((i - pre - 1) * (Math.min(height[i], height[pre]) - height[mid]));
                }
                
            }
            stack.push(i);
        }
        
        return vol;
    }
    // Two pointers
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int left = 0, right = height.length - 1;
        int vol = 0;
        int smaller;
        
        while (left < right) {
            if (height[left] < height[right]) { // 只要两边有撑腰的
                smaller = height[left];
                while (left < right && height[left] <= smaller) { // Move one by one
                    vol += smaller - height[left];
                    left++;
                }
            } else {
                smaller = height[right];
                while (left < right && height[right] <= smaller) { // Move one by one
                    vol += smaller - height[right];
                    right--;
                }
            }
        }
        
        return vol;
    }
}
