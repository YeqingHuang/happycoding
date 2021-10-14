class Solution {
    PriorityQueue<Integer> leftPart = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
    PriorityQueue<Integer> rightPart = new PriorityQueue<>();
    
    public double[] medianSlidingWindow(int[] nums, int k) {
        // we want to get the candidate(s) exposed when we move the window
        // we store all k numbers in two priority queue
        // suppose leftpart can have an addtional number compared with the right half
        // we need three helper functions: addNum, removeNum, getMedian
        double[] ans = new double[nums.length - k + 1];
        for (int i=0; i<k; i++) {
            addNum(nums[i]);
        }
        ans[0] = getMedian(k);
        for (int i=k; i<nums.length; i++) {
            removeNum(nums[i-k]);
            addNum(nums[i]);
            ans[i-k+1] = getMedian(k);
        }
        return ans;
    }
    
    private void addNum(int num) {
        if (leftPart.isEmpty() || num <= leftPart.peek()) {
            leftPart.offer(num);
        } else {
            rightPart.offer(num);
        }
        balance();
    }
    
    private void removeNum(int num) {
        if (num <= leftPart.peek()) {
            leftPart.remove(num);
        } else {
            rightPart.remove(num);
        }
        balance();
    }
    
    private void balance() {
        if (rightPart.size() > leftPart.size()) {
            leftPart.offer(rightPart.poll());
        } else if (leftPart.size() - 1 > rightPart.size()) {
            rightPart.offer(leftPart.poll());
        }
    }
    
    private double getMedian(int k) {
        if (k % 2 == 0) {
            return leftPart.peek() / 2.0 + rightPart.peek() / 2.0;
        } else {
            return leftPart.peek();
        }
    }
}