class Solution {
    public List<String> ipToCIDR(String ip, int n) {
        // how do we know 255.0.0.8 can cover 8 IP addresses?
        // check the last part 00001000, it rightmost 1 appear at 4th digit from right
        // which means it covers all combinations from 000 to 111,i.e. 2^3
        // a simple trick to find rightmost 1 is to use x & (-x)
        // now x is 8, x & (-x) = 00001000 so it covers 8 addresses
        // we increament such as 1 + 8 + ... until the sum is equal to or bigger than n
        
        // but we not only need the counts, we want to know the actual CIDR
        // we can use a helper function to convert our current IP and count to a CIDR
        List<String> ans = new ArrayList();
        long x = 0;
        String[] parts = ip.split("\\.");
        for(int i = 0; i < 4; i++) {
          x = x * 256 + Long.parseLong(parts[i]);
        }
    
        while (n > 0) {
            // count is the number of IPs the current x can cover
            long count = x & -x; 
            // how to fix IP equals "0.0.0.0"???
            if (count == 0) {
                count = 1;
                while (count < n) {
                    count *= 2;
                }
            }
            
            while(count > n) { 
                // after some updates, remaining n is small, but count is big
                // such as in example1, n becomes 1
                // but 255.0.0.16 (last chunk 00010000) covers 16 addresses
                // there's no need to return a length of 28(i.e. 4 digits), 1 digit is enough
                count /= 2;
            }
      
            ans.add(oneCIDR(x, count));
            n -= (int)count;
            x += count;
            }
        return ans;
    }
    
    private String oneCIDR(long x, long count) {
        // step1: generate the IP
        // suppose the four parts form "a.b.c.d"
        int d = (int) x & 255; 
        x >>= 8; // throw away the rightmost part
        int c = (int) x & 255;
        x >>= 8;
        int b = (int) x & 255;
        x >>= 8;
        int a = (int) x & 255;
    
        // step2: generate the number after '/' based on count
        // len stands for how many digits it can cover
        // then what we want is 32 - len
        int len = 0;
	    while (count > 0) {
            count /= 2;
            len++;
        }
        int desired = 32 - (len-1);
        return a + "." + b + "." + c + "." + d + "/" + desired;
    }
}