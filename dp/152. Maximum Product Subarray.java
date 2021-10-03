class Solution {
    public int maxProduct(int[] nums) {
        // a maxProduct comes from 3 candidates:
        // A: prevMax * num   B: prevMin * num   C: num
        //       [-2  3  -5  0  36  2]
        //   min  -2 -6 -15  0   0  0
        //            B   A  C   B  B
        //   max  -2  3  30  0  36  72
        //            C   B  C   C  A 
        if (nums.length == 1) return nums[0];
        int prevMax = nums[0], prevMin = nums[0];
        int ans = prevMax;
        for (int i=1; i<nums.length; i++) {
            int currMin = Math.min(Math.min(prevMax * nums[i], prevMin * nums[i]), nums[i]);
            int currMax = Math.max(Math.max(prevMax * nums[i], prevMin * nums[i]), nums[i]);
            ans = Math.max(ans, currMax);
            prevMin = currMin;
            prevMax = currMax;
        }
        return ans;
    }
}