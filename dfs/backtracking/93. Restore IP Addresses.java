class Solution {
    List<String> ans;
    public List<String> restoreIpAddresses(String s) {
        // backtracking
        ans = new ArrayList<>();
        helper(s, 0, new ArrayList<>());
        return ans;
    }
    
    private void helper(String s, int start, List<String> path) {
        if (start == s.length()) {
            if (path.size() == 4) {
                ans.add(String.join(".", path));
            }
            return;
        }
        
        // one digit: always possible
        // two digits: check length. Then convert it to integer, should >= 10 
        // three digits: check length. Then convert it to integer, should >= 100 and <= 255
        String chunk1 = s.substring(start, start+1);
        path.add(chunk1);
        helper(s, start+1, path);
        path.remove(path.size() - 1);
        
        if (start + 1 < s.length()) {
            String chunk2 = s.substring(start, start + 2);
            if (Integer.parseInt(chunk2) >= 10) {
                path.add(chunk2);
                helper(s, start+2, path);
                path.remove(path.size() - 1);
            }
        }
        
        if (start + 2 < s.length()) {
            String chunk3 = s.substring(start, start + 3);
            int value = Integer.parseInt(chunk3);
            if (value >= 100 && value <= 255) {
                path.add(chunk3);
                helper(s, start+3, path);
                path.remove(path.size() - 1);
            }
        }
    }
}