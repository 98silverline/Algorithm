

class Solution {

    public int countLargestGroup(int n) {
        
        int answer = 0;
        int largeN = 0;

        int[] arr = new int[37];

        for(int i = 1; i <= n; i++) {
            int div = i;
            int sum = 0; //자릿수 다 더한거
            while(div != 0) {
                sum += div % 10;
                div = div / 10;
            }
            arr[sum]++;
            if(arr[sum] > largeN) {
                largeN = arr[sum];
                answer = 1;
            } else if (arr[sum] == largeN) {
                answer++;
            }
        }
        return answer;
    }
}