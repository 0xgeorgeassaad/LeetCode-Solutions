# Since the array is immutable
# A segment tree isn't optimal
# A simple prefix sum is enough 
class NumArray:
    def __init__(self, nums: List[int]):
        # O(n)
        self.pref = [0]
        for num in nums:
            self.pref.append(self.pref[-1] + num)

    def sumRange(self, left: int, right: int) -> int:
        # O(1)
        return self.pref[right + 1] - self.pref[left]


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# param_1 = obj.sumRange(left,right)
