class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // suppose newInterval appears somewhere in the middle
        // before that, we must have intervals[1] < newInterval[0]
        // after that, we must have intervals[0] > newInterval[1]
        List<int[]> ans = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        int i = 0;
        
        while (i < intervals.length && intervals[i][1] < start) {
            ans.add(intervals[i]);
            i++;
        }
        
        while (i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        // even if intervals is empty, we can add newInterval here
        ans.add(new int[]{start, end});
        
        while (i < intervals.length) {
            ans.add(intervals[i]);
            i++;
        }
        
        int[][] result = new int[ans.size()][2];
        for (int k=0; k<ans.size(); k++) {
            result[k] = ans.get(k);
        }
        return result;
    }
}