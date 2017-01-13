package leetcode10.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 296. A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three people living at (0,0), (0,4), and (2,2):

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.

Hint:

Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 */
public class BestMeetingPoint {

	public static void main(String[] args) {
		// 1 dimention
		int[] arr = {1, 0, 0, 0, 0, 1, 0, 1, 0};
		List<Integer> guys = new ArrayList<Integer>();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 1) {
				guys.add(i);
			}
		}
		
		int minDistance = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			int distance = 0;
			for (int guy : guys) {
				distance += Math.abs(guy - i);
			}
			
			minDistance = Math.min(minDistance, distance);
		}
		
		System.out.println(minDistance);
	}

    public int minTotalDistance2(int[][] grid) {
        List<Integer> row = new ArrayList<Integer>();
        List<Integer> col = new ArrayList<Integer>();
		
		for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 1) {
    				row.add(i); // row is already sorted
    				col.add(j); // But col is not guaranteed to be sorted
    			}
		    }
		}
		
		return getMinDistance2(row) + getMinDistance2(col); 
    }
    
    /*
    Why median is always the good place?
    As long as you have different numbers of people on your left and on your right, moving a little to the side with more people decreases the sum of distances. So to minimize it, you must have equally many people on your left and on your right.
    */
    int getMinDistance2(List<Integer> list) {
        Collections.sort(list);
        
        int left = 0, right = list.size() - 1, distance = 0;
        
        while (left < right) {
            distance += list.get(right--) - list.get(left++);    
        }
        
        return distance;
    }
    
    // To avoid sorting
    public int minTotalDistance(int[][] grid) {
        List<Integer> row = new ArrayList<Integer>();
        List<Integer> col = new ArrayList<Integer>();
		
		for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid[i].length; j++) {
    			if (grid[i][j] == 1) {
    				row.add(i);
    			}
		    }
		}
		
		for (int j = 0; j < grid[0].length; j++) {
		    for (int i = 0; i < grid.length; i++) {
    			if (grid[i][j] == 1) {
    				col.add(j);
    			}
		    }
		}
		
		return getMinDistance(row) + getMinDistance(col); 
    }
    
    int getMinDistance(List<Integer> list) {
        int left = 0, right = list.size() - 1, distance = 0;
        
        while (left < right) {
            distance += list.get(right--) - list.get(left++);    
        }
        
        return distance;
    }
}
