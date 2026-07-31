class NumArray:
    class SegmentTree:
        def __init__(self, leftmost, rightmost, a):
            self.leftmost = leftmost
            self.rightmost = rightmost
            if leftmost == rightmost:
                self.sum = a[leftmost]
            else:
                mid = (leftmost + rightmost) // 2
                self.lChild = self.__class__(leftmost, mid, a)
                self.rChild = self.__class__(mid + 1, rightmost, a)
                self.recalc()
        def recalc(self):
            if self.leftmost == self.rightmost: return
            self.sum = self.lChild.sum + self.rChild.sum
        def pointUpdate(self, index, newVal):
            if self.leftmost == self.rightmost:
                self.sum = newVal
                return
            if index <= self.lChild.rightmost:
                self.lChild.pointUpdate(index, newVal)
            else:
                self.rChild.pointUpdate(index, newVal)
            self.recalc()
        def rangeSum(self, l, r):
            if l > self.rightmost or r < self.leftmost:
                return 0
            elif l <= self.leftmost and r >= self.rightmost:
                return self.sum
            return self.lChild.rangeSum(l, r) + self.rChild.rangeSum(l, r) 


    def __init__(self, nums: List[int]):
        # Build: O(n)
        self.st = self.SegmentTree(0, len(nums) - 1, nums)
        

    def update(self, index: int, val: int) -> None:
        # O(log(n))
        self.st.pointUpdate(index, val)

    def sumRange(self, left: int, right: int) -> int:
        # O(log(n))
        return self.st.rangeSum(left, right)
        


# Your NumArray object will be instantiated and called as such:
# obj = NumArray(nums)
# obj.update(index,val)
# param_2 = obj.sumRange(left,right)
