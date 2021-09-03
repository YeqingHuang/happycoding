class MyHashSet {
    // similar to HashMap, we can use a linked list to store the numbers
    // this linked list should only be accessed by the public methods
    class Chunk {
        private LinkedList<Integer> numbers;
        
        public Chunk() {
            numbers = new LinkedList<>();
        }
        
        public void addNum(int key) {
            // we should not add a number twice
            int index = numbers.indexOf(key);
            if (index == -1) {
                numbers.addLast(key);
            }
        }
        
        public void removeNum(int key) {
            Integer tobeRemoved = key;
            numbers.remove(tobeRemoved);
        }
        
        public boolean containNum(int key) {
            return numbers.indexOf(key) != -1;
        }
    }
    
    final int prime = 997;
    private Chunk[] chunks;
    
    /** Initialize your data structure here. */
    public MyHashSet() {
        chunks = new Chunk[prime];
        for (int i=0; i<prime; i++) {
            chunks[i] = new Chunk();
        }
    }
    
    public void add(int key) {
        int index = getHashCode(key);
        chunks[index].addNum(key);
    }
    
    public void remove(int key) {
        int index = getHashCode(key);
        chunks[index].removeNum(key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int index = getHashCode(key);
        return chunks[index].containNum(key);
    }
    
    private int getHashCode(int key) {
        return key % prime;
    }
}