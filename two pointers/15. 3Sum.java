class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // sort the array
        // fix the first number of the triplet
        // then use left pointer and right pointer to get the answer
        // 1. dedup when fixing the first pointer
        // 2. dedup when moving left pointer and right pointer
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) return ans;
        
        Arrays.sort(nums);
        for (int i=0; i<nums.length-2; i++) {
            if (i > 0 && nums[i-1] == nums[i]) continue;
            
            int target = -nums[i];
            int left = i+1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left-1 > i && nums[left] == nums[left-1]) {
                        left++;
                    }
                    while (right-1 > left && nums[right] == nums[right-1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return ans;
    }
}