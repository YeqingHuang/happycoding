class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // the diff is Math.min(target, a+b+c)
        Arrays.sort(nums);
        
        int[] ans = {Integer.MAX_VALUE, Integer.MAX_VALUE}; // minDiff, sum
        for (int i=0; i<nums.length-2; i++) {
            if (i>0 && nums[i] == nums[i-1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum - target);
                if (diff < ans[0]) {
                    ans[0] = diff;
                    ans[1] = sum;
                }
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return target;
                }
            }
        }
        return ans[1];
    }
}