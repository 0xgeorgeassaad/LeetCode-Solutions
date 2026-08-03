class Solution:
    def mySqrt(self, x: int) -> int:
        # O(1)
        if x < 2: return x
        r = x
        while r * r > x:
            r = (r + x // r) // 2
        return r
