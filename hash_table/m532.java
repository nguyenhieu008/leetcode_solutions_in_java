// https://leetcode.com/problems/k-diff-pairs-in-an-array/

// Solution 1: Because just run 1-pass, both for adding value to hash map and update result, so it will not cause duplicates. if k == 0, we need to know if nums[i] exist, but only count 1 time for it.
// So we use a hash map to store additional boolean that indicates whether that num already counted for result in case of k == 0;
// If we run 2-pass, 1 for adding and 1 for finding, we will use only 1 condition, e.g. (v - k). Because if we use 2 conditions, it will generate duplicates.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int findPairs(int[] nums, int k) {
        HashMap<Integer, Boolean> map = new HashMap<>();

        int res = 0;
        for (int v : nums) {
            if (!map.containsKey(v)) {
                res += map.containsKey(v - k) ? 1 : 0;
                res += map.containsKey(v + k) ? 1 : 0;
                map.put(v, false);
            } else {
                if (k == 0 && !map.get(v)) {
                    map.put(v, true);
                    res++;
                }
            }
        }
        return res;
    }
}

// Solution 2 & 3: Just for reference, apply sorting and binary + 2-pointers.
class Solution {
    public int findPairs(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            res += Arrays.binarySearch(nums, i + 1, n, nums[i] + k) >= 0 ? 1 : 0;
            while (i + 1 < n && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }
}

class Solution {
    public int findPairs(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int res = 0;
        int i = 0, j = i + 1;

        while (i < n - 1) {
            // find the first j that nums[j] >= nums[i] + k
            while (j < n && nums[j] < nums[i] + k) {
                j++;
            }
            if (j >= n) {
                return res;
            }
            if (nums[i] + k == nums[j]) {
                res++;
            }
            // go to the next different value for i
            while (i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
            i++;
            // ensure j is not at the left of i
            j = Math.max(i + 1, j);
        }
        return res;
    }
}

// solution 2-pointer can try the base pointer is the right pointer => we do not need to care about that the point go over the size of array.
// copied from solutions
class Solution {
    public int findPairs(int[] nums, int k) {
        if(nums == null || nums.length < 1) return 0;
        
        Arrays.sort(nums);
        int l = 0, ans = 0, prev = Integer.MAX_VALUE;
        for(int r = 1; r < nums.length; r++) {
            while(l < r && nums[r] - nums[l] > k) l++;
            
            if(l != r && prev != nums[l] && nums[r] - nums[l] == k) {
                ans++;
                prev = nums[l];
            }
        }
        return ans;
    }
}
