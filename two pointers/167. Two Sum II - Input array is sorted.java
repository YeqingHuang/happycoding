class Solution {
    public int[] twoSum(int[] numbers, int target) {
        // if sum is too big, right--
        // if sum is too small, left++
        int left = 0;
        int right = numbers.length - 1;
        while (left < right - 1) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                break;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[]{left+1, right+1};
    }
}