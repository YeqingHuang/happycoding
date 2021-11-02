class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return helper(x, n);
    }
    
    private double helper(double x, int n) {    
        // we use a top-down approach, time is O(logn)
        // base case
        if (n == 0) return 1.0;
        // general, recursive
        double half = helper(x, n/2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}