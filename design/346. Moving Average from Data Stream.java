class MovingAverage {
    private int size;
    private Deque<Integer> deque;
    private int sum;
    
    public MovingAverage(int size) {
        this.deque = new ArrayDeque<>();
        this.size = size;
        this.sum = sum;
    }
    
    public double next(int val) {
        addNum(val);
        return getAverage();
    }
    
    private void addNum(int val) {
        if (deque.size() >= size) {
            // remove the oldest num
            int removed = deque.pollFirst();
            sum -= removed;
        }
        deque.offerLast(val);
        sum += val;
    }
    
    private double getAverage() {
        return sum / (deque.size() * 1.0);
    }
}