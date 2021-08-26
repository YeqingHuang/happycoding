class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // dfs:
        // each number appear once, use a boolean array to record the used ones
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, new ArrayList<>(), used);
        return ans;
    }
    
    private void dfs(int[] nums, List<List<Integer>> ans, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (!used[i]) {
                // we can append this number to the path
                path.add(nums[i]);
                used[i] = true;
                dfs(nums, ans, path, used);
                
                // when we come out, restore
                path.remove(path.size()-1);
                used[i] = false;
            }
        }
    }
}