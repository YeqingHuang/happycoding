class Solution {
    public int lengthOfLongestSubstring(String s) {
        // "pwwkew"
        // sliding window: everytime we find a valid substring, record its length
        // if newly added char is duplicated, we shrink from left until it is again valid
        if (s == null || s.length() == 0) return 0;
        
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int i = 0;
        int j = 0;
        while (j < s.length()) {
            char added = s.charAt(j);
            while (set.contains(added)) {
                // remove chars on the left until it's not there
                set.remove(s.charAt(i));
                i++;
            }
            set.add(added);
            maxLen = Math.max(maxLen, set.size());
            j++;
        }
        return maxLen;
    }
}