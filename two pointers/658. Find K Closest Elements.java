class Solution {
    // easy to understand: O(nlogn + klogk)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // NOTE: x may exist in the arr or not
        // sort by distance, we don't need to add index
        // it is reflected when we convert array to arraylist
        List<Integer> nums = new ArrayList<>();
        for(int num: arr) {
            nums.add(num);
        }
        Collections.sort(nums, (a,b) -> Double.compare(Math.abs(a - x), Math.abs(b - x)));
        
        List<Integer> ans = nums.subList(0,k);
        // The result should also be sorted
        Collections.sort(ans);
        return ans;
    }

    // method2: make use of the property that arr is already sorted
    // use binary search to find the starting index
    // use two pointers to expand to both sides
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // use linkedlist to avoid an additional sorting 
        LinkedList<Integer> ans = new LinkedList<>();
        int index = findNearest(arr, x);
        ans.add(arr[index]);
        int i = index - 1;
        int j = index + 1;
        
        while (ans.size() < k) {
            int leftDist = i>=0 ? Math.abs(arr[i] - x) : Integer.MAX_VALUE;
            int rightDist = j<arr.length ? Math.abs(arr[j] - x) : Integer.MAX_VALUE;
            if (rightDist < leftDist) {
                ans.addLast(arr[j++]);
            } else {
                ans.addFirst(arr[i--]);
            }
        }
        return new ArrayList<>(ans);
    }
    
    private int findNearest(int[] arr, int x) {
        if (x <= arr[0]) return 0;
        if (x >= arr[arr.length - 1]) return arr.length - 1;
        
        int low = 0, high = arr.length - 1;
        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return Math.abs(arr[low] - x) <= Math.abs(arr[high] - x) ? low: high;
    }
}