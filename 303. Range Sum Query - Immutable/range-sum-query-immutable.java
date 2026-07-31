class NumArray {
    static class SegmentTree {
        int leftmost, rightmost;
        int sum;
        SegmentTree lChild, rChild;
        public SegmentTree(int leftmost, int rightmost, int[] a) {
            this.leftmost = leftmost;
            this.rightmost = rightmost;
            if (leftmost == rightmost) {
                sum = a[leftmost];
            } else {
                int mid = (leftmost + rightmost) / 2;
                lChild = new SegmentTree(leftmost, mid, a);
                rChild = new SegmentTree(mid + 1, rightmost, a);
                recalc();
            }
        }
        public void recalc(){
            if (leftmost == rightmost) return;
            sum = lChild.sum + rChild.sum;
        }
        public void pointUpdate(int index, int newVal) {
            if (leftmost == rightmost) {
                sum = newVal;
                return;
            }
            if (index <= lChild.rightmost) lChild.pointUpdate(index, newVal);
            else rChild.pointUpdate(index, newVal);
            recalc();
        }
        public int rangeSum(int l, int r) {
            if (l > rightmost || r < leftmost) {
                return 0;
            } else if (l <= leftmost && r >= rightmost) {
                return sum;
            }
            return lChild.rangeSum(l, r) + rChild.rangeSum(l, r);
        }
    }

    private SegmentTree st;
    public NumArray(int[] nums) {
        st = new SegmentTree(0, nums.length - 1, nums);
    }
    
    public int sumRange(int left, int right) {
        return st.rangeSum(left, right);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
