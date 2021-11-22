class Solution {
    public int hammingDistance(int x, int y) {
        // how many bits are different between x and y
        // 7:  0 1 1 1
        // 14: 1 1 1 0
        // first digit and last digit are different, so ans is 2
        
        // (use right shift) we check one digit at a time
        int ans = 0;
        int xor = x ^ y;
        while (xor != 0) {
            if (xor % 2 == 1) {
                ans++; // this digit is different
            }
            xor = xor >> 1;
        }
        return ans;
    }
}