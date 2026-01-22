class Solution {
    
    public boolean isAnagram(String s, String t) {

        if(s.length() != t.length()) return false;
        
        int[] ch = new int[26];
        Arrays.fill(ch, -1);
        for(int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            if(ch[cur] == -1) ch[cur] = 1;
            else ch[cur]++;
        }
        
        for(int i = 0; i < t.length(); i++) {
            int cur = t.charAt(i) - 'a';
            if(ch[cur] != -1) ch[cur]--;
        }

        for(int n : ch) {
            if(n != -1 && n > 0) return false;
        }

        return true;
    }
}