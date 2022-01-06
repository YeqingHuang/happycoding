class Solution {
    List<List<String>> ans;
    // basic backtracking
    public List<List<String>> partition(String s) {
        if (s.length() == 0) return new ArrayList<>();
        
        ans = new ArrayList<>();
        backtracking(s, 0, new ArrayList<>());
        return ans;
    }
    
    private void backtracking(String s, int start, List<String> path) {
        if (start >= s.length()) {
            // we've done checking
            ans.add(new ArrayList<>(path));
        }
        
        for (int j = start; j < s.length(); j++) {
            if (isPalindrome(s, start, j)) {
                // this is a possible path
                path.add(s.substring(start, j+1));
                backtracking(s, j+1, path);
                path.remove(path.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}