class Solution {
    public int strStr(String haystack, String needle) {
        int idx = 0;
        while(idx < haystack.length()) {
            if(haystack.charAt(idx) == needle.charAt(0)) {
                int cur = 1;
                boolean flag = true;
                while(cur < needle.length()) {
                    if(haystack.charAt(idx + cur) != needle.charAt(cur)) {
                        flag = false;
                        break;
                    }
                    cur++;
                }
                if(flag) return idx;
            }
            idx++;
        }
        return -1;
    }
}