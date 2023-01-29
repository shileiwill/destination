house_robber2.py

# https://leetcode.com/problems/house-robber-ii/
class Solution(object):
    def rob2(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 1:
            return nums[0]
        if len(nums) == 2:
            return max(nums[0], nums[1])
        
        take0 = []
        take0.append(nums[0])
        take0.append(max(nums[0], nums[1]))
        
        notake0 = []
        notake0.append(0)
        notake0.append(nums[1])
        
        for i in range(2, len(nums) - 1):
            take0.append(max(take0[i - 2] + nums[i], take0[i - 1]))
            notake0.append(max(notake0[i - 2] + nums[i], notake0[i - 1]))
        
        # cant pick the last item
        # take0.append(take0[len(nums) - 2])
        notake0.append(max(notake0[-1], notake0[-2] + nums[-1]))
 
        return max(take0[-1], notake0[-1])
        

    def rob(self, nums):
        if len(nums) == 1:
            return nums[0]
        if len(nums) == 2:
            return max(nums[0], nums[1])
        
        memo = {}
        
        # cut nums to smaller arrays using index as the starting point
        def helper(start, end):
            if start > end:
                return 0
            if (start, end) in memo:
                print("ever come, yes")
                return memo[(start, end)]
            cur_max = max(helper(start + 1, end), helper(start + 2, end) + nums[start])
            memo[(start, end)] = cur_max
            
            return cur_max
            
        res = max(helper(0, len(nums) - 2), helper(1, len(nums) - 1))
        return res