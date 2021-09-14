class MedianFinder {
    // thoughts: two candidates of median: even size (average), odd size(choose one)
    // how to get the two candidates exposed? i.e. get them in less than O(n) time
    // maintain a maxHeap for the left subarray, a minHeap for the right subarray
    // minHeap's size = maxHeap's size OR maxHeap's size - minHeap's size = 1
    
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
    }
    
    public void addNum(int num) {
        // two steps:
        // 1) add to one of the heaps according to its value
        // 2) balance sizes if needed
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        
        if (minHeap.size() > maxHeap.size()) {
            // right part has one more number
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() - 1 > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
        
    }
    
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek())/2.0;
        } else {
            return maxHeap.peek();
        }
    }
    // time: addNum takes O(logn), findMedian takes O(1)
    // space: we store all numbers in two pq, space complexity is O(n)
}
