class Solution {
    public int reverse(int x) {
        // the tricky part is that we are not allowed to use long
        // how to check overflow beforehand?
        // if num > Integer.MAX_VALUE / 10, it's impossible (e.g 37 vs 358/10)
        // if num == Integer.MAX_VALUE / 10, we need to compare last digit of num with Integer.MAX_VALUE % 10
        if (x == 0) return 0;
        
        if (x > 0) return reversePos(x);
        return reverseNeg(x);
    }
    
    private int reversePos(int x) {
        int ans = 0;
        while (x > 0) {
            int digit = x % 10;
            x = x/10;
            if (ans > Integer.MAX_VALUE/10 || ans == Integer.MAX_VALUE/10 && digit > Integer.MAX_VALUE%10) {
                return 0;
            }
            // we only make it bigger when we are sure it won't overflow
            ans = ans * 10 + digit;
        }
        return ans;
    }
    
    private int reverseNeg(int x) {
        int ans = 0;
        while (x < 0) {
            int digit = x % 10;
            x = x/10;
            if (ans < Integer.MIN_VALUE/10 || ans == Integer.MIN_VALUE/10 && digit < Integer.MIN_VALUE%10) {
                return 0;
            }
            ans = ans * 10 + digit;
        }
        return ans;
    }
}