class Solution {
    public int arrayNesting(int[] nums) {
        // [0,2,1]
        // there are two loops in this array
        // if you start with index = 0, the loop only contains 0
        // if you start with index = 1, the loop is longer, containing 2 and 1
        int maxLen = 0;
        boolean[] visited = new boolean[nums.length];
        for (int i=0; i<nums.length; i++) {
            if (!visited[i]) {
                // try to use i as the first index
                int index = i;
                int len = 0;
                while (!visited[index]) {
                    visited[index] = true;
                    len++;
                    index = nums[index];
                }
                maxLen = Math.max(len, maxLen);
            }  
        }
        return maxLen;
    }
}