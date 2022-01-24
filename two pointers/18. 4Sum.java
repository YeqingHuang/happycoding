class Solution {
    // a method based on 3sum   
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 4) return ans;
        
        Arrays.sort(nums);
        for (int i=0; i<nums.length - 3; i++) {
            if (i>0 && nums[i] == nums[i-1]) continue;
            for (int j=i+1; j<nums.length - 2; j++) {
                if (j>i+1 && nums[j] == nums[j-1]) continue;
                int desired = target - nums[i] - nums[j];
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (nums[left] + nums[right] == desired) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left > j+1 && nums[left] == nums[left-1]) {
                            left++;
                        }
                        while (right-1 > left && nums[right] == nums[right-1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < desired) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return ans;
    }
}

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // we can somehow write a k sum
        // the base case is when k = 2, we use left pointer and right pointer 
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }
    
    private List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (start == nums.length) return ans;
        
        if (k == 2) return twoSum(nums, target, start);
        
        for (int i=start; i<nums.length; i++) {
            // skip duplicates
            if ( i > start && nums[i] == nums[i-1]) {
                continue;
            }
            for (List<Integer> result: kSum(nums, target - nums[i], i+1, k - 1)) {
                // we've find an answer, insert nums[i] at index 0
                result.add(0, nums[i]);
                ans.add(result);
            }
            
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> ans = new ArrayList<>();
        int i = start, j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                ans.add(Arrays.asList(nums[i], nums[j]));
                while (i<j && nums[i+1] == nums[i]) i++; 
                while (i<j && nums[j-1] == nums[j]) j--; 
                i++;
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return ans;
    }
}