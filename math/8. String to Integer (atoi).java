class Solution {
    public int myAtoi(String s) {
        // "0032" is also valid
        s = s.trim();
        if (s.length() == 0) return 0;
        
        boolean isNegative = false;
        if (s.charAt(0) == '-') {
            isNegative = true;
            s = s.substring(1);
        } else if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        if (s.length() == 0) return 0;
        
        int i = 0;
        int ans = 0;
        while (i<s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';
            // if it overflows, we can terminate here
            if (ans > Integer.MAX_VALUE/10 || (ans == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE%10)) {
                return isNegative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = 10 * ans + digit;
            i++;
        }
        
        return isNegative ? -ans: ans;
    }
}