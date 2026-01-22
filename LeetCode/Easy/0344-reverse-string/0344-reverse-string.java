class Solution {
    public void reverseString(char[] s) {
        int lt = 0;
        int rt = s.length - 1;
        while(lt < rt) {
            char tmp = s[lt];
            s[lt] = s[rt];
            s[rt] = tmp;
            lt++;
            rt--;
        }
    }
}