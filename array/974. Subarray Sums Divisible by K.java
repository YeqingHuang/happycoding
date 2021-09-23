class Solution {
    // O(n^2) TLE
    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] prefix = new int[nums.length+1];
        prefix[1] = nums[0];
        for (int i=2; i<=nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        
        int count = 0;
        for (int i=1; i<=nums.length; i++) {
            for (int j=0; j<i; j++) {
                if ((prefix[i] - prefix[j]) % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraysDivByK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // how to avoid using (prefix[j] - prefix[i]) % k == 0?
        // if prefix[j] % k = m, prefix[i] % k = m, then the above condition is satisfied
        // suppose there are a total of x i that satisfy prefix[i] % k = m
        // when j comes, ans += x
        int ans = 0;
        int prefix = 0;
        Map<Integer, Integer> map = new HashMap<>(); 
        map.put(0, 1); // remainder, count
        
        for (int num: nums) {
            prefix = (prefix + num) % k;
            if (prefix < 0) prefix += k;
            if (map.containsKey(prefix)) {
                ans += map.get(prefix);
                map.put(prefix, map.get(prefix) + 1);
            } else {
                map.put(prefix, 1);
            }
        }
        return ans;
    }
}