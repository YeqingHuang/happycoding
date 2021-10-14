class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        Map<Character, Integer> target = new HashMap<>();
        for (char c: s1.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }
        
        Set<Character> collected = new HashSet<>();
        Map<Character, Integer> curr = new HashMap<>();
        for (int i=0; i<s2.length(); i++) {
            char c = s2.charAt(i);
            curr.put(c, curr.getOrDefault(c, 0) + 1);
            if (target.containsKey(c) && target.get(c).equals(curr.get(c))) {
                collected.add(c);
            }
            if (i >= s1.length()) {
                // we need to remove a char
                char removed = s2.charAt(i-s1.length());
                curr.put(removed, curr.get(removed) -1);
                if (target.containsKey(removed) && target.get(removed) > curr.get(removed) && collected.contains(removed)) {
                    // check this case: "tritro" and "trophennetritro"
                    // the first two conditions are not enough
                    // suppose we need two 't' and in curr we have one 't', after updating we have zero 't'
                    // now target.get('t') > curr.get('t'), but we should not collected.remove('t'), it's not there at all
                    collected.remove(removed);
                }
            }
            if (collected.size() == target.size()) {
                return true;
            }
        }
        return false;
    }
}

class Solution2 {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        // another straightforward version, compare two hashMaps every time
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (int i=0; i<s1.length(); i++) {
            map1[s1.charAt(i) - 'a']++;
            map2[s2.charAt(i) - 'a']++;
        }
        if (sameMap(map1, map2)) return true;
        
        for (int i=s1.length(); i<s2.length(); i++) {
            // now we need to update map2
            char added = s2.charAt(i);
            char removed = s2.charAt(i - s1.length());
            if (added != removed) {
                map2[added - 'a']++;
                map2[removed - 'a']--;
            }
            if (sameMap(map1, map2)) return true;
        }
        return false;
    }
    
    private boolean sameMap(int[] map1, int[] map2) {
        for (int i=0; i<26; i++) {
            if (map1[i] != map2[i]) {
                return false;
            }
        }
        return true;
    }
}