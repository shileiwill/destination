package chapter7.dataStructure;
/**
373.
You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3 

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]
 */
import java.util.Comparator;
import java.util.PriorityQueue;
// 求集合的最大值 最小值 用Heap
// 此题是Linear求k小的follow up. 对于原题，用quick sort 的partition. Find kth element in an array
public class KthSmallestSumIn2SortedArrays {
    public int kthSmallest(int[] arr1, int[] arr2, int k) {
    	// This way needs to build this 2 dimention matrix
    	int[][] arr = new int[arr1.length][arr2.length];
    	for (int i = 0; i < arr1.length; i++) {
    		for (int j = 0; j < arr2.length; j++) {
    			arr[i][j] = arr1[i] + arr2[j];
    		}
    	}
    	
        Comparator<Node> comp = new Comparator<Node>() {
          public int compare(Node n1, Node n2) {
              return n1.val - n2.val;
          }  
        };
        PriorityQueue<Node> heap = new PriorityQueue<Node>(k, comp);
        
        for (int i = 0; i < arr1.length; i++) {
        	heap.offer(new Node(arr[i][0], i, 0));
        }
        
        for (int i = 0; i < k - 1; i++) {
            Node cur = heap.poll();
            
            if (cur.y + 1 < arr2.length) {
            	int nextRow = cur.x;
            	int nextCol = cur.y + 1;
            	heap.offer(new Node(arr[nextRow][nextCol], nextRow, nextCol));
            }
        }
        
        return heap.poll().val;
    }
    
    class Node {
        int val, x, y;
        Node(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }
}

class KthSmallestSumIn2SortedArrays2 {
	class Pair implements Comparable<Pair>{
		int x, y, sum; // X array, Y array
		
		Pair(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
		
		public int compareTo(Pair another) {
			return this.sum - another.sum;
		}
		
		// If use visited hashset, need to implement equals() and hashcode()
	}
	
	public int kthSmallest(int[] arr1, int[] arr2, int k) {
		if ((arr1 == null || arr1.length == 0) && (arr2 == null || arr2.length == 0)) {
			return -1;
		} else if (arr1 == null || arr1.length == 0) {
			return arr2[k];
		} else if (arr2 == null || arr2.length == 0) {
			return arr1[k];
		}
		// If there are a lot duplicates, we can use a Set<Pair> visited
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>(k);
		Pair p = new Pair(0, 0, arr1[0] + arr2[0]);
		
		int[] dx = {0, 1};
		int[] dy = {1, 0};
		
		for (int count = 0; count < k - 1; count++) {
			Pair now = heap.poll();
			for (int i = 0; i < 2; i++) {
				int nextX = now.x + dx[i];
				int nextY = now.y + dy[i];
				
				if (nextX < arr1.length && nextY < arr2.length) {
					int sum = arr1[nextX] + arr2[nextY];
					Pair next = new Pair(nextX, nextY, sum);
					heap.offer(next);
				}
			}
		}
		
		return heap.peek().sum;
	}
}
