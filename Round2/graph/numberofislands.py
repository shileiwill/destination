numberofislands.py

class Solution:


    def numIslands(self, grid: List[List[str]]) -> int:
        m = len(grid)
        n = len(grid[0])
        count = 0
        visited = set()
        
#         def dfs(grid, i, j, visited):
#             # i, j out of bound
#             if i < 0 or i >= m or j < 0 or j >= n:
#                 return
#             if (i, j) in visited:
#                 return
#             if grid[i][j] == "0":
#                 return
            
#             #  it is a valid 1
#             visited.add((i, j))
#             # search 4 directions
#             dfs(grid, i - 1, j, visited)
#             dfs(grid, i + 1, j, visited)
#             dfs(grid, i, j - 1, visited)
#             dfs(grid, i, j + 1, visited)
        
        directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        def bfs(grid, i, j, visited):
            queue = []
            queue.append((i, j))
            
            while len(queue) > 0:
                # // current point
                item = queue.pop(0)
                # go to 4 directions
                for dir in directions:
                    x = item[0] + dir[0]
                    y = item[1] + dir[1]
                    
                    if x < 0 or x >= m or y < 0 or y >=n:
                        continue
                    if (x, y) in visited:
                        continue
                    if grid[x][y] == "0":
                        continue
                        
                    # it is a valid 1    
                    queue.append((x, y))
                    visited.add((x, y))
        
        # find the starting point
        for i in range(m):
            for j in range(n):
                if grid[i][j] == "1" and (i, j) not in visited:
                    count += 1
                    bfs(grid, i, j, visited)
        
        return count


        