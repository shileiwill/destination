setmatrixzeros.py

class Solution(object):
    def setZeroes(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: None Do not return anything, modify matrix in-place instead.
        """
        first_row_has_0 = False
        first_col_has_0 = False
        
        m, n = len(matrix), len(matrix[0])
        
        for val in matrix[0]:
            if val == 0:
                first_row_has_0 = True
                break
        for i in range(m):
            if matrix[i][0] == 0:
                first_col_has_0 = True
                break
                
        for i in range(1, m):
            for j in range(1, n):
                if matrix[i][j] == 0:
                    matrix[i][0] = 0
                    matrix[0][j] = 0
        
        for i in range(1, m):
            if matrix[i][0] == 0:
                # set the whole row to 0
                for j in range(1, n):
                    matrix[i][j] = 0
                    
        for j in range(1, n):
            if matrix[0][j] == 0:
                # set the whole col to 0
                for i in range(1, m):
                    matrix[i][j] = 0
        
        if first_row_has_0:
            for j in range(n):
                matrix[0][j] = 0
        if first_col_has_0:
            for i in range(m):
                matrix[i][0] = 0
                
                
        
        