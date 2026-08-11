class Solution:
    def removeElement(self, nums: List[int], val: int) -> int:
        # slighlty more optimal in terms of writes because it exploits the statement that order may be changed. 
        # If there are lots of vals near the end, we can avoid copying elements unnecessarily.
        left, right = 0, len(nums) - 1
        while left <= right:
            if nums[left] == val:
                nums[left] = nums[right] # no need to swap, right is discarded
                right -= 1
            else:
                left += 1
        return left
