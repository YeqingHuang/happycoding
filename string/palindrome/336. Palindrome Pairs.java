class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        // 3 cases:
        // s2 is a reversed string of s1, then s1+s2 is valid
        // s2 = (palindrome part + reversed s1), then s1+s2 is valid
        // s1 = reversed s2 + parlinform part, then s1+s2 is valid
        
        // for case2, if a prefix of a word is p, we can see if the reversed rest part matches any word
        // for case3, if a suffix of a word is p, we can check if the reversed prefix matches any word
        
        Map<String, Integer> map = new HashMap<>(); // word, index
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();

        for (String word : map.keySet()) {
            int index = map.get(word);
            String reversedWord = new StringBuilder(word).reverse().toString();
            // case1
            if (map.containsKey(reversedWord) && map.get(reversedWord) != index) {
                ans.add(Arrays.asList(index, map.get(reversedWord)));
            }
            // case2
            for (String suffix: findSuffixes(word)) {
                String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                if (map.containsKey(reversedSuffix)) {
                    ans.add(Arrays.asList(map.get(reversedSuffix), index));
                }
            }
            // case3:
            for (String prefix: findPrefixes(word)) {
                 String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                if (map.containsKey(reversedPrefix)) {
                    ans.add(Arrays.asList(index, map.get(reversedPrefix)));
                }
            }
        }
        return ans;
    }
    
    private List<String> findPrefixes(String word) {
        List<String> ans = new ArrayList<>();
        for (int i=0; i<word.length(); i++) {
            if (palindromePart(word, i, word.length()-1)) {
                // we need to check word.substring(0,i)
                ans.add(word.substring(0,i));
            }
        }
        return ans;
    }
    
    private List<String> findSuffixes(String word) {
        List<String> ans = new ArrayList<>();
        for (int i=0; i<word.length(); i++) {
            if (palindromePart(word, 0, i)) {
                // we need to check word.substring(0,i)
                ans.add(word.substring(i+1, word.length()));
            }
        }
        return ans;
    }
    
    private boolean palindromePart(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}