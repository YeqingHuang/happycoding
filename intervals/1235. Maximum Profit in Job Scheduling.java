class Solution {
    class Job {
        int start, end, profit;
        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }

    int[] dp;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        dp = new int[n];
        Arrays.fill(dp, -1);

        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        Collections.sort(jobs, (a,b) -> Integer.compare(a.start, b.start));

        // will be used for binary search
        for (int i = 0; i < n; i++) {
            startTime[i] = jobs.get(i).start;
        }
        return findMaxProfit(jobs, startTime, 0);
    }

    private int findMaxProfit(List<Job> jobs, int[] startTime, int index) {
        if (index == startTime.length || index == -1) return 0;

        if (dp[index] != -1) return dp[index];

        int nextIndex = findNextJob(startTime, jobs.get(index).end);
        int include = jobs.get(index).profit + findMaxProfit(jobs, startTime, nextIndex);
        int skip = findMaxProfit(jobs, startTime, index + 1);
        int maxProfit = Math.max(include, skip);

        dp[index] = maxProfit;
        return maxProfit;
    }

    private int findNextJob(int[] startTime, int ending) {
        // find the leftmost job whose job.start >= ending
        int low = 0;
        int high = startTime.length - 1;
        int ans = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (startTime[mid] >= ending) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}


class Solution {
    // instead of implementing binary search, we can also use a TreeMap
    // key is a job's ending time, value is max profit sum
    class Job {
        int start, end, profit;
        public Job(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }
    }
    
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {        
        int n = profit.length;
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        // this time we sort by ending time
        Collections.sort(jobs, (a,b) -> Integer.compare(a.end, b.end));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // choose the 0th job, we get a profit of 0
        map.put(0,0); 
        
        for (Job job: jobs) {
            int prevIndex = map.floorKey(job.start);
            int include = map.get(prevIndex) + job.profit;
            int currMax = map.lastEntry().getValue();
            if (include > currMax) {
                map.put(job.end,include);
            }
        }
        return map.lastEntry().getValue();
    }
}