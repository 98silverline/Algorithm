class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Queue<Integer> queue = new LinkedList<>();
        int[] cnt = new int[1001];
        for(int i = 0; i < nums1.length; i++) {
            cnt[nums1[i]] += 1;
        }
        for(int i = 0; i < nums2.length; i++) {
            if(cnt[nums2[i]] > 0) {
                cnt[nums2[i]]--;
                queue.offer(nums2[i]);
            }
        }
        int[] answer = new int[queue.size()];
        int idx = 0;
        while(!queue.isEmpty()) {
            answer[idx] = queue.poll();
            idx++;
        }
        return answer;
    }
}