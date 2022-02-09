class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums.length <= 1) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        if (k == 0) {
            for (int key: map.keySet()) {
                if (map.get(key) >= 2) ans++;
            }
        } else {
            // the freq does not matter, we only want to know if it exists
            // [3,1,4,3,6], k = 2
            // when we count (3,1) and meet 3 later, we should not count (1,3)
            // a good way to do this is define a<b in a pair (a,b)
            for (int key: map.keySet()) {
                if (map.containsKey(key - k)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}