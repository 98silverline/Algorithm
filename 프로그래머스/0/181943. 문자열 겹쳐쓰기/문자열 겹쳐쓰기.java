class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        int end = s + overwrite_string.length();
        String answer = "";
        answer = my_string.substring(0, s) + overwrite_string + my_string.substring(end);
        return answer;
    }
}