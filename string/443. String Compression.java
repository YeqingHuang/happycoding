class Solution {
    // method1: use a stringbuilder to store the answer
    public int compress(char[] chars) {
        if (chars.length <= 1) return chars.length;
    
        char prev = chars[0];
        int count = 1;
        StringBuilder sb = new StringBuilder();
        
        for (int i=1; i < chars.length; i++) {
            char curr = chars[i];
            if (curr == prev) {
                count++;
            } else {
                sb.append(prev);
                if (count > 1) sb.append(count);
                prev = curr;
                count = 1;
            }
        }
        sb.append(prev);
        if (count > 1) sb.append(count);
        for (int k=0; k<sb.length(); k++) {
            chars[k] = sb.charAt(k);
        }
        return sb.length();
    }

    // method2: in-place
    public int compress(char[] chars) {
        if (chars.length <= 1) return chars.length;
        
        char prev = chars[0];
        int count = 1;
        int index = 0; // first available index to overwrite
        
        for (int i=1; i < chars.length; i++) {
            char curr = chars[i];
            if (curr == prev) {
                count++;
            } else {
                chars[index++] = prev;
                if (count > 1) {
                    String countStr = Integer.toString(count);
                    for (char c: countStr.toCharArray()) {
                        chars[index++] = c;
                    }
                }
                prev = curr;
                count = 1;
            }
        }
        chars[index++] = prev;
        if (count > 1) {
            String countStr = Integer.toString(count);
            for (char c: countStr.toCharArray()) {
                chars[index++] = c;
            }
        }
        return index;
    }
}