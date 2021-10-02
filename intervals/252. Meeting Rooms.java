class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        // determine if a person can attend all the meetings
        
        int n = intervals.length;
        if (n <= 1) return true;
        
        // if there is an overlapping, he cannot attend
        // sort by starting time first
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i=1; i<n; i++) {
            if (intervals[i][0] < end) {
                return false;
            } 
            start = intervals[i][0];
            end = intervals[i][1];
        }
        return true;
    }
}