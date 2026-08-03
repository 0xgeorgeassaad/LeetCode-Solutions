class Solution:
    def mySqrt(self, x: int) -> int:
        if x < 2:
            return x
        # for any x > 1
        # sqrt(x) < x/2 + 1
        # Proof: x < x^2/4 + x + 1 => 0 < x^2/4 + 1 (always true)
        l, r = 1, x // 2 + 1
        while l <= r:
            mid = l + (r - l) // 2
            if mid * mid == x:
                return mid
            elif mid * mid < x:
                l = mid + 1
            else:
                r = mid - 1
        # after the loop
        # r is the largest value whose square is <= x
        # l is the smallest value whose square is > x
        return r
