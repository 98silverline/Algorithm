class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int cur = m + n - 1;
        int pt1 = m - 1;
        int pt2 = n - 1;

        // if(pt2 < 0) return;
        // else if (pt1 < 0) {
        //     int cnt = 0;
        //     for(int k : nums2) {
        //         nums1[cnt] = n;
        //         cnt++;
        //     }
        // }
        while(pt1 >= 0 || pt2 >= 0) {
            if(pt1 < 0 && pt2 >= 0) {
                nums1[cur] = nums2[pt2];
                cur--;
                pt2--;
                continue;
            } else if (pt2 < 0 && pt1 >= 0) {
                nums1[cur] = nums1[pt1];
                cur--;
                pt1--;
                continue;
            }
            //현재 최댓값 찾기
            if(nums1[pt1] <= nums2[pt2]) {
                nums1[cur] = nums2[pt2];
                cur--;
                pt2--;
            } else {
                nums1[cur] = nums1[pt1];
                cur--;
                pt1--;
            }
        }
    }
}