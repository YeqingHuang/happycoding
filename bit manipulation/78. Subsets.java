class Solution {
    // method1: easy to understand
    public List<List<Integer>> subsets(int[] nums) {
        // double the size of the answers when we include a new number
        // [1,2,3] begins with [[]]
        // add 1, we have [[],[1]]
        // add 2, we have [[],[1], [2], [1,2]]
        // add 3, we have [[],[1], [2], [1,2], [3],[1,3],[2,3],[1,2,3]]
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        ans.add(new ArrayList<>()); // base case
        for (int num: nums) {
            List<List<Integer>> oldAns = new ArrayList<>(ans);
            for (List<Integer> list: oldAns) {
                List<Integer> copiedList = new ArrayList<>(list);
                copiedList.add(num);
                ans.add(copiedList);
            }
        }
        return ans;
    }

    // method2: bit manipulation
        public List<List<Integer>> subsets(int[] nums) {
        // [1,2,3] has a length of 3, so there are 2^3 combinations
        // we need to generate 000, 001, 010, 011,... till 111 as the mask
        
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        for (int mask = 0; mask < 1 << n; mask++) {
            List<Integer> subset = new ArrayList<>();
            // we append a number when the mask has 1 for this digit
            int digit = 1;
            for (int i=0; i<n; i++) {
                if ((mask & digit) != 0) {
                    subset.add(nums[i]);
                }
                digit <<= 1;
            }
            ans.add(subset);
        }
        return ans;
    }
}