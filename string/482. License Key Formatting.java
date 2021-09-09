class Solution {
    public String licenseKeyFormatting(String s, int k) {
        s = s.replaceAll("-", "").toUpperCase();
        
        int n = s.length();
        int r = n % k;
        StringBuilder ans = new StringBuilder();
        if (r > 0) {
            ans.append(s.substring(0, r));
        }
        if (n / k == 0) return ans.toString();
        
        int i = r;
        while (i < s.length()) {
            ans.append("-");
            ans.append(s.substring(i, i+k));
            i += k;
        }
        return r == 0 ? ans.substring(1) : ans.toString();
    }

    public String licenseKeyFormatting1(String s, int k) {
        s = s.replaceAll("-", "").toUpperCase();
        
        // we can go from right to left to prevent checking remainder
        // "-" is the last char of every section with length k+1
        StringBuilder ans = new StringBuilder();
        for (int i=s.length()-1; i>=0; i--) {
            if (ans.length() % (k+1) == k) {
                ans.append("-");
            }
            ans.append(s.charAt(i));
        }
        return ans.reverse().toString();
    }
}