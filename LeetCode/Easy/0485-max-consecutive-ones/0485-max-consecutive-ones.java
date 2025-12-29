class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int answer = Integer.MIN_VALUE;
        int cur = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) cur++;
            else {
                answer = Math.max(answer, cur);
                cur = 0;
            }
        }
        answer = Math.max(answer, cur);
        return answer;
    }
}