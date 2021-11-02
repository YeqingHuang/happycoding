class Solution {
    public int missingNumber(int[] nums) {
        // method1: sorting
        Arrays.sort(nums);
        
        if (nums[0] != 0) return 0;
        for (int i=1; i<nums.length; i++) {
            int expected = nums[i-1] + 1;
            if (nums[i] != expected) return expected;
        }
        return nums.length; 
    }

    public int missingNumber2(int[] nums) {
        // method2: hashSet
        Set<Integer> seen = new HashSet<>();
        for (int num: nums) {
            seen.add(num);
        }
        
        for (int i=0; i<=nums.length; i++) {
            if (!seen.contains(i)) {
                return i;
            }
        }
        return -1;
    }

    public int missingNumber3(int[] nums) {
        // method3: Gauss' Formula
        int n = nums.length;
        int expected = n * (n+1) / 2;
        int actual = 0;
        for (int num: nums) {
            actual += num;
        }
        return expected - actual;
    }
}