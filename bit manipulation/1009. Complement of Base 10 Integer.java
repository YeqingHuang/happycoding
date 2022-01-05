class Solution {
    public int bitwiseComplement(int n) {
        // find out how many digits n has 
        // then return 2^x - 1 - n
        if (n == 0) return 1;
        
        int initial = n;
        int digits = 0;
        while (n > 0) {
            n = n>>1;
            digits++;
        }

        return (1 << digits) - 1 - initial;
    }
}