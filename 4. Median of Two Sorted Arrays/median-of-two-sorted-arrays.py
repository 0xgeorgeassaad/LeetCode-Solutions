class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        # m is length of shorter list
        if len(nums1) > len(nums2):
            nums1, nums2 = nums2, nums1
        m, n = len(nums1), len(nums2)
        # left1 | right1
        # left2 | right2
        # all elements on the right are larger than all elements on the left
        # we are searching in nums1 for the smallest i that statisfies this
        # nums1[i - 1] <= nums2[j] and nums2[j - 1] <= nums1[i]

        # [1,3,4, | 6,8]
        # [2,5, | 7,9]
        l, r = 0, m
        while l <= r:
            i = l + (r - l) // 2
            # i + j = (m + n + 1) // 2 
            j = (m + n + 1) // 2 - i
            left1 = float("-inf") if i == 0 else nums1[i - 1]
            right1 = float("inf") if i == m else nums1[i]

            left2 = float("-inf") if j == 0 else nums2[j - 1]
            right2 = float("inf") if j == n else nums2[j]

            if left1 <= right2 and left2 <= right1:
                if (m + n) % 2 == 0: 
                    return (max(left1, left2) + min(right1, right2)) / 2
                return max(left1, left2) # deals with empty arrays
            elif left1 > right2:
                r = i - 1
            else:
                l = i + 1 
