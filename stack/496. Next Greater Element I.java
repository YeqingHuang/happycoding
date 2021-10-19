class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // suppose nums2 = [3,1,5,2,4]
        // next greater is [5,5,-1,4,-1]
        // how to get this array in O(n)?
        // maintain a decreasing stack
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>(); // store numbers
        for (int i=0; i<nums2.length; i++) {
            while (!stack.isEmpty() && stack.peek() < nums2[i]) {
                map.put(stack.pop(), nums2[i]);
            }
            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        
        for (int i=0; i<nums1.length; i++) {
            nums1[i] = map.get(nums1[i]);
        }
        return nums1;
    }
}