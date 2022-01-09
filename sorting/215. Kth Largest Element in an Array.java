class Solution {
    // priority queue: O(nlogk)
    public int findKthLargest(int[] nums, int k) {
        // k = 3, nums is [2,5,1,8,10,1]
        // first comes three numbers [1,2,5] 
        // comes 8: pop 1 and push 8 [2,5,8]
        // comes 10: pop 2 and push 10 [5,8,10]
        // comes 1: 1 is smaller than top, do nothing
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for(int num:nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

}

class Solution1 {
    public int findKthLargest(int[] nums, int k) {
        // quick select: target index is nums.length - k
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
    
    private int quickSelect(int[] nums, int left, int right, int target) {
        int pivotIndex = findIndex(nums, left, right);
        
        if (pivotIndex == target) {
            return nums[pivotIndex];
        } else if (pivotIndex < target) {
            return quickSelect(nums, pivotIndex + 1, right, target);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, target);
        }
    }
    
    private int findIndex(int[] nums, int left, int right) {
        // use the leftmost num as pivot
        int pivot = nums[left];
        int i = left + 1;
        for (int j=left+1; j<=right; j++) {
            if (nums[j] <= pivot) {
                // this num should be placed in front
                swap(nums, i, j);
                i++;
                // have not checked the swapped num yet, don't do j--
            }
        }
        // now i-1 is the place that pivot belongs to
        swap(nums, left, i-1);
        return i-1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}