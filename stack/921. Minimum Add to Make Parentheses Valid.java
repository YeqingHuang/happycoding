class Solution {
    public int minAddToMakeValid(String s) {
        // suppose we process it so only the invalid ones are left
        // ")(", they are single, we need to double the length to get it valid
        Stack<Character> stack = new Stack<>();
        for (char c: s.toCharArray()) {
            if (c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.size();
    }
}