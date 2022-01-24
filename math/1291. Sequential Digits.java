class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        int[] smallest = {0,1,12,123,1234,12345,123456,1234567,12345678, 123456789};
        
        List<Integer> ans = new ArrayList<>();
        int minLength = Integer.toString(low).length();
        int maxLength = Math.min(Integer.toString(high).length(), 9);
        for (int length = minLength; length <=maxLength; length++) {
            int num = smallest[length]; // beginning num given this length
            while (num < low && num != -1) {
                // one edge case: suppose low = 8999, biggest num is 6789
                // when num becomes -1, num < low will always be satisfied and we get stuck
                num = findNextNum(num, length);
            } 
            while (num >= low && num <= high) {
                ans.add(num);
                num = findNextNum(num, length);
            } 
        }
        return ans;
    }
    
    private int findNextNum(int value, int n) {
        int[] delta = {0, 1, 11, 111, 1111, 11111, 111111, 1111111, 11111111,111111111};
        
        int num = value;
        while (num > 10) {
            num = num / 10;
        }
        // now num stands for the left most digit
        // 789 is the biggest for n=3, there is no next num
        if (num == 10 - n) return -1;

        return value + delta[n];
    }
}