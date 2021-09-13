class Solution {
    public String minimizeError(String[] prices, int target) {
        // [1.4, 3.2, 4.5] target is 9
        // we use ceil, floor, floor 2+3+4 to achieve 9, diff = 0.6 + 0.2 + 0.5 = 1.3
        // however use floor, floor, ceil 1+3+5 also equals to 9, diff = 0.4 + 0.2 + 0.5 = 1.1
        
        // by default, we can floor every num and compare the sum with target
        // then we know how many numbers we need to alter to ceil
        // greedy: those double closer to ceil have lower cost, choose these ones
        // therefore, we use a minHeap
        double ans = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>(); // store the cost
        for (String s: prices) {
            double price = Double.valueOf(s);
            double floorVal = Math.floor(price);
            double ceilingVal = Math.ceil(price); 
            double costToDown = price - floorVal;
            double costToUp = ceilingVal - price;
            if (floorVal != ceilingVal) {
                // it's not an integer, we have the opportunity to change it
                
                // if a number is X.500, we can see it has a cost of 0
                // if a number is X.200, 0.8 - 0.2 = 0.6
                // if a number is X.700, 0.3 - 0.7 = -0.4
                pq.offer(costToUp - costToDown);
                
            } 
            ans += costToDown;
            target -= floorVal;
        }
        
        if (target < 0 || target > pq.size()) return "-1";
        // now target stands for the count of numbers we must use Math.ceil()
        while (target > 0) {
            ans += pq.poll();
            target--;
        }
        return String.format("%.3f", ans);
    }
}