class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        // 41. First Missing Positive
        // make use of the relationship between index and value
        // we only change a num to negative the first time we process it
        // in the second pass, if nums[i-1] > 0, then i is missing
        for (int num: nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        for (int i=1; i<=nums.length; i++) {
            if (nums[i-1] > 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}