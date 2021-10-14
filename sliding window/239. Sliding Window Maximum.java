class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // we want to find the max in a window of size k in O(1), not O(k)
        // suppose the maxNum get removed(first num in the window), how do we know what's the second maxNum
        // maintain a decreasing list(deque)
        // [-3, 5, 3, 1, 7] and k = 2
        // first we have 5, when 3 comes, save it in case it will be the next maxNum, i.e.[5,3]
        // then we remove 3 and get 1, it becomes [3,1]
        // now 7 comes, it becomes [7]
        int n = nums.length;
        int[] ans = new int[n-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i=0; i<k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
        }
        ans[0] = deque.peekFirst();
        
        for (int i=k; i<n; i++) {
            if (nums[i-k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                 deque.pollLast();
            }   
            deque.offerLast(nums[i]);
            ans[i-k+1] = deque.peekFirst();
        }
        return ans;
    }
}