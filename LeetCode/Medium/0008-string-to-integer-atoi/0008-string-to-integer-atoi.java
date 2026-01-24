class Solution {
    public int myAtoi(String s) {
        int sign = 1;
        int idx = 0;
        int len = s.length();
        long answer = 0;
        while(idx < len && s.charAt(idx) == ' ') idx++;
        if(idx >= len) return 0;
        if(s.charAt(idx) == '-' || s.charAt(idx) == '+') {
            sign = s.charAt(idx) == '-' ? sign * -1 : 1;
            idx++;
        }

        while(idx < len && Character.isDigit(s.charAt(idx))) {
            answer = answer * 10 + s.charAt(idx) - '0';
            if(answer > Integer.MAX_VALUE) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            idx++;
        }

        return (int) answer * sign;
    }
}