package chapter7.dataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KthSmallestElementInMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        Comparator<Node> comp = new Comparator<Node>() {
          public int compare(Node n1, Node n2) {
              return n1.val - n2.val;
          }  
        };
        PriorityQueue<Node> heap = new PriorityQueue<Node>(k, comp);
        
        // Point by point will not work. should be line by line
        for (int i = 0; i < Math.min(matrix.length, k); i++) {
            heap.offer(new Node(matrix[i][0], i, 0));
        }
        
        for (int i = 0; i < k - 1; i++) {
            Node cur = heap.poll();
            
            if (cur.y + 1 < matrix[0].length) {
                heap.offer(new Node(matrix[cur.x][cur.y + 1], cur.x, cur.y + 1));
            }
        }
        
        return heap.poll().val;
    }
    
    public int kthSmallestFailed(int[][] matrix, int k) {
        Comparator<Node> comp = new Comparator<Node>() {
          public int compare(Node n1, Node n2) {
              return n1.val - n2.val;
          }  
        };
        PriorityQueue<Node> heap = new PriorityQueue<Node>(k, comp);
        
        // Point by point will not work. should be line by line
        heap.offer(new Node(matrix[0][0], 0, 0));
        int count = 1;
        while (!heap.isEmpty()) {
            Node n = heap.poll();
            if (count == k) {
                return n.val;
            }
            
            if (matrix[n.x + 1][n.y] <= matrix[n.x][n.y + 1]) {
                heap.offer(new Node(matrix[n.x + 1][n.y], n.x + 1, n.y));
            }
        }
        
        return -1;
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

