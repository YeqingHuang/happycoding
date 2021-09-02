class Solution {
    final char[][] rules = {
        {},{},{'a','b','c'}, {'d','e','f'},
        {'g','h','i'},{'j','k','l'},{'m','n','o'},
        {'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}
    };
    
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        dfs(ans, digits, new ArrayList<>(), 0);
        return ans;
    }
    
    // instead of using List<Character> path, we can directly use StringBuilder
    // remove last char: path.deleteCharAt(path.length()-1);
    private void dfs(List<String> ans, String s, List<Character> path, int start) {
        if (start == s.length()) {
            StringBuilder sb = new StringBuilder();
            for (char c: path) {
                sb.append(c);
            }
            ans.add(sb.toString());
            return;
        }
        
        char[] options = rules[s.charAt(start) - '0'];
        for (char c: options) {
            path.add(c);
            dfs(ans, s, path, start+1);
            path.remove(path.size()-1);
        }
    }
}