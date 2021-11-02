class Solution {
    public int[] sortedSquares(int[] nums) {
        // thoughts: it's already sorted, we want to make use of it
        // instead of using O(nlogn) to sort
        // the candidates are from both sides, we stop until two pointers meet
        int i = 0;
        int j = nums.length - 1;
        int[] ans = new int[nums.length];
        int k = nums.length - 1;
        while (i<=j) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[k--] = nums[i] * nums[i];
                i++;
            } else {
                ans[k--] = nums[j] * nums[j];
                j--;
            }
        }
        return ans;
    }
}