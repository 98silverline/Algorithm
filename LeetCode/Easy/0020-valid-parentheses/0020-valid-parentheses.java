class Solution {
    public boolean isValid(String s) {
        HashMap<Character, Character> hm = new HashMap<>();
        Stack<Character> stk = new Stack<>();
        hm.put(')', '(');
        hm.put('}', '{');
        hm.put(']', '[');
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stk.push(s.charAt(i));
            } else {
                if (stk.isEmpty()) return false;
                if(hm.get(s.charAt(i)) != stk.pop()) return false; 
            }
        }
        if(stk.isEmpty()) return true;
        else return false;
    }
}