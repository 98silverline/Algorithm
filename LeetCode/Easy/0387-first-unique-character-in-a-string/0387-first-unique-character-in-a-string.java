class Solution {
    static class Alpha {
        int idx;
        int cnt;

        public Alpha(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public int firstUniqChar(String s) {
        int answer = Integer.MAX_VALUE;
        Alpha[] ch = new Alpha[26];
        for(int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            if(ch[cur] == null) {
                ch[cur] = new Alpha(i, 1);
            } else {
                ch[cur].cnt++;
            }
        }
        for(Alpha al : ch) {
            if(al != null && al.cnt == 1) {
                answer = Math.min(answer, al.idx);
            }
        }
        if(answer == Integer.MAX_VALUE) return -1;
        return answer;
    }
}