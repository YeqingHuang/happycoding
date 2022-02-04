class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // we want to return the count
        // as long as the index is different, they should be counted(not duplicated)
        // convert it to a two sum problem
        // we use nums1 and nums2 to form all the possible sum and get their freq
        // then we move on to nums3 and nums4 to get the answer
        Map<Integer, Integer> map = new HashMap<>();
        for (int a: nums1) {
            for (int b: nums2) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0)+1);
            }
        }
        
        int count = 0;
        for (int c: nums3) {
            for (int d: nums4) {
                int desired = -c-d;
                if (map.containsKey(desired)) {
                    count += map.get(desired);
                }
            }
        }
        return count;
    }
}