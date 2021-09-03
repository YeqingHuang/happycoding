class Solution {
    Map<String, Integer> map;  // the maxLen of the chain starting with this key
    Map<Integer, List<String>> source; // words with the same length are stored together
    
    public int longestStrChain(String[] words) {
        // "abc" is a before status for "abdc" (insert one char)
        // return the length of longest word chain given words
        
        source = new HashMap<>();
        for (String word: words) {
            int len = word.length();
            source.putIfAbsent(len, new ArrayList<>());
            source.get(len).add(word);
        }
        
        map = new HashMap<>();
        for (String word: words) {
            map.put(word, 1);
        }
        
        int maxLen = 1;
        Arrays.sort(words, (a,b) -> b.length() - a.length());
        for (String word: words) {
            int nextLen = word.length() + 1;
            if (source.containsKey(nextLen)) {
                for (String nextWord: source.get(nextLen)) {
                    if (isPredecessor(word, nextWord) && map.get(nextWord) + 1 > map.get(word)) {
                        int newLen = map.get(nextWord) + 1;
                        map.put(word, newLen);
                        maxLen = Math.max(maxLen, newLen);
                    }
                }
            }
        }
        return maxLen;
    }
    
    private boolean isPredecessor(String s1, String s2) {
        // suppose s2.length() - s1.length() == 1
        int mismatch = 0;
        int i = 0, j = 0;
        while (i<s1.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else {
                mismatch++;
                if (mismatch > 1) return false;
                j++;
            }
        }
        return i == s1.length();
    }
}