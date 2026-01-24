class Solution {
    public int myAtoi(String s) {
        boolean flag = false;
        int idx = 0;
        while(s.length() > idx && s.charAt(idx) == ' ') idx++;
        if(s.length() > idx && s.charAt(idx) == '-') {
            flag = true;
            idx++;
        } else if (s.length() > idx && s.charAt(idx) == '+') idx++;
        if(s.length() > idx && !Character.isDigit(s.charAt(idx))) return 0;

        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();
        
        while(s.length() > idx && Character.isDigit(s.charAt(idx))) {
            queue.offer(s.charAt(idx) - '0');
            idx++;
        }

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            if(flag) cur = cur * -1;
            if(answer > Integer.MAX_VALUE / 10 || (answer == Integer.MAX_VALUE / 10 && cur > 7)) return Integer.MAX_VALUE;
            if(answer < Integer.MIN_VALUE / 10 || (answer == Integer.MIN_VALUE / 10 && cur < -8)) return Integer.MIN_VALUE;
            answer = answer * 10 + cur;
        }
        return answer;
    }
}