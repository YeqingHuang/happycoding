class KthLargest {
    // naive method: if we call add() m times
    // time complexity is O(m * nlogn)
    private static int k;
    private List<Integer> list;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        list = new ArrayList<>();
        for (int num: nums) {
            list.add(num);
        }
    }
    
    public int add(int val) {
        list.add(val);
        Collections.sort(list);
        return findKthLargest();
    }
    
    private int findKthLargest() {
        int index = list.size() - k;
        return list.get(index);
    }
}

class KthLargest {
    // if we call it m times
    // time complexity is (nlogk + mlogk)
    private static int k;
    private PriorityQueue<Integer> minHeap;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<Integer>();
        for (int num: nums) {
            addOneNum(num);
        }
    }
    
    public int add(int val) {
        addOneNum(val);
        return minHeap.peek();
    }
    
    public void addOneNum(int num) {
        minHeap.offer(num);
        if (minHeap.size() > k) {
            minHeap.poll();
        }
    }
}