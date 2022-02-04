class Solution {
    public int findMaxLength(int[] nums) {
        // suppose we convert 0 to -1
        // then use prefix sum, as long as the prefix sum is 0, it's when -1 offset 1
        // [-1,-1,-1, 1, 1, -1, 1, 1]
        // [-1,-2,-3, -2,-1, -2, -1, 0]
        if (nums.length <= 1) return 0;
        
        for (int i=0; i<nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        
        int sum = 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i=0; i<nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                maxLen = Math.max(maxLen, i - map.get(sum));
            } else {
                map.put(sum, i); // only put the leftmost index
            }
        }
        return maxLen;
    }
}

// P.S. we don't need to modify the original array
// we can just do sum++ or sum--