class Solution {
    // time O(n)
    public int maxProfit(int[] prices) {
        // three status: hold, sold, reset
        // reset is the transient state before hold and sold
        // i.e. after sold status, it must go into reset for at least one day
        // three actions:
        // sell, buy, do nothing
        
        // sold[i] = hold[i-1] + price[i] (we cannot sell if we don't own a stock)
        // reset[i] = max(reset[i-1], sold[i-1]) (we come to reset either because of 
        // selling in the previous day, or we volunteerly do nothing)
        // hold[i] = max(hold[i-1], reset[i-1] - price[i])
        // when we choose to buy, the balance is do nothing - cost to buy
        
        // initially, the balance of do nothing is 0, i.e. reset[-1] = 0
        // on day 0, we are not at state hold or sold, to represent unreachable
        // sold[-1] = hold[-1] = Integer.MIN_VALUE
        
        int sold = Integer.MIN_VALUE;
        int hold = Integer.MIN_VALUE;
        int reset = 0;
        for (int price: prices) {
            int currSold = sold;
            sold = hold + price;
            hold = Math.max(hold, reset - price);
            reset = Math.max(reset, currSold);
        }
        // on the last day, we either do nothing or just sell the stock
        // it is clearly no benefit to hold it
        return Math.max(sold, reset);
    }

    // time O(n^2)
    public int maxProfit1(int[] prices) {
        // on each day, we either buy or do not buy
        // not buying for 3 reasons: 1) already bought 2) cooldown 3) willingly do nothing
        // we cannot buy on the last day, so start from n - 2
        // buy at day i and find best day j to sell
        
        int n = prices.length;
        if (n == 1) return 0;
        int[] dp = new int[n+2];
        
        for (int i=n-2; i>=0; i--) {
            int maxGain = 0;
            for (int j=i+1; j<n; j++) {
                // if we choose to sell at day j, we cannot by at j+1
                // therefore, we add dp[j+2] instead of dp[j+1]
                int currGain = prices[j] - prices[i] + dp[j+2];
                maxGain = Math.max(currGain, maxGain);
            }
            // at day i, either buy or do nothing            
            dp[i] = Math.max(maxGain, dp[i+1]);
        }
        return dp[0];
    }
}