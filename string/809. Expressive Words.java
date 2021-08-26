class Solution {
    public int expressiveWords(String s, String[] words) {
        if (s == null || words == null) {
            return 0;
        }
        int count = 0;
        for (String word : words) {
            if (stretchy(s, word)) {
                count++;
            }
        }
        return count;
    }
    
    public boolean stretchy(String s, String word) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                int len1 = getLength(s, i);
                int len2 = getLength(word, j);
                if (len1 < len2 || (len1 != len2 && len1 < 3)) {
                    return false;
                }
                i += len1;
                j += len2;
            } else {
                return false;
            }
        }
        return i == s.length() && j == word.length();
    }
    
    public int getLength(String s, int start) {
        int k = start;
        while (k < s.length() && s.charAt(k) == s.charAt(start)) {
            k++;
        }
        return k - start;
    }
}