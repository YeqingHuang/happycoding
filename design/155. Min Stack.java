class MinStack {
    // the only difference is that it has an additional method
    // getMin() and we require it in O(1)
    // if current min get popped, how do we know what's the next min number
    // maintain a non-increasing stack to store this information
    Stack<Integer> stack;
    Stack<Integer> minStack;
    
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        int popped = stack.pop();
        if (minStack.peek() == popped) {
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}