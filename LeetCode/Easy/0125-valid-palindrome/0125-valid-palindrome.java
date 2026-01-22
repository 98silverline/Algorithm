class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        s = s.toLowerCase();
        if(s.length() == 1) return true;

        int lt = 0;
        int rt = s.length() - 1;

        while(lt < rt) {
            if(s.charAt(lt) != s.charAt(rt)) return false;
            lt++;
            rt--;
        }

        return true;
    }
}