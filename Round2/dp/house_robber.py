house_robber.py
# https://leetcode.com/problems/house-robber/

class Solution(object):
    def rob2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        hash = []
        hash.append(nums[0])
        hash.append(max(nums[0], nums[1]))
        
        for i in range(2, len(nums)):
            hash.append(max(hash[i - 2] + nums[i], hash[i - 1]))
        
        return hash[len(nums) - 1]


    def rob(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        N = len(nums)
        
        # cut nums to smaller arrays using index as the starting point
        def helper(index):
            if index >= N:
                return 0
            return max(helper(index + 1), helper(index + 2) + nums[index])
            
        return helper(0)

    def rob(self, nums):
        memo = {}
        
        # cut nums to smaller arrays using index as the starting point
        def helper(index):
            if index >= len(nums):
                return 0
            if index in memo:
                return memo[index]
            cur_max = max(helper(index + 1), helper(index + 2) + nums[index])
            memo[index] = cur_max
            
            return cur_max
            
        helper(0)
        return memo[0]

    def rob3(self, nums):
        """
                recurisve, no memo
        :type nums: List[int]
        :rtype: int
        """
        N = len(nums)
        
        # cut nums to smaller arrays using index as the starting point
        def helper(index):
            if index >= N:
                return 0
            return max(helper(index + 1), helper(index + 2) + nums[index])
            
        return helper(0)

    def rob2(self, nums):
        """
        with memo
        :type nums: List[int]
        :rtype: int
        """
        memo = {}
        N = len(nums)
        
        # cut nums to smaller arrays using index as the starting point
        def helper(index):
            if index >= N:
                return 0
            if index in memo:
                return memo[index]
            cur_max = max(helper(index + 1), helper(index + 2) + nums[index])
            memo[index] = cur_max
            
            return cur_max
            
        return helper(0)

    def rob2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        hash = []
        hash.append(nums[0])
        hash.append(max(nums[0], nums[1]))
        
        for i in range(2, len(nums)):
            hash.append(max(hash[i - 2] + nums[i], hash[i - 1]))
        
        return hash[len(nums) - 1]
