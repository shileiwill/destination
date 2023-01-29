wordsearch.py
class Solution(object):
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        directions = [[-1, 0], [1, 0], [0, -1], [0, 1]]
        m, n = len(board), len(board[0])
        visited = set()
        
        def dfs(i, j, pos):
            if pos == len(word):
                return True
        
            for dir in directions:
                x = i + dir[0]
                y = j + dir[1]
                
                if x >= 0 and x < m and y >= 0 and y < n and board[x][y] == word[pos] and (x, y) not in visited:
                    visited.add((x, y))
                    res = dfs(x, y, pos + 1)
                    if res:
                        return True
                    visited.remove((x, y))
            
            return False
            
        for i in range(m):
            for j in range(n):
                if board[i][j] == word[0]:
                    visited.add((i, j))
                    res = dfs(i, j, 1)
                    if res:
                        return True
                    visited.remove((i, j))
        return False