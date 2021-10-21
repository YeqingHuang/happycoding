class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> ans = new ArrayList<>();
        // case1: s1 is abc, s2 is cba
        // i.e. one is a reversed string of the other, so they form a pair
        
        // case2: one part in one string is palindrome, we want to find if
        // the other string complement the invalid part
        // 2a) s1: p part + invalid, s2: reversed(invalid), then s2 + s1 is ok
        // 2b) s1: invalid + p part, s2 : reversed(invalid), then s1 + s2 is ok
        
        // for 2a) if prefix is p, we stop and check if reversed right part matches any word
        // for 2b) if suffix is p, we stop and check if reversed left part matches any word
        // note that there may be multiple p part in s1
        Map<String, Integer> map = new HashMap<>();
        for (int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }
        
        String desired;
        for (int k=0; k<words.length; k++) {
            String word = words[k];
            // case1
            String reversed = reverseStr(word);
            if (map.containsKey(reversed) && map.get(reversed) != k) {
                // don't try to add {k, map.get(reversed)}, which will double the answer
                ans.add(Arrays.asList(map.get(reversed), k));
            }
            // case2a
            for (int i=0; i<word.length(); i++) {
                if (isPalindrome(word, 0, i)) {
                    desired = reverseStr(word.substring(i+1));
                    if (map.containsKey(desired)) {
                        ans.add(Arrays.asList(map.get(desired), k));
                    }
                }
            }
            // case2b
            for (int i=word.length() - 1; i >= 0; i--) {
                if (isPalindrome(word, i, word.length() - 1)) {
                    desired = reverseStr(word.substring(0,i));
                    if (map.containsKey(desired)) {
                        ans.add(Arrays.asList(k, map.get(desired)));
                    }
                }
            }
        }
        return ans;   
    }
    
    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    
    private String reverseStr(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}