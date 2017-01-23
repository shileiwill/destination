package leetcode15.bfs.dfs.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 130. Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
 */
public class SurroundedRegions {
    /*
    The idea is to first find all 'O's on the edge, and do BFS from these 'O's. Keep adding 'O's into the queue in the BFS, and mark it as '+'. Since these 'O's are found by doing BFS from the 'O's on the edge, it means they are connected to the edge 'O's. so they are the 'O's that will remain as 'O' in the result.

After BFS, there are some 'O's can not be reached, they are the 'O's that need to be turned as 'X'.
    */
    public void solveBFS(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        
        Queue<Point> queue = new LinkedList<Point>();
        // First column and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '+';
                queue.offer(new Point(i, 0));
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = '+';
                queue.offer(new Point(i, n - 1));
            }
        }
        // First Row and last row
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = '+';
                queue.offer(new Point(0, i));
            }
            if (board[m - 1][i] == 'O') {
                board[m - 1][i] = '+';
                queue.offer(new Point(m - 1, i));
            }
        }
        
        
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            
            for (int[] dir : direction) {
                int x = p.x + dir[0];
                int y = p.y + dir[1];
                
                if (x >= 0 && x < m && y >= 0 && y < n && board[x][y] == 'O') {
                    board[x][y] = '+';
                    queue.offer(new Point(x, y));
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '+') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    public void solveDFS(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        
        // First and last column
        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '+'; // Can also move this to dfs()
                dfs(board, i, 0);
            }
            if (board[i][n - 1] == 'O') {
                board[i][n - 1] = '+';
                dfs(board, i, n - 1);
            }
        }
        
        // First and last row
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = '+';
                dfs(board, 0, i);
            }
            if (board[m - 1][i] == 'O') {
                board[m - 1][i] = '+';
                dfs(board, m - 1, i);
            }
        }
        
        // Post processing
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '+') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }        
    }
    
    int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    void dfs(char[][] board, int x, int y) {
        for (int[] dir : direction) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            
            if (newX > 0 && newX < board.length - 1 && newY > 0 && newY < board[0].length - 1 && board[newX][newY] == 'O') {
                board[newX][newY] = '+';
                dfs(board, newX, newY);
            }
        }
    }
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        int m = board.length;
        int n = board[0].length;
        
        // The last one is dummyNode, which connects to all boundary nodes
        UnionFind uf = new UnionFind(m * n + 1);
        int dummyId = m * n;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    int curId = i * n + j;
                    
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) { // boundaries
                        uf.union(dummyId, curId);
                    } else {
                        for (int[] dir : direction) {
                            int newX = i + dir[0];
                            int newY = j + dir[1];
                            int newId = newX * n + newY;
                            
                            if (newX >= 0 && newX < m && newY >= 0 && newY < n && board[newX][newY] == 'O') {
                                uf.union(newId, curId);
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int curId = i * n + j;
                if (uf.isConnected(curId, dummyId)) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    class UnionFind {
        int[] parent = null;
        
        UnionFind(int totalNodes) {
            parent = new int[totalNodes];
            for (int i = 0; i < totalNodes; i++) {
                parent[i] = i;
            }
        }
        
        void union(int id1, int id2) {
            int parent1 = find(id1);
            int parent2 = find(id2);
            
            if (parent1 != parent2) {
                parent[parent1] = parent2; // Share the same ancestor now
            }
        }
        
        int find(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id]; // id goes up 1 level
            }
            
            return parent[id]; // return the ancestor
        }
        
        int findRecursion(int id) {
            if (parent[id] == id) {
                return id;
            }
            
            parent[id] = findRecursion(parent[id]); // Recursion to find the ancestor
            return parent[id]; // return the ancestor
        }
        
        boolean isConnected(int id1, int id2) {
            return find(id1) == find(id2);
        }
    }
    
    class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}