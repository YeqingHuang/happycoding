class Solution {
    // a method based on 3Sum, time complexity O(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length <= 3) return ans;
        if (nums[0] * 4 > target || nums[nums.length-1] * 4 < target) return ans;
        
        // if we use two pointers for the first and the second num
        // the subproblem is then a twoSum
        for (int i=0; i<nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            for (int j=i+1; j<nums.length - 2; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }
                int desired = target - nums[i] - nums[j];
                List<List<Integer>> twoSums = findTwoSum(nums, j+1, desired);
                for (List<Integer> twoSum: twoSums) {
                    ans.add(Arrays.asList(nums[i], nums[j], twoSum.get(0), twoSum.get(1)));
                }   
            }
        }
        return ans;
    }
    
    private List<List<Integer>> findTwoSum(int[] nums, int start, int target) {
        // consider the subArray [start, nums.length-1]
        List<List<Integer>> ans = new ArrayList<>();
        int i = start, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                ans.add(Arrays.asList(nums[i], nums[j]));
                while (i < j && nums[i+1] == nums[i]) {
                    i++;
                }
                while (i < j && nums[j-1] == nums[j]) {
                    j--;
                }
                i++;
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }
}