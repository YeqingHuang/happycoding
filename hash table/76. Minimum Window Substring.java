class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) return "";
        
        Map<Character, Integer> target = new HashMap<>();
        for (char c: t.toCharArray()) {
            target.put(c, target.getOrDefault(c,0) + 1);
        }
        
        int desired = target.size();
        int collected = 0;
        int[] ans = new int[]{-1,-1}; // length is (j-i+1)
        Map<Character, Integer> curr = new HashMap<>();
        int i=0, j=0;
        while (j < s.length()) {
            char c = s.charAt(j);
            curr.put(c, curr.getOrDefault(c, 0) + 1);
            if (target.containsKey(c) && target.get(c).equals(curr.get(c))) {
                // this character has made a contribution
                collected++;
            }
            while (collected == desired) {
                // we've find an answer, record it
                if (ans[0] == -1 || ans[1] - ans[0] > j - i) {
                    ans[0] = i;
                    ans[1] = j;
                }
                // try to shrink from left
                char removed = s.charAt(i++);
                curr.put(removed, curr.get(removed) - 1);
                if (target.containsKey(removed) && target.get(removed) > curr.get(removed)) {
                    collected--;
                }
            }
            j++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[0], ans[1]+1);
    }
}