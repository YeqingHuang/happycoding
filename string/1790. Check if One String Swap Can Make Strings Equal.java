class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        
        int[] diff = new int[]{-1,-1}; // store two index
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff[0] == -1) {
                    diff[0] = i; 
                } else if (diff[1] == -1) {
                    diff[1] = i;
                } else {
                    return false;
                }
            }
        }
        int x = diff[0], y = diff[1];
        if (y == -1) return false; // only one char is different
        return s1.charAt(x) == s2.charAt(y) && s1.charAt(y) == s2.charAt(x);
    }
}