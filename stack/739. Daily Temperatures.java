class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // suppose we use a stack to process, the stack stores indexes
        // we only calculate when an index is poped
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[temperatures.length];
        
        // decreasing
        for (int i=0; i<temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }
        return ans;
    }
}