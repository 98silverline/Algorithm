class Solution {
    public int minRemoval(int[] nums, int k) {
        if (nums.length == 1) return 0;
        Arrays.sort(nums);

        int len = nums.length;
        int lt = 0;
        int maxKeep = 1;

        for (int rt = 0; rt < len; rt++) {
            while((long) nums[lt] * k < nums[rt]) lt++;
            int cur = rt - lt + 1;
            maxKeep = Math.max(maxKeep, cur);
        }

        return len - maxKeep;
    }
}