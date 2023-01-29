search2dmatrix.py

class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        m, n = len(matrix), len(matrix[0])
        left, right = 0, m - 1

        while left + 1 < right:
            mid = left + (right - left) / 2
            if matrix[mid][0] == target:
                return True
            if matrix[mid][0] > target:
                right = mid
            if matrix[mid][0] < target:
                left = mid
        
        if matrix[left][0] == target or matrix[right][0] == target:
            return True
        
        # print(left)
        # print(right)
        row = left
        if matrix[right][0] < target:
            row = right
        
        left, right = 0, n - 1
        while left + 1 < right:
            mid = left + (right - left) / 2
            if matrix[row][mid] == target:
                return True
            if matrix[row][mid] < target:
                left = mid
            if matrix[row][mid] > target:
                right = mid
                
        if matrix[row][left] == target or matrix[row][right] == target:
            return True
    
        return False
        
            