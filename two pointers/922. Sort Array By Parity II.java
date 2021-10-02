class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        // additional space
        int[] ans = new int[nums.length];
        int even = 0, odd = 1;
        for (int num: nums) {
            if (num % 2 == 0) {
                ans[even] = num;
                even += 2;
            } else {
                ans[odd] = num;
                odd += 2;
            }
        }
        return ans;
    }
}

class Solution1 {
    public int[] sortArrayByParityII(int[] nums) {
        // still, two pointers, O(1) space
        int odd = 1;
        for (int even = 0; even < nums.length; even += 2) {
            if (nums[even] % 2 == 1) {
                // find an odd number that is in a wrong place
                // and swap with it, so both will be correct
                while (nums[odd] % 2 == 1) {
                    odd += 2;
                }
                swap(nums, odd, even);
            }
        }
        return nums;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}