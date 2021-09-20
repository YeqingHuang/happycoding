class Solution {
    
    private HashMap<Pair<Integer, Integer>, Integer> map;
    
    public int numDistinct(String s, String t) {
        // when two chars do not match, we do i++
        // however, if two chars match, we have two options:
        // 1) treat it as match, i++, j++
        // 2) ignore this match, i++
        // when j = t.length(), return 1, i.e. we've found an answer
        map = new HashMap<>();
        return helper(s, t, 0, 0);
    }
    
    private int helper(String s, String t, int i, int j) {
        int m = s.length();
        int n = t.length();
        if (j == n) {
            return 1;
        }
        if (i == m) {
            return 0;
        }
        
        Pair<Integer, Integer> key = new Pair<Integer, Integer>(i, j);
        if (map.containsKey(key)) {
            return map.get(key);
        }
        
        int ans = helper(s, t, i+1, j);
        if (s.charAt(i) == t.charAt(j)) {
            ans += helper(s, t, i+1, j+1);
        }
        map.put(key, ans);
        return ans;
    }
}