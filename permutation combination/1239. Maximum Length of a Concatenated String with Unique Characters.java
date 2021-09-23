class Solution {
    public int maxLength(List<String> arr) {
        List<String> valid = new ArrayList<>();
        valid.add("");
        int maxLen = 0;
        
        for (String s: arr) {
            if (!validOption(s)) continue;
            // we cannot modify a list while we iterate it
            // ConcurrentModificationException
            // let's temporarily store them and add later
            List<String> added = new ArrayList<>();
            for (String existing: valid) {
                String newWord = existing + s;
                if (validOption(newWord)) {
                    added.add(newWord);
                    maxLen = Math.max(maxLen, newWord.length());
                }
            }
            valid.addAll(added);
        }
        return maxLen;
    }
    
    private boolean validOption(String s) {
        if (s.length() > 26) return false;
        boolean[] seen = new boolean[26];
        for (char c: s.toCharArray()) {
            if (seen[c-'a']) {
                return false;
            } else {
                seen[c-'a'] = true;
            }
        }
        return true;
    }
}