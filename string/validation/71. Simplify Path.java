class Solution {
    public String simplifyPath(String path) {
        // 1. begins with '/' and does not end with '/'
        // 2. no "."(current dir) or ".." (up a level)
        // 3. multiple consecutive slashes are simplied as one '/'
        // "/./a" -> "/a"
        // "t/a/../b" -> "t/b", if it's a stack, we pop a and push b
        
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        for (String part: parts) {
            if (part.equals(".") || part.isEmpty())  {
                continue;
            }
            else if (part.equals("..")) {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        // concat the components with "/" in reverse order
        for (String s: stack) {
            sb.append("/");
            sb.append(s);
        }
        return stack.isEmpty() ? "/" : sb.toString();
    }
    
    // private void printArray(String[] parts) {
    //     StringBuilder sb = new StringBuilder();
    //     for (String p: parts) {
    //         sb.append(p);
    //         sb.append("_");
    //     }
    //     System.out.println(sb);
    // }
}