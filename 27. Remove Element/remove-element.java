class Solution {
    public int removeElement(int[] nums, int val) {
        // maintains order even though not required
        int k = 0; // writer pointer
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
