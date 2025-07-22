class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int lt = 0;
        int rt = 1;
        int answer = nums[0];
        int[] ch = new int[10001];
        ch[nums[0]] = 1;
        int cur = nums[0];
        while(rt < nums.length) {
            cur += nums[rt];
            //nums 배열에서 rt 위치에 있는 값이 중복인지 체크함
            if(ch[nums[rt]] == 1) {
                //중복인 경우 중복 빠질 때까지 lt 옮기면서 값 뺌
                while(ch[nums[rt]] == 1 && lt != rt) {
                    ch[nums[lt]] = 0;
                    cur -= nums[lt];
                    lt++;
                }
            }
            ch[nums[rt]] = 1;
            rt++;
            answer = Math.max(answer, cur);
        }
        return answer;
    }
}