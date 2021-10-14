class Solution {
    public int longestSubstring(String s, int k) {
        // naive: O(n^2), fix i and expand j
        // once it is a valid answer, we update the ans
        if (s == null || s.length() == 0) return 0;
        if (k == 1) return s.length();
        
        int ans = 0;
        for (int i=0; i<s.length(); i++) {
            int[] map = new int[26];
            for (int j=i; j<s.length(); j++) {
                map[s.charAt(j) - 'a']++;
                if (validMap(map, k)) {
                    ans = Math.max(ans, j-i+1);
                }
            }
        }
        return ans;
    }
    
    private boolean validMap(int[] map, int k) {
        for (int count: map) {
            if (count != 0 && count < k) {
                return false;
            }
        }
        return true;
    }
}

class Solution1 {
    // cannot understand this O(maxUnique * n) solution
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        if (k == 1) return s.length();
        
        // suppose there are 4 unique characters in s
        // we will find the answer for including only 1 unique char, including 2, including 3,...
        int maxUnique = findMaxUnique(s);
        int ans = 0;
        for (int i=1; i<=maxUnique; i++) {
            int[] map = new int[26];
            int left = 0, right = 0;
            int currUnique = 0, validUnique = 0;
            while (right < s.length()) {
                if (currUnique <= i) {
                    int index = s.charAt(right) - 'a';
                    map[index]++;
                    if (map[index] == 1) currUnique++;
                    if (map[index] == k) validUnique++;
                    right++;
                } else {
                    // try to shrink from left
                    // once it no longer satisfies currUnique > i, it falls into if condition
                    int removedIndex = s.charAt(left) - 'a';
                    map[removedIndex]--;
                    if (map[removedIndex] == k - 1) validUnique--;
                    if (map[removedIndex] == 0) currUnique--;
                    left++;
                }
                // we cannot check this in if, because left is not optimal
                // usually len = right - left + 1
                // but we have overshoot left by 1, right - (left+1) + 1 = right - left
                if (currUnique == i && currUnique == validUnique) {
                    ans = Math.max(ans, right - left);
                }
            }
        }
        return ans;
    }
    
    
    private int findMaxUnique(String s) {
        Set<Character> set = new HashSet<>();
        for (char c: s.toCharArray()) {
            set.add(c);
        }
        return set.size();
    }
}