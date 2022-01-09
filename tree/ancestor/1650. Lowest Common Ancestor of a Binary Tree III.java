class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        // we are not given the root of a tree, but we have parent pointer
        // a node can be the ancestor of itself and the other node
        // example2: for p=5, its trace is 5->3
        // for q = 4, its trace is 4->2->5->3
        Stack<Node> pStack = getTrace(p);
        Stack<Node> qStack = getTrace(q);
        Node ancestor = null;
        
        while (!pStack.isEmpty() && !qStack.isEmpty()) {
            if (pStack.peek().val != qStack.peek().val) {
                return ancestor;
            } else {
                ancestor = pStack.peek();
                pStack.pop();
                qStack.pop();
            }
        }
        return ancestor;
    }
    
    private Stack<Node> getTrace(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.parent;
        }
        return stack;
    }
}

class Solution1 {
    public Node lowestCommonAncestor(Node p, Node q) {
        // a better solution which still requires additional space
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }
        
        while (q != null) {
            if (set.contains(q)) return q;
            else q = q.parent;
        }
        return null;
    }
}