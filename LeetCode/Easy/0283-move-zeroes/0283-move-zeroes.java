class Solution {
    public void moveZeroes(int[] nums) {
        int lt = -1;
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                lt = i;
                break;
            }
        }

        if(lt == -1) return;
        int rt = lt + 1;
        while(rt < nums.length) {
            if(nums[lt] != 0) {
                lt++;
                rt = lt + 1;
                continue;
            }
            if(nums[rt] != 0) {
                nums[lt] = nums[rt];
                nums[rt] = 0;
                lt++;
                rt = lt + 1;
            } else rt++;
        }
    }
}