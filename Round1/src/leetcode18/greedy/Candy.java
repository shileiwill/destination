package leetcode18.greedy;

import java.util.Arrays;

/**
 * 135. There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
 */
public class Candy {

    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        Arrays.fill(candy, 1); // Each child gets 1 candy
        
        // Scan from left to right, make sure the right one with higher rating gets 1 more candy than left
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        
        // Scan from right to left
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        
        int sum = 0;
        for (int v : candy) {
            sum += v;
        }
        
        return sum;
    }

    public int candy2(int[] ratings) {
        int[] arr1 = new int[ratings.length];
        int[] arr2 = new int[ratings.length];
        
        int res = 0;
        
        arr1[0] = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                arr1[i] = arr1[i - 1] + 1;
            } else {
                arr1[i] = 1;
            }
        }
        
        arr2[arr2.length - 1] = 1;
        for (int i = arr2.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                arr2[i] = arr2[i + 1] + 1;
            } else {
                arr2[i] = 1;
            }
        }
        
        for (int i = 0; i < ratings.length; i++) {
            res += Math.max(arr1[i], arr2[i]);
        }
        
        return res;
    }
}
