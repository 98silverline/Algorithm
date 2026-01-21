class Solution {
    public int[] plusOne(int[] digits) {
        boolean flag = true;
        
        for(int n : digits) {
            if(n != 9) {
                flag = false;
                break;
            }
        }

        if(flag) {
            int[] answer = new int[digits.length + 1];
            answer[0] = 1;
            return answer;
        }

        if(digits[digits.length - 1] != 9) {
            digits[digits.length - 1]++;
            return digits;
        }

        for(int i = digits.length - 1; i >= 0; i--) {
            if(digits[i] + 1 == 10) {
                digits[i] = 0;
            } else {
                digits[i]++;
                break;
            }
        }
        return digits;
    }
}