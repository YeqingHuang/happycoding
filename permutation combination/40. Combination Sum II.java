class Solution {
    int[] nums;
    List<List<Integer>> ans;
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.nums = candidates;
        this.ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, target);
        return ans;
    }
    
    private void backtrack(List<Integer> path, int start, int remaining) {
        // [10,1,2,7,6,1,5] target is 8
        // after sorting, it becomes [1,1,2,5,6,7,10]
        // previously, the problem is that we have [1,2,5] and [2,1,5]
        // now when we choose 1,
        if (remaining < 0) return;
        if (remaining == 0) {
            ans.add(new ArrayList<>(path));
        }
        for (int i=start; i<nums.length; i++) {
            if (nums[i] > remaining) return;
            
            // we only choose one number in each round
            // this line means that suppose we have 2a and 2b, if 2a is not selected
            // we are not allowed to select 2b
            // since we reach here, it means we have not chosen nums[i-1], therefore
            // we should not choose nums[i]
            if (i > start && nums[i] == nums[i-1]) continue;
            
            path.add(nums[i]);
            backtrack(path, i+1, remaining - nums[i]);
            path.remove(path.size()-1);
        }
    }  
}