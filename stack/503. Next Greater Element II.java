class Solution {
    public int[] nextGreaterElements(int[] nums) {
        // diff with 496. Next Greater Element I
        // 1. nums contain duplicated numbers, so same value may have different answer
        // 2. we can have circular check, so only the biggest number has -1
        // nums = [4,1,6,3,5,3]
        // ans = [6,6,-1,5,6,4]
        // 1. is easy to fix, store indexes instead of values
        // how to solve 2.? using previous method, stack is now left with
        // [6,5,3], the corresponding indexes are [2,4,5]
        // first index is for sure the biggest number, it has -1
        // for index 4, we need to check [0,4-1]
        // for index 5, we need to check [0,5-1]
        Map<Integer, Integer> map = new HashMap<>(); // index, next greater
        Stack<Integer> stack = new Stack<>(); // index
        for (int i=0; i<nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                map.put(stack.pop(), nums[i]);
            }
            stack.push(i);
        }
        
        int k = 0;
        while (!stack.isEmpty() && k < stack.peek() ) {
            while (nums[stack.peek()] < nums[k]) {
                map.put(stack.pop(), nums[k]);
            }
            k++;
        }
        
        // consider [6,6,6,6,6], stack has [0,1,2,3,4]
        int[] ans = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            ans[i] = map.containsKey(i) ? map.get(i) : -1;
        }
        return ans;
    }
}

class Solution {
    // a naive O(n^2) method
    public int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i=0; i<nums.length; i++) {
            ans[i] = -1;
            for (int k=1; k<nums.length; k++) {
                int index = (i + k) % nums.length;
                if (nums[index] > nums[i]) {
                    ans[i] = nums[index];
                    break;
                }
            }
        }
        return ans;
    }
}