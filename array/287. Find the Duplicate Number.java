class Solution {
    // note: there is only one duplicated num, 
    // but it can be something like [2,2,2,2]
    
    // method1: sorting
    // time O(nlogn), space O(1)
    public int findDuplicate1(int[] nums) {
        
        Arrays.sort(nums);
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }
        return -1; 
    }

    // method2: hashset
    // time O(n), space O(n)
    public int findDuplicate2(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int num: nums) {
            if (seen.contains(num)) {
                return num;
            } else {
                seen.add(num);
            }
        }
        return -1;
    }

    // method3: index, flip to negative
    // time O(n), space O(1)
    public int findDuplicate3(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                // this number has been processed before
                return Math.abs(nums[i]);
            } else {
                nums[index] = -nums[index];
            }
        }
        return -1;
    }

    // method4: index, place every num at the correct index
    // time O(n), space O(1)
    public int findDuplicate4(int[] nums) {
        // there are n+1 numbers and the range is [1,n]
        // so we place number i at index i, index 0 should store the dup number
        // let's say we always check nums[0], once we find the corresponding position
        // has already been placed with the correct number, we know current nums[0] is dup
        int curr = nums[0]; // stands for both num and index
        while (nums[curr] != curr) {
            // we give nums[curr] the correct value curr
            // but we don't want to overwrite nums[curr]
            // swap to process it in the next round
            swap(nums, 0, curr);
            curr = nums[0];
        }
        return nums[0];
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // method5: binary search
    // time O(nlogn), space O(1)
    public int findDuplicate5(int[] nums) {
        int low = 1;
        int high = nums.length - 1;
        int dup = -1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int count = 0;
            for (int num: nums) {
                if (num <= mid) {
                    count++;
                }
            }
            if (count > mid) {
                // ans may be mid itself, or a num smaller than mid
                dup = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return dup;
    }

    // method6: Tortoise and Hare (Cycle Detection)
    // time O(n), space O(1)
    public int findDuplicate6(int[] nums) {
        // stage1: slow pointer, faster pointer, let them meet
        // stage2: start another pointer from begining, when it meets with slow pointer
        // it's the entrance of the cycle, i.e. the duplicated number
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        
        // note that the start position is not nums[0]
        int third = 0; 
        while (third != slow) {
            third = nums[third];
            slow = nums[slow];
        }
        return slow;
    }
}