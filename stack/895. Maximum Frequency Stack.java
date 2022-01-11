class FreqStack {
    // when it's [5,7,5,7,4], 
    // suppose we have a frequecy map like {5:2, 7:2, 4:1} and we also know curr maxFreq = 2
    // how do we know we should pop 7? the most tricky part is to use another map
    // key is the frequency, value is a stack of numbers {2:[5,7], 1:[4]}
    Map<Integer, Integer> map;
    Map<Integer, Stack<Integer>> stackMap;
    int maxFreq;
    
    public FreqStack() {
        map = new HashMap<>();
        stackMap = new HashMap<>();
        maxFreq = 0;
    }
    
    public void push(int val) {
        int freq = map.getOrDefault(val, 0) + 1;
        
        map.put(val, freq);
        maxFreq = Math.max(freq, maxFreq);
        
        stackMap.putIfAbsent(freq, new Stack<>());
        stackMap.get(freq).push(val);
    }
    
    public int pop() {
        int num = stackMap.get(maxFreq).pop();
        
        map.put(num, map.get(num) - 1);
        
        // remember to update maxFreq
        if (stackMap.get(maxFreq).size() == 0) {
            // suppose maxFreq is 5 and popped num is 100, when we lose a 100
            // its freq becomes 4
            // it does not matter if there is other num that has a freq of 4
            // 100 definitely has a freq of 4, it's safe to say maxFreq -= 1
            maxFreq--;
        }
        return num;
    }
}