class Solution {
    // NOTE: Math.power(2, n) can easily get overflow
    // to solve this problem, precompute and store it in an array
    public int numSubseq(int[] nums, int target) {
        // nums = [3,5,6,7], target = 9
        // first sort, then use two pointers
        // nums[i] serves as the minNum, find the biggest possible maxNum(binary search)
        int MOD = (int)1e9 + 7;
        int[] powers = new int[nums.length];
        powers[0] = 1;
        for (int i=1; i<nums.length; i++) {
            powers[i] = powers[i-1] * 2 % MOD;
        }
        
        Arrays.sort(nums);
        int count = 0;
        for (int i=0; i<nums.length; i++) {
            if (nums[i] > target - nums[i]) break;
            
            int j = findBiggerOrSmaller(nums, i, target - nums[i]);
            if (j == -1) break;
            // we must select nums[i]
            // then select subsequence from a length of j - i
            count = (count + powers[j - i]) % MOD;
        }
        return count;
    }

    private int findBiggerOrSmaller(int[] nums, int start, int target) {
        // find rightmost index where nums[index] <= target
        int low = start;
        int high = nums.length - 1;
        while (low < high - 1) {
            int mid = low + (high - low) / 2;
            if (nums[mid] <= target) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (nums[high] <= target) return high;
        else if (nums[low] <= target) return low;
        return -1;
    }
}

class Solution1 {
    // no need to use binary search, just move right pointer by j--
    public int numSubseq(int[] nums, int target) {
        int MOD = (int)1e9 + 7;
        int[] powers = new int[nums.length];
        powers[0] = 1;
        for (int i=1; i<nums.length; i++) {
            powers[i] = powers[i-1] * 2 % MOD;
        }
        
        Arrays.sort(nums);
        int ans = 0;
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            if (nums[l] + nums[r] > target) {
                r--;
            } else {
                ans = (ans + powers[r - l]) % MOD;
                l++;
            }
        }
        return ans;
    }
}
