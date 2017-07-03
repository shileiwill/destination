package company.facebook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 63. Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.
 * @author Lei
 *
 */
public class UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int[][] hash = new int[m][n];
        
        boolean leftBlock = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                leftBlock = true;
            }
            hash[i][0] = leftBlock ? 0 : 1;
        }
        
        boolean topBlock = false;
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                topBlock = true;
            }
            hash[0][i] = topBlock ? 0 : 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    hash[i][j] = 0;
                } else {
                    hash[i][j] = hash[i - 1][j] + hash[i][j - 1];
                }
            }
        }
        
        return hash[m - 1][n - 1];
    }
    
    /**
     *  Unique Path II，这题bug free, 很快就解出来了。
     *  第二题是第一题follow up. 问有2个robert, 一个在左上(A)，一个在右下(B)。2个机器人同时移动，A 只能移动Down, right. B 只能移动 Up,left. 
     *  写出一个方程，问A和B能不能同时到终点(ie. A到右下，B到左上）
     *  Note 1. 有障碍 2，A，B不能碰撞。 我给出了暴力的dfs解法，但是显然有更好的解法.
     *  
     *  如果a,b只能一个down right, 一个up left 如果都能到的话，肯定同时，因为都走 m + n - 2 步。 
     *  如果从a到b 只有一条道，那肯定相遇，没治。有两条或以上，如果相交，肯定只相交在一点，因为他们移动方向，所以不相交在长度的中点（m+n+2）/2就行
     *  
     *  1）如果相撞，必定中点相撞；（所以m+n % 2 == 0, return true）
		2）如果不相撞，（能不能同时到）==（a和b能不能都到）, 如果都可以走到：
		3）用a BFS走（m+n-2）/2步，把这些点标记成障碍，但不是1，而是标记成2.
		4）用b走，不经过2或者（经过2&&不只一个2），TRUE；

		"不相交在长度的中点" 厉害厉害~
		判断只需要满足：
		1. 存在可行路径
		2. 若有可能于某点相撞，则需要可行路径里有至少两种不同的中点位置
		
		My thoughts:
		1. A path for A is also a path for B, just reverse it.
		2. If there is only 1 path available, then definitely it will collide
		3. If there are more than 1 paths, need to check if there are 2 paths which don't meet in the middle step, (m + n - 2) / 2;
		4. If you want to remember the path, use a Map<Integer, Integer> for backtracking, but it is not necessary in our case. BFS走（m+n-2）/2步，把这些点标记成障碍，但不是1，而是标记成2
     */
    int[][] downRight = {{1, 0}, {0, 1}};
    int[][] upLeft = {{-1, 0}, {0, -1}};
	int m = 0;
	int n = 0;
    public int uniquePath2Robots(int[][] M) {
    	m = M.length;
    	n = M[0].length;
    	List<Integer> meetPoints = new ArrayList<Integer>();
    	// Move Robot A
    	bfs(M, 0, 0, downRight, meetPoints);
    }
    
    private void bfs(int[][] M, int i, int j, int[][] direction, List<Integer> meetPoints) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Set<Integer> visited = new HashSet<Integer>();
		
		int id = i * n + j;
		queue.offer(id);
		visited.add(id);
		int step = 1;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int a = 0; a < size; a++) {
				int now = queue.poll();
				int x1 = now / n;
				int y1 = now % n;
				int id1 = x1 * n + y1;
				
				if (step == (m + n - 2) / 2) { // Meet point
					// 不是所有的meet point都能到达终点啊
				}
			}
		}
	}

	/**
     *  题目是一个机器人在一个空间里面，不知道大小形状，也不知道初始位置。
     *  空间里有障碍物， 有一个move function, 如果前面是障碍物的话，会return false, 不是return true. 
     *  求所有可以到达位置的面积。Note: move 是真的move, 就算是障碍物也会move到那里，但是会return false。
     *  follow up 是怎么在dfs基础上再缩减call move的次数
     *  
     *  My thoughts:
     *  Need to have a visited[] to avoid revisit empty space
     *  Need to have a obstacle[] to remember known obstacles. Only if move() to that position, can we find out if it is obstacle. 
     *  So, important to remember
     *  We dont know the start point, but there is one. We will treat that as (0,0), move from there. ID is based on the start point and distance. 
     *  ID is a string now
     *  Use DFS is easier
     *  encode() and moveBack() are helpful sub-methods
     *  There is only 1 move(UP, DOWN, LEFT, RIGHT), no int[][] dashboard for us. We dont know the borders, but move() can take us to 4 directions
     */
    int getArea(int[] start) {
    	Set<String> visited = new HashSet<String>();
    	Set<String> obstacles = new HashSet<String>();
    	
    	visited.add(encode(start[0], start[1]));
    	dfs(visited, obstacles, start[0], start[1]);
    	return visited.size();
    }
    
    int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private void dfs(Set<String> visited, Set<String> obstacles, int i, int j) {
		for (int[] dir : directions) {
			int x = i + dir[0];
			int y = j + dir[1];
			String id = encode(x, y);
			
			if(visited.contains(id) || obstacles.contains(id)) {
				continue; //不要重蹈覆辙
			}
			
			if (move(x, y)) { // [x, y] is available
				visited.add(id);
				dfs(visited, obstacles, x, y); // Go deeper
			} else {
				obstacles.add(id);
			}
			moveBack(dir); // No matter success or failure, move back. 如果按照传统思维，一直走下去 不回退的话，走到死胡同，这个dfs就结束了， 不行， 这个题得回到起点， 继续搜寻
		}
	}

	String encode(int i, int j) {
    	return i + "," + j;
    }
    
    void moveBack(int[] dir) {
    	move(-dir[0], -dir[1]);
    }
}
