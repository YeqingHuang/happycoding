class Solution {
    // O(n^2) TLE
    public int subarraysWithKDistinct(int[] nums, int k) {
        // iterate the beginning index of a subarray
        // if the unique count <= k, we expand j, once count > k, we break
        int ans = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i=0; i<nums.length; i++) {
            seen.clear();
            for (int j=i; j<nums.length; j++) {
                seen.add(nums[j]);
                if (seen.size() == k) {
                    ans++;
                } else if (seen.size() > k) {
                    break;
                }
            }
        }
        return ans;
    }
}

class Solution {
    // O(n): sliding window + hashmap
    public int subarraysWithKDistinct(int[] nums, int k) {
        // if we want to achieve O(n), we must use sliding window
        // a typical sliding window is that, if count <= k, we can expand j
        // as soon as count > k, we shrink from left, i.e. i++
        return atMost(nums, k) - atMost(nums, k-1);
    }
    
    private int atMost(int[] nums, int k) {
        // return the number of subarrays with at most k unique numbers
        int ans = 0;
        int i = 0, j = 0;
        Map<Integer, Integer> map = new HashMap<>(); // frequency map
        int unique = 0;
        while (j < nums.length) {
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
            if (map.get(nums[j]) == 1) {
                unique++; // this is the first time we see nums[j]
            }
            while (unique > k) {
                // find the left boundary
                map.put(nums[i], map.get(nums[i]) - 1);
                if (map.get(nums[i]) == 0) {
                    unique--;
                }
                i++;
            }
            // now unique <= k, record answer
            ans += j - i + 1;
            j++;
        }
        return ans;
    }
}