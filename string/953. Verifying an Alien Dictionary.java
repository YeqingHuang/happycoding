class Solution {
    int[] rules;
    
    public boolean isAlienSorted(String[] words, String order) {
        // check two adjacent words at a time
        // use a dictionary to check
        rules = new int[26];
        for (int i=0; i<order.length(); i++) {
            rules[order.charAt(i) - 'a'] = i;
        }
        
        for (int k=0; k<words.length - 1; k++) {
            if (!validTwoWords(words[k], words[k+1])) {
                return false;
            }
        }
        return true;
    }
    
    private boolean validTwoWords(String s1, String s2) {
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if (c1 != c2) {
                return rules[c1-'a'] < rules[c2-'a'];
            } else {
                i++;
            }
        }
        // "app","apple" valid, "apple","app" invalid
        return i == s1.length();
    }
}