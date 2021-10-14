class NumArray {
    int[] prefix;
    public NumArray(int[] nums) {
        prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i=1; i<nums.length; i++) {
            prefix[i] = prefix[i-1] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        if (left == 0) {
            return prefix[right];
        } else {
            // [2,1,5,4], prefix = [2,3,8,12]
            // suppose left = 1, right = 3
            // 1+5+4 = 10, which is prefix[right] - prefix[left-1]
            return prefix[right] - prefix[left-1];
        }
    }
}