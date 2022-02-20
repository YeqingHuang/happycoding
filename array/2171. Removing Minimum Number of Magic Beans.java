class Solution {
    public long minimumRemoval(int[] beans) {
        // try each num as target
        // nums smaller than it need to be emptied as 0, cost is num - 0
        // nums bigger than it need some removal, cost is num - target
        // imagine there's a bar chart and figure out the area
        Arrays.sort(beans);
        
        long sum = 0;
        for (int bean: beans) {
            sum += bean;
        }
        
        long minCost = Long.MAX_VALUE;
        long bagCount = beans.length;
        for (int i=0; i<beans.length; i++) {
            minCost = Math.min(minCost, sum - bagCount * beans[i]);
            bagCount--;
        }
        return minCost;
    }
}