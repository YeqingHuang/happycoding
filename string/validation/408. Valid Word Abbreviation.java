class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        // 1. no leading zeros or empty replacement
        // which means a 0 followed by a letter is always wrong
        // 2. no adjacent replacement
        // which means 12 should be treated as 12, not 1 and 2
        if (!passZeroChecking(abbr)) return false;
        
        int i = 0, j = 0;
        int count = 0;
        while (i < word.length() && j < abbr.length()) {
            char c1 = word.charAt(i);
            char c2 = abbr.charAt(j);
            if (Character.isLetter(c2)) {
                if (c1 != c2) return false;
                else {
                    i++;
                    j++;
                }
            } else {
                count = count * 10 + (c2 - '0');
                if (j+1 < abbr.length() && Character.isLetter(abbr.charAt(j+1))) {
                    // it's time to have a check
                    i += count;
                    count = 0;
                    if (i >= word.length()) return false;
                } else if (j == abbr.length() - 1) {
                    // also needs to check
                    return count + i == word.length();
                }
                j++;
            }
        }
        return i == word.length() && j == abbr.length();
    }
    
    private boolean passZeroChecking(String s) {
        if (s.charAt(0) == '0') return false;
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i) == '0' && Character.isLetter(s.charAt(i-1))) {
                return false;
            }
        }
        return true;
    }
}