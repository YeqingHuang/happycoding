class Solution {
    // time: O(m+n)
    // space: O(m+n)
    public int[] intersection(int[] nums1, int[] nums2) {
        // the easiest solution, hashset
        Set<Integer> set1 = new HashSet<>();
        for (int num: nums1) {
            set1.add(num);
        }
        
        Set<Integer> set2 = new HashSet<>();
        for (int num: nums2) {
            if (set1.contains(num)) {
                set2.add(num);
            }
        }
        
        int[] ans = new int[set2.size()];
        int k = 0;
        for (int unique: set2) {
            ans[k++] = unique;
        }
        return ans;
    }

    // time: O(mlogm + nlogn)
    // space: O(min(m,n))
    public int[] intersection1(int[] nums1, int[] nums2) {
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
                if (ans.isEmpty() || ans.get(ans.size()-1) != nums1[i]) {
                    ans.add(nums1[i]);
                }
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