class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // [3,6,7,11] h = 8
        // if k = 11, she can eat in 4 hours, but we want the min k
        // if k = 4, the hours taken is 1,2,2,3, sum is 8 hours
        int maxPile = 0;
        for (int pile: piles) {
            maxPile = Math.max(pile, maxPile);
        }
        int low = 1;
        int high = maxPile;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (!possible(piles, h, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return possible(piles, h, low) ? low : high;
    }
    
    private boolean possible(int[] piles, int h, int k) {
        int totalTime = 0;
        for (int pile: piles) {
            int time = pile / k;
            if (pile % k != 0) time++;
            totalTime += time;
        }
        return totalTime <= h;
    }
}