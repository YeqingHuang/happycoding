class Solution {
    public int countBinarySubstrings(String s) {
        if (s.length() <= 1) return 0;
        
        // pattern: equal number of 1s + equal number of 0s
        // OR equal number of 0s + equal number of 1s
        // "11100111", convert it to [3,2,3], ans = Math.min(3,2) + Math.min(2,3)
        List<Integer> freq = new ArrayList<>();
        int count = 1;
        char c = s.charAt(0);
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) == c) {
                count++;
            } else {
                freq.add(count);
                count = 1;
                c = s.charAt(i);
            }
        }
        freq.add(count); // don't forget this line
        
        int ans = 0;
        for (int k=0; k<freq.size()-1; k++) {
            ans += Math.min(freq.get(k), freq.get(k+1));
        }
        return ans;
    }
}