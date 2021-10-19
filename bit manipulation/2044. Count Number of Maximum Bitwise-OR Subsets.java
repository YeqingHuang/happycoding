class Solution1 {
    // brute force, generate all the subsets and check bitwise OR
    public int countMaxOrSubsets(int[] nums) {
        int currMax = 0;
        int currCount = 0;
        List<List<Integer>> subsets = getSubsets(nums);
        for(List<Integer> subset: subsets) {
            int ans = 0;
            for (int num: subset) {
                ans = ans | num; // ^ is exclusice OR
            }
            if (ans > currMax) {
                currMax = ans;
                currCount = 1;
            } else if (ans == currMax) {
                currCount++;
            }
        }
        return currCount;
    }
    
    private List<List<Integer>> getSubsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // double the size of the answers when we add a new number
        // [1,2,3] begins with [[]]
        // add 1, we have [[],[1]]
        // add 2, we have [[],[1], [2], [1,2]]
        // add 3, we have [[],[1], [2], [1,2], [3],[1,3],[2,3],[1,2,3]]
        ans.add(new ArrayList<>());
        
        // every time we just add one number
        for (int num: nums) {
            List<List<Integer>> existing = new ArrayList<>(ans);
            for (List<Integer> list: existing) {
                List<Integer> copied = new ArrayList<>(list);
                copied.add(num);
                ans.add(copied);
            }
        }
        return ans;
    }
}

class Solution2 {
    // dfs
    private int count;
    public int countMaxOrSubsets(int[] nums) {
        count = 0;
        int maxOr = 0;
        for (int i=0; i<nums.length; i++) {
            // consider two nums in binary: 001 and 100
            // max bitwiseOr is 101
            maxOr = maxOr | nums[i];
        }
        dfs(nums, 0, 0, maxOr);
        return count;
    }
    
    private void dfs(int[] nums, int i, int currOr, int maxOr) {
        if (i == nums.length) {
            // we've done calculating currOr
            if (currOr == maxOr) count++;
            return;
        }
        
        dfs(nums, i+1, currOr | nums[i], maxOr); // include it
        dfs(nums, i+1, currOr, maxOr); // skip it
    }
}

class Solution3 {
    // dp O(mn), m is max number in nums
    public int countMaxOrSubsets(int[] nums) {        
        // dp[i] means there are a number of dp[i] subsets with bitwiseOr = i
        int[] dp = new int[1 << 17];
        dp[0] = 1;
        int maxOr = 0;

        for (int num: nums) {
            // why we must traverse in reverse order?
            for (int i=maxOr; i>=0; i--) {
                if (dp[i] != 0) {
                    dp[i | num] += dp[i];
                }                
            }
            maxOr = maxOr | num;
        }
        return dp[maxOr];
    }
}
