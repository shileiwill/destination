spiral_matrix.py


class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if not matrix:
            return matrix

        m, n = len(matrix), len(matrix[0])
        start_row = 0
        end_row = m - 1
        start_col = 0
        end_col = n - 1

        res = []
        while start_row <= end_row and start_col <= end_col:
            # go left to right on start row 
            for i in range(start_col, end_col + 1):
                res.append(matrix[start_row][i])
            start_row += 1

            # go top to bottom on end_col 
            for i in range(start_row, end_row + 1):
                res.append(matrix[i][end_col])
            end_col -= 1

            # go right to left on end_row 
            if end_row >= start_row:
                for i in range(end_col, start_col - 1, -1):
                    res.append(matrix[end_row][i])
                end_row -= 1

            # go bottom to top on start_col 
            if end_col >= start_col:
                for i in range(end_row, start_row - 1, -1):
                    print(i, start_col)
                    res.append(matrix[i][start_col])
                start_col += 1

        return res