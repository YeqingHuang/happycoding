class Solution {
    public int maxNumberOfBalloons(String text) {
        Set<Character> desired = new HashSet<>(Arrays.asList('b', 'a', 'n', 'l', 'o'));
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: text.toCharArray()) {
            if (desired.contains(c)) {
                freq.put(c, freq.getOrDefault(c, 0) + 1);
            }
        }
        for (char c: desired) {
            if (!freq.containsKey(c)) {
                return 0;
            }
            if (c == 'l' || c == 'o') {
                freq.put(c, freq.get(c)/2);
            }  
        }

        int count = Integer.MAX_VALUE;
        for (char c: freq.keySet()) {
            count = Math.min(freq.get(c), count);
        }
        return count;
    }
}