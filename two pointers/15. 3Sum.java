class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // [-4,-1,-1,0,1,2]
        // we should only get one [-1,0,1], 
        // the second -1 should not be considered unless the first -1 is there
        if (nums.length <= 2) return new ArrayList<>();
        
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i=0; i<nums.length-2; i++) {
            // one optimization
            if (nums[i] > 0) break;
            
            // don't choose it as the first num if it has been chosen as the first num
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }
            
            // normal case
            int low = i+1;
            int high = nums.length - 1;
            while (low < high) {
                if (nums[low] + nums[high] < -nums[i]) {
                    low++;
                } else if (nums[low] + nums[high] == -nums[i]) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet = Arrays.asList(nums[i], nums[low], nums[high]);
                    ans.add(triplet);
                    // we should no simply do low++, high--, there may be duplicates
                    while (low+1 < high && nums[low+1] == nums[low]) {
                        low++;
                    }
                    while (high-1 > low && nums[high-1] == nums[high]) {
                        high--;
                    }
                    low++;
                    high--;
                } else {
                    high--;
                }
            }
        }
        return ans;
    }
}