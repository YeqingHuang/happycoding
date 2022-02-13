class MaxStack {
    private Stack<Integer> stack, maxStack;
    
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty() || maxStack.peek() <= x) {
            maxStack.push(x);
        }
    }
    
    public int pop() {
        int popped = stack.pop();
        if (popped == maxStack.peek()) {
            maxStack.pop();
        }
        return popped;
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int maxVal= maxStack.pop();
        
        List<Integer> temp = new ArrayList<>();
        while (stack.peek() != maxVal) {
            temp.add(stack.pop());
        }
        stack.pop();
        for (int i=temp.size()-1; i>=0; i--) {
            // note that we may need to add some values to maxStack
            // so we use this.push(), not stack.push()
            push(temp.get(i)); 
        }
        
        return maxVal;
    }
}