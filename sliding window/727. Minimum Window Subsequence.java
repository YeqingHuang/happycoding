class Solution {
    // two pointer, O(n) time and O(1) space
    public String minWindow(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) return "";
        
        String ans = "";
        int minLen = Integer.MAX_VALUE;
        int j = 0;
        
        while (j < s1.length()) {
            int s2Index = 0;
            while (j < s1.length()) {
                if (s1.charAt(j) == s2.charAt(s2Index)) {
                    s2Index++;
                }
                if (s2Index == s2.length()) {
                    // we've found an answer
                    break;
                }
                j++;
            }
            // check why we come out of the loop
            if (j == s1.length()) {
                break;
            }
            
            // use another pointer to go from right to left
            int i = j;
            s2Index = s2.length() - 1;
            while (i >= 0) {
                if (s1.charAt(i) == s2.charAt(s2Index)) {
                    s2Index--;
                }
                if (s2Index < 0) {
                    break;
                }
                i--;
            }
            
            // now this is an local optimal answer
            if (j - i + 1 < minLen) {
                minLen = j - i + 1;
                ans = s1.substring(i, j+1);
            }
            // now check the next possible piece
            j = i + 1;
        }
        return ans;
    }
}