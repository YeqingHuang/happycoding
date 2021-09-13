class Solution {
    public int[][] merge(int[][] intervals) {
        // we maintain a currStart and currEnd
        // if it overlaps with the next interval, update it
        // otherwise, put it to the answer, reset currStart and currEnd as the next interval
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        List<int[]> ans = new ArrayList<>();
        int currStart = intervals[0][0];
        int currEnd = intervals[0][1];
        for(int i=1; i<intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            if (start <= currEnd) {
                currEnd = Math.max(end, currEnd);
            } else {
                ans.add(new int[] {currStart, currEnd});
                currStart = start;
                currEnd = end;
            }
        }
        ans.add(new int[] {currStart, currEnd});
        
        return ans.toArray(new int[ans.size()][2]);
    }
}