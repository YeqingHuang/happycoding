class Solution {
    public int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                // item at index stack.peek() can have a discount
                prices[stack.pop()] -= prices[i];
            }
            stack.push(i);
        }
        // note that if the original array has increasing subarray
        // then the stack is not empty at the end(it's ok)
        return prices;
    }
}