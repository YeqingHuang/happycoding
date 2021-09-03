class MyHashMap {
    // 0 <= key, value <= 10^6
    private int[] arr;
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        arr = new int[1000001];
        Arrays.fill(arr, -1);
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        arr[key] = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return arr[key];
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        arr[key] = -1;
    }
}

class MyHashMap1 {

    class Chunk {
        private List<int[]> list; // don't use arraylist

        public Chunk() {
            list = new LinkedList<>(); 
        }
        
        public void putPair(int key, int value) {
            boolean foundKey = false;
            for (int i=0; i<list.size(); i++) {
                if (list.get(i)[0] == key) {
                    list.set(i, new int[]{key, value});
                    foundKey = true;
                    break;
                }
            }
            if (!foundKey) {
                list.add(new int[]{key, value});
            }
        }
        
        public int getValue(int key) {
            for (int[] pair: list) {
                if (pair[0] == key) {
                    return pair[1];
                }
            }
            return -1;
        }
        
        public void removePair(int key) {
            for (int i=0; i<list.size(); i++) {
                if (list.get(i)[0] == key) {
                    list.remove(i);
                }
            }
        }
    }
    
    final int prime = 997;
    private Chunk[] chunks;
    
    /** Initialize your data structure here. */
    public MyHashMap() {
        chunks = new Chunk[prime];
        for (int i=0; i<prime; i++) {
            chunks[i] = new Chunk();
        }
    }
    
    private int getHashCode(int key) {
        return key % prime;
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        // may be a new key or an existing key
        int index = getHashCode(key);
        chunks[index].putPair(key, value);
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = getHashCode(key);
        return chunks[index].getValue(key);
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = getHashCode(key);
        chunks[index].removePair(key);
    }
}