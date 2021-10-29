class Solution {
    List<List<Integer>> ans;
    int[] nums;
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // we want to eliminate the permutation between numbers of the same value
        // let's say in example1, we have 1a and 1b, we don't consider including 1b unless 1a is chosen
        ans = new ArrayList<>();
        if (candidates.length == 0) return ans;
        
        Arrays.sort(candidates);
        nums = candidates;
        dfs(0, target, 0, new ArrayList<>());
        return ans;
    }
    
    private void dfs(int currSum, int target, int index, List<Integer> path) {
        if (currSum > target) return;
        if (currSum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int i=index; i<nums.length; i++) {
            if (i > index && nums[i] == nums[i-1]) 
                continue;
            path.add(nums[i]);
            dfs(currSum + nums[i], target, i+1, path);
            path.remove(path.size()-1);
        }
    }
}