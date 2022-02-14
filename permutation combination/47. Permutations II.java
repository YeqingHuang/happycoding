class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        // the idea is to elimiate the permutation between the same numbers
        // [1,1,2] 1b should not be selected unless 1a is selected
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        
        Arrays.sort(nums);
        backtracking(ans, nums, new ArrayList<>(), used);
        return ans;
    }
    
    private void backtracking(List<List<Integer>> ans, int[] nums, List<Integer> path, boolean[] used) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (used[i]) continue;
            else if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                continue;
            } else {
                path.add(nums[i]);
                used[i] = true;
                backtracking(ans, nums, path, used);
                
                used[i] = false;
                path.remove(path.size()-1);
            }
        }
    }
}