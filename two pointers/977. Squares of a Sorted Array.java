class Solution {
    // time: O(nlogn)
    public int[] sortedSquares(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);
        return nums;
    }
}

class Solution {
    // time: O(n)
    public int[] sortedSquares(int[] nums) {
        // find where negative meets positive, then expand to both side
        int left = -1;
        int right = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] * nums[i+1] <= 0) {
                left = i;
                right = i + 1;
                break;
            }
        }
        
        if (left == -1) {
            // we don't find such changing point
            // either the arr has all negs, or all pos
            if (nums[0] < 0) {
                reverseArray(nums);
            }
            for (int j = 0; j<nums.length; j++) {
                nums[j] *= nums[j];
            }
            return nums;
        }
        
        int[] ans = new int[nums.length];
        int k = 0;
        while (left >= 0 && right < nums.length) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[k++] = nums[right] * nums[right];
                right++;
            } else {
                ans[k++] = nums[left] * nums[left];
                left--;
            }
        }
        while (left >= 0) {
            ans[k++] = nums[left] * nums[left];
            left--;
        }
        while (right < nums.length) {
            ans[k++] = nums[right] * nums[right];
            right++;
        }
        return ans;
    }
    
    private void reverseArray(int[] arr) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}

class Solution {
    // time O(n)
    public int[] sortedSquares(int[] nums) {
        // there's no need to shrink and meet, then expand again
        // we can just start from both side, and fill the answers from right to left
        int n = nums.length;
        int[] ans = new int[n];
        int k = n - 1;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                ans[k--] = nums[left] * nums[left];
                left++;
            } else {
                ans[k--] = nums[right] * nums[right];
                right--;
            }
        }
        return ans;
    }
}