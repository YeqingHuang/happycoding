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

    public int firstMissingPositive(int[] nums) {
        // O(1) space: 565. Array Nesting
        // ideally, this array should be [1,2,3,4,5]...
        // in the first pass, we try to allocate num to its "correct" index
        // in the second pass, once we find nums[i] != i + 1, the answer is i+1
        int n = nums.length;
        
        for (int i=0; i<n; i++) {
            if (nums[i] >= 1 && nums[i] <= n && nums[i] != i + 1) {
                // try to start a loop from here
                int currNum = nums[i];
                while (currNum >= 1 && currNum <= n && nums[currNum-1] != currNum) {
                    int index = currNum - 1;
                    int temp = nums[index];
                    nums[index] = currNum;
                    currNum = temp;
                }
            }
        }
        
        for (int k=0; k<n; k++) {
            if (nums[k] != k + 1) {
                return k + 1;
            }
        }
        return n + 1;
    }
}