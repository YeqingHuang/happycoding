class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
         boolean[] used = new boolean[nums.length];
        return backtrack(nums, k, target, 0, 0, used);
    }
    
    // naive backtracking, TLE
    private boolean backtrack(int[] nums, int k, int target, int curr, int count, boolean[] used) {
        int n = nums.length;
        if (count == k-1) return true;
        if (curr > target) return false;
        if (curr == target) {
            return backtrack(nums, k, target, 0, count+1, used);
        }
        
        for (int i=0; i<n; i++) {
            if (!used[i]) {
                used[i] = true;
                if (backtrack(nums, k, target, curr + nums[i], count, used)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }
}

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num: nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        char[] used = new char[nums.length];
        Arrays.fill(used, '0');
        HashMap<String, Boolean> memo = new HashMap<>();
        return backtrack(nums, k, target, 0, 0, used, memo);
    }
    
    // suppose we know that if we pair a and b, c and d, the rest numbers cannot form an answer
    // then at some point, we pair a and c, b and d, we should not try again
    // memoization: using taken element's string as key, value is true/false.
    private boolean backtrack(int[] nums, int k, int target, int curr, int count, 
                              char[] used, HashMap<String, Boolean> memo) {
        int n = nums.length;
        if (count == k - 1) return true;
        if (curr > target) return false;
        
        String taken = new String(used);
        if (memo.containsKey(taken)) {
            return memo.get(taken);
        }
        
        if (curr == target) {
            boolean result = backtrack(nums, k, target, 0, count+1, used, memo);
            memo.put(taken, result);
            return result;
        }
        
        for (int j = 0; j<n; j++) {
            if (used[j] == '0') {
                used[j] = '1';
                if (backtrack(nums, k, target, curr + nums[j], count, used, memo)) {
                    return true;
                }
                used[j] = '0';
            }
        }
        memo.put(taken, false);
        return false;
    }
}