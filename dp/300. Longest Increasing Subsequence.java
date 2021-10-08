class Solution {
    // dp: O(n^2)
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        
        for (int i=1; i<nums.length; i++) {
            for (int j = i-1; j>= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // if the last element is very small, dp[n-1] may not be the answer
        // we must use another pass to find max
        int maxLen = 1;
        for (int i=0; i<nums.length; i++) {
            maxLen = Math.max(dp[i], maxLen);
        }
        return maxLen;
    }
}

class Solution {
    // hashmap + binary search: O(nlogn)
    public int lengthOfLIS(int[] nums) {
        // nums = [10,9,2,5,3,7,101,18]
        // return 4 because [2,3,7,101]
        // naive dp takes O(n^2), but we can improve to (nlogn)
        
        // it's like a hashmap:
        // key is index, i,e. the length of a subsequence - 1
        // value is list.get(index), which represents the last num for this subsequence
        // we update value if it's better, i.e. we find a smaller last num
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > list.get(list.size() - 1)) {
                list.add(num);
            } else {
                int index = binarySearch(list, num);
                list.set(index, num);
            }
        }
        return list.size();
    }
    
    private int binarySearch(List<Integer> list, int target) {
        int low = 0;
        int high = list.size() - 1;
        while (low < high) {
            int mid = (low + high)/2;
            if (list.get(mid) == target) {
                return mid;
            } else if (list.get(mid) < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}