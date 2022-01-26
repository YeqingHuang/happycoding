class Solution {
    // O(n^2) TLE
    public int maxFrequency(int[] nums, int k) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        Arrays.sort(nums);
        int n = nums.length;
        int[] cost = new int[n];
        for (int i=0; i<n-1; i++) {
            // how many operations it takes to make nums[i] equal to nums[i+1]
            cost[i] = nums[i+1] - nums[i];
        }

        // then we can try each num has the highest num
        int ans = 0;
        for (int i=1; i<n; i++) {
            ans = Math.max(ans, findIncluded(i, cost, k));
        }
        return ans;
    }
    
    private int findIncluded(int index, int[] cost, int k) {
        int included = 1;
        int stepCost = 0;
        for (int i=index-1; i>=0; i--) {
            stepCost += cost[i];
            k -= stepCost;
            if (k >= 0) {
                included++;
            } else {
                break;
            }
        }
        return included;
    }
}

class Solution1 {
    // O(nlogn): prefix sum and sliding window
    public int maxFrequency(int[] nums, int k) {
        // suppose we fix the target num, i.e. nums[j]
        // how do we know how long it can expand to the left?
        // suppose the left most index is i, it must be true that
        // nums[j] * (j-i+1) - sum of subarray[i,j] <= k 
        // if we want to find the sum of subarray quickly -> use prefix sum
        
        // if we want to achieve O(n) -> use sliding window
        // keep moving j, when the condition is no longer satisfied, shrink the subarray and move i
        int ans = 1;
        int i = 0, j = 0;
        long sum = 0; // store prefix sum
        
        Arrays.sort(nums);
        while (j < nums.length) {
            sum += nums[j];
            while ((long) nums[j] * (j-i+1) - sum > k) {
                sum -= nums[i];
                i++;
            }
            ans = Math.max(ans, j-i+1);
            j++;
        }
        return ans;
    }
}