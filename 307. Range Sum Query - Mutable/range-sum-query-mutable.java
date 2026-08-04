class NumArray {
    // The number of nodes is 2n - 1
    // n + n/2 + n/4 + ... + 1 
    //   = \sum_{i=0}^{k} n(1/2)^i   -> last term: n (1/2)^k = 1
    //   = \sum_{i=0}^{\log{n}} n(1/2)^i 
    //   = n (1 - 0.5^{\log{n} + 1}) / (1 - 0.5) = 2n (1 - 1/2n)
    //   = 2n - 1
    class SegmentTree {
        SegmentTree lChild, rChild;
        int leftmost, rightmost;
        int sum;
        SegmentTree(int leftmost, int rightmost, int[] a) {
            this.leftmost = leftmost;
            this.rightmost = rightmost;
            if (leftmost == rightmost) {
                this.sum = a[leftmost];
            } else {
                int mid = leftmost + (rightmost - leftmost) / 2;
                lChild = new SegmentTree(leftmost, mid, a);
                rChild = new SegmentTree(mid + 1, rightmost, a);
                recalc();
            }
        }
        void recalc(){
            if (leftmost == rightmost) return;
            this.sum = lChild.sum + rChild.sum;
        }
        int rangeSum(int l, int r) {
            if (l <= leftmost && r >= rightmost) {
                return sum;
            } else if (r < leftmost || l > rightmost ) {
                return 0;
            }
            return lChild.rangeSum(l, r) + rChild.rangeSum(l, r);
        }
        void pointUpdate(int index, int val) {
            if (leftmost == rightmost) {
                sum = val;
                return;
            }
            if (index <= lChild.rightmost) {
                lChild.pointUpdate(index, val);
            } else rChild.pointUpdate(index, val);
            recalc();
        }
    }
    private SegmentTree st;
    public NumArray(int[] nums) {
        st = new SegmentTree(0, nums.length - 1, nums);
    }
    
    public void update(int index, int val) {
        st.pointUpdate(index, val);
    }
    
    public int sumRange(int left, int right) {
        return st.rangeSum(left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
