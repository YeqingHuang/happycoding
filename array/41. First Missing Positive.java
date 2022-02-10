class Solution {
    public int firstMissingPositive(int[] nums) {
        // with additional space
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        for (int i=1; i<=nums.length; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return nums.length+1;
    }
}

class Solution1 {
    public int firstMissingPositive(int[] nums) {
        // make use of the index
        // the idea is to place every number at the correct place
        // in the second pass, once nums[k] != k+1, we find the answer
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == i+1) {
                // no index to place this number
                // OR it's already at the right place
                i++;
            } else {
                // the right index for nums[i] is nums[i] - 1
                if (nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                } else {
                    // nums[i] - 1 already has the correct number
                    i++;
                }
            }
        }
        
        for (int k=0; k<nums.length; k++) {
            if (nums[k] != k+1) {
                return k+1;
            }
        }
        return nums.length+1;
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}