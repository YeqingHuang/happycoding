class Solution {
    public int findMinArrowShots(int[][] points) {
        // sort by end time, pick the one that end earliest to give the first arrow
        // this one has currEnding, then begin to iterate the following ballons
        // if its start <= currEnding, it can be solved with existing arrow
        // otherwise, get another arrow
        Arrays.sort(points, (a,b) -> Integer.compare(a[1],b[1]));
        int arrows = 1;
        int currEnding = points[0][1];
        for (int i=1; i<points.length; i++) {
            if (points[i][0] <= currEnding) {
                continue;
            } else {
                arrows++;
                currEnding = points[i][1];
            }
        }
        return arrows;
    }
}

class Solution1 {
    public int findMinArrowShots(int[][] points) {
        // sort the intervals by starting point
        // pick the leftmost interval to begin with
        // as long as the next interval's starting point is before current ending
        // it can be burst with current arrow
        Arrays.sort(points, (a,b) -> Integer.compare(a[0],b[0]));

        int arrow = 1;
        int ending = points[0][1];
        for (int i=1; i<points.length; i++) {
            if (points[i][0] <= ending) {
                // can be solved with current arrow
                // but we also needs to update the ending
                ending = Math.min(ending, points[i][1]);
            } else {
                // we need a new arrow
                arrow++;
                ending = points[i][1];
            }
        }
        return arrow;
    }
}