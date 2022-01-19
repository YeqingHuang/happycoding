class Solution {
    int maxCount;
    public int maxUniqueSplit(String s) {
        maxCount = 0;
        Set<String> set = new HashSet<>(); // keep the generated substrings
        helper(s, 0, set);
        return maxCount;
    }
    
    private void helper(String s, int start, Set<String> generated) {
        if (start == s.length()) {
            maxCount = Math.max(maxCount, generated.size());
        }
        
        for (int i=start; i<s.length(); i++) {
            String chunk = s.substring(start, i+1);
            if (!generated.contains(chunk)) {
                generated.add(chunk);
                helper(s, i+1, generated);
                generated.remove(chunk);
            }
        }
    }
}