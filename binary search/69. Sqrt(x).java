class Solution {
    public int mySqrt(int x) {
        // we want to find an integer i, such that i^2 <= x and (i+1)^2 > x
        // use binary search, low is 1, high is x
        // to avoid overflow, convert the conditions to i <= x/i && i+1 > x/i+1
        if (x <= 1) return x;
        
        int low = 1, high = x;
        while (low < high - 1) {
            int mid = (high - low)/2 + low;
            if (mid <= x/mid) {
                if (mid+1 > x/(mid+1)) {
                    return mid; // this is what we need
                } else {
                    low = mid; // mid is still too small
                }
            } else {
                high = mid; // mid is too big
            }
        }
        return high <= x/high ? high : low;
    }
}