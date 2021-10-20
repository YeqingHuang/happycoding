/*
// Definition for an Interval.
class Interval {
    public int start;
    public int end;

    public Interval() {}

    public Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};
*/

class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        // [1,3],[2,4],[2,5],[6,7],[9,12]
        // merge serveral intervals until we get the ending of the merged interval
        // so we now have 5(which will serve as starting point)
        // we find [6,7], so we get [5,6]
        // using 7, we have another [9,12], so we get [7,9]
        List<Interval> intervals = new ArrayList<>();
        for (List<Interval> list: schedule) {
            intervals.addAll(list);
        }
        
        Collections.sort(intervals, (a,b) -> a.start - b.start);
        List<Interval> ans = new ArrayList<>();
        int i = 0;
        int currEnding = intervals.get(i).end;
        while (i < intervals.size()) {
            while (i < intervals.size() && intervals.get(i).start <= currEnding) {
                currEnding = Math.max(currEnding, intervals.get(i).end);
                i++;
            }
            // now currEnding can serve as the start of a free slot 
            if (i < intervals.size()) {
                ans.add(new Interval(currEnding, intervals.get(i).start));
                currEnding = intervals.get(i).end;
            }
        }
        return ans;
    }
}