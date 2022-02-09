class StockPrice {
    
    int latestTime;
    Map<Integer, Integer> map; // {timestamp, price} only store the correct records
    TreeMap<Integer, Integer> treeMap; // {price, count}
    
    public StockPrice() {
        latestTime = 0;
        map = new HashMap<>();
        treeMap = new TreeMap<>();
    }
    
    public void update(int timestamp, int price) {
        if (map.containsKey(timestamp)) {
            int oldPrice = map.get(timestamp);
            int oldCount = treeMap.get(oldPrice);
            if (oldCount == 1) {
                treeMap.remove(oldPrice);
            } else {
                treeMap.put(oldPrice, treeMap.get(oldPrice) - 1);
            }
        }
        map.put(timestamp, price);
        treeMap.put(price, treeMap.getOrDefault(price, 0) + 1);
        
        if (timestamp > latestTime) {
            latestTime = timestamp;
        }
    }
    
    // if we maintain a variable for the biggest timestamp
    // we can find the latest price of the stock in O(1)
    public int current() {
        return map.get(latestTime);
    }
    
    // how do we know maxPrice and minPrice?
    // if they happened to be a wrong record and is updated
    // how do we find the second largest/smallest?
    // a intuitive thought is to use minHeap/maxHeap
    // but it would take time to update a certain timestamp with a new value
    // a better option is to use a treeMap
    // key is price, value is count
    public int maximum() {
        return treeMap.lastKey(); 
    }
    
    public int minimum() {
        return treeMap.firstKey();
    }
}