class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
        int MOD = (int) 1e9 + 7;
        // when we meet buy, consume sell from smallest, minHeap
        // when we meet sell, consume buy from biggest, maxHeap
        PriorityQueue<int[]> sellPQ = new PriorityQueue<>((a,b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> buyPQ = new PriorityQueue<>((a,b) -> Integer.compare(b[0], a[0]));
        for (int[] order: orders) {
            if (order[2] == 0) {
                // this is buy, we want to match with sell
                int buyPrice = order[0];
                int buyAmount = order[1];
                while (buyAmount > 0 && !sellPQ.isEmpty()) {
                    int[] topSell = sellPQ.peek();
                    if (topSell[0] <= buyPrice) {
                        sellPQ.poll();
                        if (buyAmount < topSell[1]) {
                            sellPQ.offer(new int[]{topSell[0], topSell[1] - buyAmount});
                            buyAmount = 0;
                        } else {
                            buyAmount -= topSell[1];
                        }
                    } else {
                        // cannot match
                        break;
                    }
                }
                if (buyAmount > 0) {
                    buyPQ.offer(new int[]{buyPrice, buyAmount});
                }
            } else {
                // this is sell, we want to match with buy
                int sellPrice = order[0];
                int sellAmount = order[1];
                while (sellAmount > 0 && !buyPQ.isEmpty()) {
                    int[] topBuy = buyPQ.peek();
                    if (topBuy[0] >= sellPrice) {
                        buyPQ.poll();
                        if (sellAmount < topBuy[1]) {
                            buyPQ.offer(new int[]{topBuy[0], topBuy[1] - sellAmount});
                            sellAmount = 0;
                        } else {
                            sellAmount -= topBuy[1];
                        }
                    } else {
                        // cannot match
                        break;
                    }
                }
                if (sellAmount > 0) {
                    sellPQ.offer(new int[]{sellPrice, sellAmount});
                }
            }
        }
        
        int ans = 0;
        for (int[] b: buyPQ) {
            ans = (ans + b[1]) % MOD;
        }
        for(int[] s: sellPQ) {
            ans = (ans + s[1]) % MOD;
        }
        return ans % MOD;
    }
}