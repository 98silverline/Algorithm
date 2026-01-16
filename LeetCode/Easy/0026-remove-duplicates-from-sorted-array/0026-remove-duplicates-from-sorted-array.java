class Solution {
    public int removeDuplicates(int[] nums) {
        Arrays.sort(nums);
        int answer = 1;
        int cur = nums[0];
        int lt = 1;
        int rt = 1;
        while(rt < nums.length) {
            if(nums[rt] != cur) {
                nums[lt] = nums[rt];
                cur = nums[rt];
                lt++;
                answer++;
            }
            rt++;
        }
        return answer;
    }
}