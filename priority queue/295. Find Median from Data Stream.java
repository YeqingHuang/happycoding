class MedianFinder {
    // we want the two candidates get exposed
    // assume left half can have one number more than the right half
    // left half is maxHeap, right half is minHeap
    PriorityQueue<Integer> left, right;
    
    public MedianFinder() {
        left = new PriorityQueue<>((a,b) -> Integer.compare(b, a));
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if (left.isEmpty() || left.peek() >= num) {
            left.offer(num);
        } else {
            right.offer(num);
        }
        
        if (left.size() < right.size()) {
            left.offer(right.poll());
        }
        if (left.size() - 1 > right.size()) {
            right.offer(left.poll());
        }
    }
   
    public double findMedian() {
        if (left.size() == right.size()) {
            return left.peek() / 2.0 + right.peek() / 2.0;
        } else {
            return left.peek();
        }
    }
    // time: addNum takes O(logn), findMedian takes O(1)
    // space: we store all numbers in two pq, space complexity is O(n)
}