class Solution {
    // O(n^2), TLE
    public int maxSubArrayLen(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i=1; i<nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }
        
        int maxLen = 0;
        for (int i=0; i<nums.length; i++) {
            if (prefix[i] == k) {
                maxLen = Math.max(maxLen, i+1);
            }
            for (int j=0; j<i; j++) {
                if (prefix[i] - prefix[j] == k) {
                    maxLen = Math.max(maxLen, i - j);
                }
            }
        }
        return maxLen;
    }

    // O(n), use a hashmap
    public int maxSubArrayLen(int[] nums, int k) {
        // in the previous solution
        // case1 is OK: if prefix[i] == k, len = i+1
        // but case2 can be improved: 
        // if prefix[i] - prefix[j] == k, how to improve from O(n) to O(1) ? 
        // use a hashmap, our target is prefix[i] - k,
        // if this key exists, how do we know j to get len = i - j?
        // store j as the value
        int prefix = 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            prefix += nums[i];
            if (prefix == k) {
                maxLen = i + 1;
            }
            if (map.containsKey(prefix - k)) {
                maxLen = Math.max(maxLen, i - map.get(prefix-k));
            }
            // if this prefix sum has not been in the map, store it
            // otherwise, don't overwrite, we don't want j to become bigger
            if (!map.containsKey(prefix)) {
                map.put(prefix, i);
            }
        }
        return maxLen;
    }
}