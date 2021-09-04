class Solution {
    public String reorganizeString(String s) {
        // strategy: cope with one char at a time
        // place at index 0,2,4,6...then 1,3,5...
        // we must begin with the most popular char, then process other chars. why? 
        // consider "aaabb",if we first get b _ b _ a, then the last two 'a's meet
        int[] freq = new int[26];
        char popular = s.charAt(0);
        int count = 1;
        for (char c: s.toCharArray()) {
            freq[c-'a']++;
            if (freq[c-'a'] > count) {
                popular = c;
                count = freq[c-'a'];
            }
        }
        
        int n = s.length();
        if (count > (n+1)/2) return "";
        
        char[] ans = new char[n];
        int i = 0;
        while (freq[popular-'a'] > 0) {
            ans[i] = popular;
            i += 2;
            freq[popular-'a']--;
        }
        for (int k=0; k<26; k++) {
            while (freq[k] > 0) {
                if (i >= n) i = 1;
                ans[i] = (char) (k + 'a');
                i += 2;
                freq[k]--;
            }
        }
        return String.valueOf(ans);
    }
}