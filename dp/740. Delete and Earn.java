class Solution {
    public int deleteAndEarn(int[] nums) {
        // nums = [2,2,3,3,3,4]
        // if you choose to take 3, you can take it all, 
        // but you lose the opportunity to take its adjacent numbers 2 and 4
        // therefore, this is another version of house robber
        // suppose nums = [3，3，3，6，6，7]
        // convert it to [9,0,12,7] a single 0 means 3 and 6 are not adjacent
        if (nums == null || nums.length == 0) return 0;
        
        Arrays.sort(nums);
        List<Integer> candidates = new ArrayList<>();
        int currNum = nums[0];
        int currTotal = currNum;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == currNum) {
                currTotal += currNum;
            } else {
                candidates.add(currTotal);
                if (nums[i] - currNum > 1) {
                    candidates.add(0);
                }
                currNum = nums[i];
                currTotal = currNum;
            }
        }
        candidates.add(currTotal);
        
        int[] dp = new int[candidates.size()+1];
        dp[1] = candidates.get(0);
        for (int i=2; i<=candidates.size(); i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + candidates.get(i-1));
        }
        return dp[candidates.size()];
    }

    // a cleaner version: 1 <= nums[i] <= 10^4
    public int deleteAndEarn1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] sum = new int[10001];
        for(int num: nums) {
            sum[num] += num;
        }
        
        int takePrev = 0, skipPrev = 0;
        for (int i=1; i<=10000; i++) {
            int takeCurr = skipPrev + sum[i];
            int skipCurr = Math.max(skipPrev, takePrev);
            // update variables
            takePrev = takeCurr;
            skipPrev = skipCurr;
        }
        return Math.max(takePrev, skipPrev);
    }
}