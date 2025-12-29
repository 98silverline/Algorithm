class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] answer = new int[2 * n];
        int m = 0;
        for(int i = 0; i < 2 * n; i += 2) {
            answer[i] = nums[m];
            answer[i + 1] = nums[m + n];
            m += 1;
        }
        return answer;
    }
}