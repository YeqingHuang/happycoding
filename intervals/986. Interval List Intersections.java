class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        // suppose we use two pointers for the two lists
        // we want intersections, not merged ones
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int[] a = firstList[i];
            int[] b = secondList[j];
            int newStart = Math.max(a[0],b[0]);
            int newEnd = Math.min(a[1],b[1]);
            if (newStart <= newEnd) {
                ans.add(new int[]{newStart, newEnd});
            } 
            if (a[1] < b[1]) {
                // a ends earlier, b still has the chance 
                // to intersect with next a
                i++;
            } else {
                j++;
            }
        }
        
        // a faster way is to use .toArray()
        // return ans.toArray(new int[ans.size()][]);
        int[][] intersections = new int[ans.size()][2];
        for (int k=0; k<ans.size(); k++) {
            intersections[k] = ans.get(k);
        }
        return intersections;
    }
}