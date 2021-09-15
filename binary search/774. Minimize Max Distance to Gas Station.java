class Solution {
    // same with 875. Koko Eating Bananas
    public double minmaxGasDist(int[] stations, int k) {
        // ideally, we want the distances between adjacent stations to be the same
        // initially, we have a max distance, assign it as high, 
        // and low is 0 (if we can put infinite stations between two stations)
        // how to check if distance D is possible given k?
        // for every existing distance x, to make it equal to or smaller than D
        // we need to split it into x/D + 1 sections, i.e. x = 8, D = 3, we need 2 new stations
        int maxDist = 0;
        for (int i=1; i<stations.length; i++) {
            maxDist = Math.max(maxDist, stations[i] - stations[i-1]);
        }
        
        double low = 0;
        double high = maxDist;
        // cannot write high >= low
        while (high > low  + 1e-6) {
            double mid = (low + high) / 2.0;
            if (possible(stations, k, mid))
                high = mid;
            else
                low = mid;
        }
        return low;
    }
    
    private boolean possible(int[] stations, int k, double dist) {
        int needed = 0;
        for(int i=1; i<stations.length; i++) {
            int currDist = stations[i] - stations[i-1];
            needed += (int) currDist / dist;
        }
        return needed <= k;
    }
}