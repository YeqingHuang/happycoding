class Solution {
    // O(logn), most staightforward solution
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        
        while (n > 1) {
            if (n % 2 != 0) {
                return false;
            }
            n /= 2;
        }
        return true;
    }

    // O(1)
    public boolean isPowerOfTwo(int n) {
        // Q1: how to use bit manipulation? 
        // The pattern for those numbers which are power of two:
        // 1: 00000001
        // 2: 00000010
        // 4: 00000100
        // 8: 00001000
        // there should be only one 1 in it, if we set it as 0, the number should be all 0s
        // Q2: how to set the rightmost 1 as 0?
        // suppose x is 100, then x-1 is 011, we use AND, then x & (x-1) = 0
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
        // P.S. if n = Integer.MIN_VALUE, i.e. - 2^31, n - 1 will overflow
        // it is beter to convert n to long then do n & (n - 1)
    }
}