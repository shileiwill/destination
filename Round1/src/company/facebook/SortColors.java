package company.facebook;

import java.util.Arrays;
import java.util.List;

//重点
/**
 * 75. Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, 
 * with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

给三个funtions: is_low(), is_mid(), is_high(). 让给一个数组排序, low的放在最前面, mid的放在中间, high的放在最后面.
Color sort: think about when there are K colors
 */
public class SortColors {

	public static void main(String[] args) {
		int[] arr = {0, 1, 2, 1, 2, 1, 0, 1, 1, 0, 0, 3, 0, 2, 3};
		SortColors sc = new SortColors();
		sc.sortColors2Best(arr, 4);
		
		for (int val : arr) {
			System.out.print(val + " ");
		}
	}
	
	// Need 1 pass solution, 3 pointers, similar to 3 sliding window with size K, maximum sum
	void sortColors1Pass(int[] arr) {
		int pos0 = 0; // Red 0
		int pos1 = 0; // White 1
		int pos2 = arr.length - 1; // Blue 2
		
		while (pos1 < pos2 + 1) { // It is possible there is no Blue at all.
			if (arr[pos1] == 0) {
				swap(arr, pos0, pos1);
				pos0++;
				pos1++;
			} else if (arr[pos1] == 2) {
				swap(arr, pos1, pos2);
				pos2--;
			} else {
				pos1++;
			}
		}
	}

	// This is 2 pass, you can also use counting sort to solve this, using 2 pass
	// But this solution is stable. It can keep original order of 0s, 1s, 2s
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int pos = helper(nums, 0, nums.length - 1, 0);
        helper(nums, pos, nums.length - 1, 1);
    }
    
    int helper(int[] nums, int start, int end, int val) {
        int left = start, right = start;
        
        while (right <= end) {
            while (left <= end && nums[left] == val) {
                left++;
            } // Left will be the first non-val
            
            right = Math.max(right, left);
            while (right <= end && nums[right] != val) {
                right++;
            } // Right will be the first val
            
            if (left < right && right <= end) {
                swap(nums, left, right);
            }
        }
        
        return left;
    }
    
    void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /*
     * Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, 
     * with the colors in the order 1, 2, ... k.
You are not suppose to use the library's sort function for this problem.

k <= n

Example
Given colors=[3, 2, 2, 1, 4], k=4, your code should sort colors in-place to [1, 2, 2, 3, 4].

Challenge 
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?
     */
    // Like Quick Sort, O(N*K)
    public void sortColors2(int[] colors, int k) {
        int left = 0;
        for (int i = 1; i <= k; i++) {
            left = partition(colors, left, colors.length - 1, i);
        }
    }
    
    int partition(int[] nums, int left, int right, int val) {
        while (left < right) {
            while (left < right && nums[left] == val) {
                left++;
            }
            while (left < right && nums[right] != val) {
                right--;
            }
            swap(nums, left, right);
        }
        if (nums[left] == val) {
            return left + 1;
        } else {
            return left;
        }
    }
    
	public void sortKColorsLearntFromOthers(int[] nums, int k) {
		int left = 0;
		int right = nums.length - 1;
		int minColor = 1;
		int maxColor = k;
		
		while (minColor < maxColor) {
			for (int i = left; i <= right; i++) {
				while (nums[i] == maxColor && i < right) {
					swap(nums, i, right--);
				} // 直到i不是maxColor
				
				if (nums[i] == minColor) {
					swap(nums, i, left++);
				}
			}
			
			minColor++;
			maxColor--; // 每一回合都把minColor和maxColor安顿好
		}
	}
    
    /**
     * 大家很多人提到了一道sort color的变种，我今天就遇到了，趁现在还记得详细跟大家说一下。
给定一个API getCategory(int n)， return {L| M| H} 三种category
第一问 --- 给定一个Array， [4,2,5,7,8,9], 对于每一个int，有一个category，sort them by category
很简单，用sortcolor 就可以解决，两个ptr，一个前一个后。LZ不小心有一个bug （for loop 不太对），但是测试的时候找到了
第二问 ---- 如果这个时候有K个category， 应该怎么办
顺着上一题的思路，我的想法是将（0,1，。。。，k-1） category 分成（0）--> L, (1, k-2) -->M, (k-1) --> H， 
然后相同的思想继续call之前的function，然后reduce为 （1，k-2）的range，重复之前的事情
大家可以再仔细想想这里面有什么问题。
第一要注意的是，之前的function应该变成：
sortCategory（nums, low, high） (low and high is the corresponding int of the "L" and "H" category)
要记得把front和back一个指针变一下，如下：
while（getCategory(nums[front]) < low） front++;
while（getCategory(nums[back]) > high） back--;
     */
    //  O(nlogk), the best algorithm based on comparing 好题
    public void sortColors2Best(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }
        rainbowSort(colors, 0, colors.length - 1, 0, k - 1);
    }
    
    public void rainbowSort(int[] colors, int left, int right, int colorFrom, int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }
        
        if (left >= right) {
            return;
        }
        
        int colorMid = (colorFrom + colorTo) / 2;
        int l = left, r = right;
        while (l <= r) {
            while (l <= r && colors[l] <= colorMid) {
                l++;
            }
            while (l <= r && colors[r] > colorMid) {
                r--;
            }
            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
                
                l++;
                r--;
            }
        }
        
        rainbowSort(colors, left, r, colorFrom, colorMid);
        rainbowSort(colors, l, right, colorMid + 1, colorTo);
    }

}
