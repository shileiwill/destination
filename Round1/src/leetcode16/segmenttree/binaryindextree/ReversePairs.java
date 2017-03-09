package leetcode16.segmenttree.binaryindextree;
/**
 * 493. Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.
 */
public class ReversePairs {
    class Node {
        int val, count; // Elements <= current element
        Node left, right;
        Node(int val) {
            this.val = val;
            this.count = 1; // Itself
        }
    }
    public int reversePairs(int[] N) {
        int res = 0;
        
        if (N == null || N.length <= 1) {
            return res;
        }
        // Just make the last element the ROOT, it will be extremely bad if the initial array is in order
        Node root = new Node(N[N.length - 1]); 
        for (int i = N.length - 2; i >= 0; i--) { // From the end
            res += query(root, N[i] / 2.0); // query first, then insert
            insert(root, N[i]);
        }
        
        return res;
    }
    
    int query(Node root, double val) { // val is already half
        if (root == null) {
            return 0;
        }
        
        if (val <= root.val) {
            return query(root.left, val);            
        } else { // val > root.val
            return root.count + query(root.right, val);
        }
    }
    
    Node insert(Node root, int val) {
        if (root == null) {
            return new Node(val);    
        }
        
        if (val == root.val) {
            root.count++;
        } else if (val < root.val) {
            root.count++;
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        
        return root;
    }
    
    public int reversePairsMergeSort(int[] N) {
        return mergeSort(N, 0, N.length - 1);
    }
    
    int mergeSort(int[] N, int left, int right) {
        if (left >= right) {
            return 0;
        }
        
        int mid = (left + right) / 2;
        
        int count = mergeSort(N, left, mid) + mergeSort(N, mid + 1, right);
        
        // Left and right array are sorted in increasing order independently here. i and j are the indexes.
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && N[i] / 2.0 > N[j]) {
                j++;
            }
            count += j - (mid + 1);
        }
        
        // Arrays.sort(N, left, right + 1);
        merge(N, left, mid, right);
        return count;
    }
    
    void merge(int[] N, int left, int mid, int right) {
        int[] helper = new int[N.length];
        
        for (int i = left; i <= right; i++) {
            helper[i] = N[i];
        }
        
        int i = left, j = mid + 1;
        int index = left;
        while (i <= mid && j <= right) {
            if (helper[i] <= helper[j]) {
                N[index++] = helper[i++];
            } else {
                N[index++] = helper[j++];
            }
        }
        
        while (i <= mid) {
            N[index++] = helper[i++];
        }
        
        while (j <= right) {
            N[index++] = helper[j++];
        }
    }
}