class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        
        // use a sliding window in s2, i.e. make the length equal to s1.length()
        // every time we compare two hashmaps, if they are the same, return true
        int[] map1 = new int[26];
        int[] map2 = new int[26];
        for (int i=0; i<s1.length(); i++) {
            map1[s1.charAt(i) - 'a']++;
            map2[s2.charAt(i) - 'a']++;
        }
        
        if (sameMap(map1, map2)) return true;
        
        for (int i=0; i< s2.length() - s1.length(); i++) {
            char removed = s2.charAt(i);
            char added = s2.charAt(i+s1.length());
            map2[removed - 'a']--;
            map2[added - 'a']++;
            if (sameMap(map1, map2)) return true;
        }
        return false;
    }
    
    private boolean sameMap(int[] map1, int[] map2) {
        for (int i=0; i<map1.length; i++) {
            if (map1[i] != map2[i]) return false;
        }
        return true;
    }
}