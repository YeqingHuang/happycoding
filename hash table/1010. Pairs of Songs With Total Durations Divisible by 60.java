class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        if (time.length == 0) return ans;
        
        int[] freq = new int[60];
        for (int t: time) {
            t = t % 60;
            if (t != 0) {
                ans += freq[60 - t];
            } else {
                ans += freq[0];
            }
            freq[t]++;
        }
        return ans;
    }
}