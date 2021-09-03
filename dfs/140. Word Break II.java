class Solution {
    private Set<String> set;
    private Map<Integer, List<String>> map;
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        map = new HashMap<>();
        return dfs(s, 0);
    }
    
    private List<String> dfs(String s, int start) {
        if (map.containsKey(start)) {
            return map.get(start); // we've calculated this latter part
        }
        
        List<String> ans = new ArrayList<>(); 
        if (start == s.length()) {
            ans.add(""); // base case is to create a new string
        }
        
        // process the substring [start, s.length())
        for (int i=start; i<s.length(); i++) {
            String possible = s.substring(start, i+1);
            if (set.contains(possible)) {
                List<String> nextParts = dfs(s, i+1);
                for (String nextPart: nextParts) {
                    if (nextPart == "") {
                        // if we don't check this edge case, the answer becomes
                        // ["cats and dog ","cat sand dog "]
                        ans.add(possible);
                    } else {
                        ans.add(possible + " " + nextPart);
                    }
                }
            }
        }
        map.put(start, ans);
        return ans;
    }
}