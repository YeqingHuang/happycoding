class Solution {
    public String removeOccurrences(String s, String part) {
        // obviously, we don't want to modify/generate the string so many times
        // how do we simulate the first occurence of part in s?
        // use a stack
        // when it's "daabc", we pop "c" "b" "a" and continue with "da"
        if (s.length() < part.length()) return s;
        
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (matchFound(stack, part)) {
                for (int i=0; i<part.length(); i++) {
                    stack.pop();
                }
            }
            stack.push(c);
        }
        
        // we need to check one more time
        if (matchFound(stack, part)) {
            for (int i=0; i<part.length(); i++) {
                stack.pop();
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c: stack) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    private boolean matchFound(Stack<Character> stack, String pattern) {
        int offset = stack.size() - pattern.length();
        if (offset < 0) return false; // not long enough to form pattern
        
        for (int i=0; i<pattern.length(); i++) {
            if (stack.get(i+offset) != pattern.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}