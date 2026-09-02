# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def nodesBetweenCriticalPoints(self, head: Optional[ListNode]) -> List[int]:
        min_dist = float('inf')
        prev, curr = head, head.next
        first = last = -1
        i = 1 # no of seen nodes 
        while curr.next:
            if (prev.val < curr.val > curr.next.val or
                prev.val > curr.val < curr.next.val):
                if first == -1:
                    first = i
                else:
                    min_dist = min(min_dist, i - last)
                last = i
            prev, curr = curr, curr.next
            i += 1
        return [-1,-1] if min_dist == float('inf') else [min_dist, last - first]
        
