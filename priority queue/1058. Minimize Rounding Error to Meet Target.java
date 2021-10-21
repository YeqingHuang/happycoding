class Solution {
    public String minimizeError(String[] prices, int target) {
        // greedy: if we need to round, pick those with smaller diff
        // suppose we choose to round down for all of them and get a sum
        // if the count of non-integer prices is x, we can reach up to sum + x
        int sum = 0;
        PriorityQueue<Double> minHeap = new PriorityQueue<>((a,b)->
            Double.compare(Math.ceil(a) - a, Math.ceil(b) - b));
        
        for (String price: prices) {
            double p = Double.valueOf(price);
            if ((int) Math.floor(p) != (int) Math.ceil(p)) {
                minHeap.add(p);
            } 
            sum += (int) Math.floor(p);
        }
        
        if (target < sum || target > sum + minHeap.size()) {
            return "-1";
        }
        
        int needsUp = target - sum;
        double cost = 0;
        while (!minHeap.isEmpty()) {
            double curr = minHeap.poll();
            if (needsUp > 0) {
                cost += Math.ceil(curr) - curr;
                needsUp--;
            } else {
                cost += curr - Math.floor(curr);
            }
        }
        return String.format("%.3f", cost);
    }
}