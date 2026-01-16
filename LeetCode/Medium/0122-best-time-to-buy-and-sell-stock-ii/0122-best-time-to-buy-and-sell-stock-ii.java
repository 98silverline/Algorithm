class Solution {
    public int maxProfit(int[] prices) {
        int[] profit = new int[prices.length];
        for(int i = 0; i < prices.length; i++) {
            int cur = prices[i];
            for(int j = i + 1; j < prices.length; j++) {
                profit[j] = Math.max(prices[j] - cur + profit[i], profit[j - 1]);
            }
        }
        return profit[prices.length - 1];
    }
}