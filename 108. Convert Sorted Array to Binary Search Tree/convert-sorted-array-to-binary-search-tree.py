# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:
        def build(l, r):
            if r < l:
                return None
            mid = (l + r + 1) // 2
            return TreeNode(
                nums[mid], 
                build(l, mid - 1),
                build(mid + 1, r)
            )
        return build(0, len(nums) - 1)
