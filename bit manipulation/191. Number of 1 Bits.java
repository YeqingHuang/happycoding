public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int mask = 1;
        int count = 0;
        // don't use mask <= n, because n can be negative
        for (int i=0; i<32; i++) {
            if ((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    // method2
    // https://en.wikipedia.org/wiki/Hamming_weight
    public int hammingWeight(int n) {
        // everytime, we can remove the rightmost 1 by n & (n-1)
        int count = 0;
        while (n != 0) {
            n = n & (n-1);
            count++;
        }
        return count;
    }
}