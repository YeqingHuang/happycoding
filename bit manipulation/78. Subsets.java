class Solution {
    // method1: easy to understand
    public List<List<Integer>> subsets(int[] nums) {
        // double the size of the answers when we add a new number
        // [1,2,3] begins with [[]]
        // add 1, we have [[],[1]]
        // add 2, we have [[],[1], [2], [1,2]]
        // add 3, we have [[],[1], [2], [1,2], [3],[1,3],[2,3],[1,2,3]]
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        ans.add(new ArrayList<>());
        for (int num: nums) {
            List<List<Integer>> lists = new ArrayList<>(ans);
            for (List<Integer> oldList: lists) {
                List<Integer> copied = new ArrayList<>(oldList);
                copied.add(num);
                ans.add(copied);
            }
        }
        return ans;
    }

    // method2: bitmask
    public List<List<Integer>> subsets1(int[] nums) {
        // consider [1,2,3]
        // if we can generate 000, 001, 010, 011, 100, 101, 110, 111
        // we will know if we should add nums[i] or not
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        int n = nums.length;
        for (int num=0; num<Math.pow(2, n); num++) {
            // check one digit at a time, use a mask, 0, 10, 100, 1000...
            List<Integer> subset = new ArrayList<>();
            int mask = 1;
            for (int i=0; i<n; i++) {
                if ((mask & num) != 0) {
                    // digit i is 1 in num
                    subset.add(nums[i]);
                }
                mask <<= 1;
            }
            ans.add(subset);
        }
        return ans;
    }
}