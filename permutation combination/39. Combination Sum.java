class Solution {
    int[] nums;
    Set<List<Integer>> ans;
    // naive method
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.nums = candidates;
        this.ans = new HashSet<>();
        backtrack(new ArrayList<>(), 0, target);
        return new ArrayList<>(ans);
    }
    
    private void backtrack(List<Integer> path, int currSum, int target) {
        if (currSum > target) return;
        if (currSum == target) {
            List<Integer> combination = new ArrayList<>(path);
            Collections.sort(combination);
            ans.add(combination);
        }
        for (int num: nums) {
            path.add(num);
            backtrack(path, currSum + num, target);
            path.remove(path.size()-1);
        }
    }
}

class Solution1 {
    // improvement: [2,3,7] and target = 7
    // if we don't consider duplicates, we get [2,2,3],[2,3,2],[3,2,2],[7]
    // we restrict the selection to current or following numbers
    // in this way, when path is [2,3] we won't be able to choose 2 again
    int[] nums;
    List<List<Integer>> ans;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.nums = candidates;
        this.ans = new ArrayList<>();
        backtrack(new ArrayList<>(), 0, 0, target);
        return ans;
    }
    
    private void backtrack(List<Integer> path, int start, int currSum, int target) {
        if (currSum > target) return;
        if (currSum == target) {
            ans.add(new ArrayList<>(path));
        }
        for (int i=start; i<nums.length; i++) {
            path.add(nums[i]);
            // we can only choose curr number or numbers after this number
            backtrack(path, i, currSum + nums[i], target);
            path.remove(path.size()-1);
        }
    }
}