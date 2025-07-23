class Solution {
    public int maximumGain(String s, int x, int y) {
        int answer = 0;
        if (x < y) {
            s = new StringBuilder(s).reverse().toString();
            int temp = x;
            x = y;
            y = temp;
        }
        Stack<Character> stk = new Stack<>();
        for(char c : s.toCharArray()) {
            if(!stk.isEmpty() && stk.peek() == 'a' && c == 'b') {
                stk.pop();
                answer += x;
            } else {
                stk.push(c);
            }
        }
        Stack<Character> stk2 = new Stack<>();
        while(!stk.isEmpty()) {
            char c = stk.pop();
            if(!stk2.isEmpty() && c == 'b' && stk2.peek() == 'a') {
                stk2.pop();
                answer += y;
            } else {
                stk2.push(c);
            }
        }
        return answer;
    }
}