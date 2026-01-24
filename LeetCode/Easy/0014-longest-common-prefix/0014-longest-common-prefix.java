class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();

        int min = 200;
        int idx = 0;

        for(String str : strs) min = Math.min(min, str.length());

        while(idx < min) {
            char cur = strs[0].charAt(idx);
            boolean flag = false;
            for(int i = 1; i < strs.length; i++) {
                if(strs[i].charAt(idx) != cur) {
                    flag = true;
                    break;
                }
            }
            if(flag) return sb.toString();
            sb.append(cur);
            idx++;
        }
        return sb.toString();
    }
}