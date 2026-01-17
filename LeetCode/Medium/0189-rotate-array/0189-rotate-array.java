class Solution {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] tmp = new int[k];
        int cur = nums.length - k;
        for(int i = 0; i < k; i++) {
            tmp[i] = nums[cur];
            cur++;
        }
        int idx = nums.length - k - 1;
        for(int i = nums.length - 1; i >= k; i--) {
            nums[i] = nums[idx];
            idx--;
        }
        for(int i = 0; i < k; i++) {
            nums[i] = tmp[i];
        }
    }
}