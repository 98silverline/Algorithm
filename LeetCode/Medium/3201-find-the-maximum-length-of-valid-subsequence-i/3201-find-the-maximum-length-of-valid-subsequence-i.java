class Solution {
    public int maximumLength(int[] nums) {
        int answer = 0;
        int odd = 0;
        int even = 0;
        int curNum = nums[0] % 2;
        int len = 1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] % 2 == 0) even++;
            else odd ++;
            if(curNum != nums[i] % 2) {
                len++;
                curNum = nums[i] % 2;
            }
        }
        
        if(odd > even) answer = odd;
        else answer = even;
        answer = Math.max(answer, len);
        return answer;
    }
}