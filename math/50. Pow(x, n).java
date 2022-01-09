class Solution {
    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return powHelper(x, n);
    }
    
    private double powHelper(double x, int n) {
        if (n == 0) return 1.0;
        // top-down, O(logn)
        double half = powHelper(x, n/2);
        return n % 2 == 0? half * half : half * half * x;
    }
}