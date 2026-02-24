/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lt = 1;
        int rt = n;
        int answer = Integer.MAX_VALUE;
        while(lt <= rt) {
            int cur = lt + (rt - lt) / 2;
            System.out.println(cur);
            //불량인 경우
            if(isBadVersion(cur)) {
                answer = cur;
                rt = cur - 1;
            } 
            //불량 아닌 경우
            else {
                lt = cur + 1;
            }
        }
        return answer;
    }
}