class Solution {
    // naive method: O(n^2)
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n <= 1) return n;
        
        // if we sort by width first, the trouble is when width are the same
        // even if height satisfies, we cannot guarantee that width satisfies
        // i.e. [6,7] cannot expand the chain ending with [6,6]
        // [2,3] [5,4] [6,6] [6,7]
        // the trick is to sort the heights by descending order
        // [2,3] [5,4] [6,7] [6,6] 
        Arrays.sort(envelopes, (a,b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i=1; i<n; i++) {
            for (int j=i-1; j>=0; j--) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLen = Math.max(dp[i], maxLen);
                }
            }
        }
        return maxLen;
    }
}

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n <= 1) return n;
        
        Arrays.sort(envelopes, (a,b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int[] heights = new int[n];
        for (int i=0; i<n; i++) {
            heights[i] = envelopes[i][1];
        }
        
        return lengthOfLIS(heights);
    }
    
    public int lengthOfLIS(int[] nums) {
        // 300. Longest Increasing Subsequence
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