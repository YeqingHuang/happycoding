class Solution {
    public int leastInterval(char[] tasks, int n) {
        // another version of 767. Reorganize String
        // 6A 1B 3C 2D, n = 2, greedy
        // A _ _ A _ _ A _ _ A _ _ A _ _ A
        // A C _ A C _ A C _ A _ _ A _ _ A
        // A C _ A C _ A C _ A D _ A D _ A
        // A C B A C _ A C _ A D _ A D _ A
        // therefore ans = 12 + 4 = 16
        if (n == 0) return tasks.length;
        
        int[] freq = new int[26];
        for (char task: tasks) {
            freq[task - 'A']++;
        }
        
        // sort by frequency, check from right to left
        Arrays.sort(freq);
        int maxFreq = freq[25];
        if (maxFreq == 1) return tasks.length;
        
        int slotCount = maxFreq - 1;
        int totalSlots = slotCount * n;
        // we only care about how many slots can be eliminated
        // the number of chars is always there, i.e. tasks.length
        for (int i=24; i>=0; i--) {
            if (freq[i] == 0) break;
            // consider ["A","A","A","B","B","B"]
            // slotCount is 2, but B still has a frequency of 3
            int filled = Math.min(slotCount, freq[i]);
            totalSlots -= filled;
        }
        return tasks.length + Math.max(0, totalSlots);
    }
}