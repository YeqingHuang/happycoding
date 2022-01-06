class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>(); // store index
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c)) continue;
            else if (c == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                stack.pop();
            } else {
                stack.push(i);
            } 
        }
        
        // the left ones in the stack are invalid ones
        Set<Integer> set = new HashSet<>();
        for (int index: stack) {
            set.add(index);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++) {
            if (!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}