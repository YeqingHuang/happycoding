class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        // sort and use two pointers
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> ans = new ArrayList<>();
        while (i<nums1.length && j<nums2.length) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                ans.add(nums1[i]);
                i++;
                j++;
            }
        }
        
        int[] intersection = new int[ans.size()];
        for(int k=0; k<ans.size(); k++) {
            intersection[k] = ans.get(k);
        }
        return intersection;
    }
}