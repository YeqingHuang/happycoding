class Solution {
    // method1: use a stringbuilder to store the answer
    public int compress(char[] chars) {
        if (chars.length <= 1) return chars.length;
        
        StringBuilder sb = new StringBuilder();
        char prev = chars[0];
        int count = 1;
        for (int i=1; i<chars.length; i++) {
            if (chars[i] == prev) {
                count++;
            } else {
                sb.append(prev);
                if (count > 1) {
                    sb.append(Integer.toString(count));
                } 
                // reset variables
                prev = chars[i];
                count = 1;
            }
        }
        // don't forget this step
        sb.append(prev);
        if (count > 1) {
            sb.append(Integer.toString(count));
        } 
        
        for (int k = 0; k < sb.length(); k++){
            chars[k] = sb.charAt(k);
        }
        return sb.length();
    }

    // method2: in-place
    public int compress(char[] chars) {
        if (chars.length <= 1) return chars.length;
        
        int index = 0; // first available pos
        char prev = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == prev) {
                count++;
            } else {
                chars[index++] = prev;
                if (count > 1) {
                    for (char digit: String.valueOf(count).toCharArray()) {
                        chars[index++] = digit;
                    } 
                }
                prev = chars[i];
                count = 1;
            }
        }
        chars[index++] = prev;
        if (count > 1) {
            for (char digit: String.valueOf(count).toCharArray()) {
                chars[index++] = digit;
            }
        }
        return index;
    }
}