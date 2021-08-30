class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        
        Stack<Integer> countStack = new Stack<>();
        Stack<String> suffixStack = new Stack<>();
        String curr = "";
        int num = 0;
        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (Character.isLetter(c)) {
                curr += c;
            } else if (c == '[') {
                countStack.push(num);
                suffixStack.push(curr);
                num = 0;
                curr = "";
            } else {
                String prev = suffixStack.pop();
                int count = countStack.pop();
                curr = prev + curr.repeat(count);
            }
        }
        return curr;
    }

    // use a stringbuilder to make it faster
    public String decodeString1(String s) {
        if (s == null || s.length() == 0) return "";
        
        Stack<Integer> countStack = new Stack<>();
        Stack<String> suffixStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int num = 0;
        for (char c: s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (Character.isLetter(c)) {
                curr.append(c);
            } else if (c == '[') {
                countStack.push(num);
                suffixStack.push(curr.toString());
                num = 0;
                curr = new StringBuilder();
            } else {
                int count = countStack.pop();
                String prev = suffixStack.pop();
                StringBuilder updated = new StringBuilder(prev);
                for (int i=0; i<count; i++) {
                    updated.append(curr);
                }
                curr = updated;
            }
        }
        return curr.toString();
    }
}