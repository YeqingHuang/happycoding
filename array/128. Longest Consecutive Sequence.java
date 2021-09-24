class Solution {
    // O(nlogn): sorting
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        
        Arrays.sort(nums);
        // consider [1,1,2,3,3,3]
        int maxLen = 1;
        int prevNum = nums[0];
        int len = 1;
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == prevNum) {
                continue;
            } else if (nums[i] == prevNum + 1) {
                len++;
                prevNum++;
            } else {
                prevNum = nums[i];
                len = 1;
            } 
            maxLen = Math.max(len, maxLen);
        }
        return maxLen;
    }

    // O(n): union find
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        // when we check a new number, either it increment the length by 1
        // or we use it as a start in a subsequence
        // for case1, we don't know the existing length for number - 1
        // for case2, we can keep finding number + 1 until it can no longer expand
        // in this way, the num that we choose to check is always the smallest num in a part
        // [10,7,6,5,4,8], for 7, we do nothing because 6 is there
        // similarly, only when we reach 4, we begin to expand
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            set.add(num);
        }
    
        int maxLen = 1;
        for (int num: set) {
            if (!set.contains(num-1)) {
                int len = 1;
                while (set.contains(num+1)) {
                    len++;
                    num = num + 1;
                }
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
}