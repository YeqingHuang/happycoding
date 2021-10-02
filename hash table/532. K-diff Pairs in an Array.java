class Solution {
    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        // [3,1,4,1,5], (3,1) and (3,1) should only be counted once
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        if (k != 0) {
            for (int key: map.keySet()) {
                if (map.containsKey(key - k)) {
                    ans++;
                }
            }
        } else {
            for (int key: map.keySet()) {
                if (map.containsKey(key) && map.get(key) >= 2) {
                    ans++;
                }
            }
        }
        return ans;
    }
}