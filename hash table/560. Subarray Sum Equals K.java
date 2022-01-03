class Solution {
    public int subarraySum(int[] nums, int k) {
        // continuous subarrays whose sum equals to k
        // naive: O(n^2), TLE
        // prefix sum: O(n)
        Map<Integer, Integer> map = new HashMap<>(); // prefixSum, count
        map.put(0,1);
        
        int sum = 0, ans = 0;
        for (int num: nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return ans;
    }
}