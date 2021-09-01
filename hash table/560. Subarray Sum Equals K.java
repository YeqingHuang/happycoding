class Solution {
    public int subarraySum(int[] nums, int k) {
        // prefix sum
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>(); // prefix sum, count
        map.put(0,1);
        int prefixSum = 0;
        for (int num: nums) {
            prefixSum += num;
            int desired = prefixSum - k;
            if (map.containsKey(desired)) {
                ans += map.get(desired);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return ans;
    }
}