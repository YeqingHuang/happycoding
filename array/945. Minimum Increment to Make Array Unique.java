class Solution {
    // method1: TLE
    public int minIncrementForUnique(int[] nums) {
        // consider [1,2,1,2,3]
        // we need [1,2,3,4,5], greedy: 1->4 and 2->6, ans is 3+3
        Set<Integer> seen = new HashSet<>();
        int ans = 0;
        for (int num: nums) {
            if (seen.contains(num)) {
                int changedNum = findNextSmallest(seen, num);
                ans += changedNum - num;
                seen.add(changedNum);
            } else {
                seen.add(num);
            }
        }
        return ans;
    }
    
    private int findNextSmallest(Set<Integer> seen, int curr) {
        while (seen.contains(curr)) {
            curr++;
        }
        return curr;
    }

    // method2: O(nlogn)
    public int minIncrementForUnique1(int[] nums) {
        // consider [1,2,1,2,3]
        // we need [1,2,3,4,5], greedy: 1->4 and 2->6, ans is 3+3
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        // [1,1,2,2,3]
        int ans = 0;
        int pre = nums[0];
        for (int i=1; i<nums.length; i++) {
            if (nums[i] <= pre) {
                ans += (pre - nums[i] + 1);
                nums[i] = pre + 1;
            }
            pre = nums[i];
        }
        return ans;
    }
}