class Solution {
    public int maxProfit(int[] prices) {
        // suppose we do two transactions
        // there must be a day i, where you complete 1st transaction during [0,i]
        // and you complete the 2nd transaction from day[i,n-1]
        // we just don't know where is i
        int n = prices.length;
        
        // 1st transaction, check from left to right
        // maintain a minPrice(reflects a buy day), then you sell at day i
        int minPrice = prices[0];
        int[] first = new int[n];
        for (int i=1; i<n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            int sellGain = Math.max(0, prices[i] - minPrice); // sell at day i
            first[i] = Math.max(first[i-1], sellGain); // sell at or before day i
        }
        
        // 2nd transaction, check from right to left
        // maintain a maxPrice(reflects a sell day), then buy at day i
        int maxPrice = prices[n-1];
        int[] second = new int[n];
        for (int i=n-2; i>=0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            int buyGain = Math.max(0, maxPrice - prices[i]); // buy at day i
            second[i] = Math.max(second[i+1], buyGain); // buy at or after day i
        }

        int gain = 0;
        for (int i=1; i<n; i++) {
            gain = Math.max(gain, first[i] + second[i]);
        }
        return gain;
    }
}