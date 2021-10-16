class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // how to find 4/333 = 0.012012012
        // use a hashmap, key is current numerator, value is the length of current ans
        // therefore we would know where to insert '('
        if (numerator == 0) return "0";
        
        StringBuilder ans = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            ans.append("-");
        }
        
        long up = Math.abs(Long.valueOf(numerator));
        long down = Math.abs(Long.valueOf(denominator));
        long q = up / down;
        ans.append(String.valueOf(q));
        long r = up % down;
        if (r == 0) return ans.toString();
        
        ans.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (r != 0) {
            if (map.containsKey(r)) {
                // insert(int offset, String str), if offset = 5, then insert from the 6th char
                ans.insert(map.get(r), "(");
                ans.append(")");
                return ans.toString();
            }
            // get current length before this round of processing
            map.put(r, ans.length());
            r = r * 10;
            ans.append(String.valueOf(r / down));
            r = r % down;
        }
        return ans.toString();
    }
}