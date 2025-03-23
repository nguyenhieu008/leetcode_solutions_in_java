// https://leetcode.com/problems/binary-subarrays-with-sum/description/

// Solution 4: Sliding window 1-pass, we must keep the prefix zeros of the window, so can add them to the result.

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int l = 0, prefixZeros = 0;
        int count = 0, res = 0;

        for (int r = 0; r < n; r++) {
            count += nums[r];

            while (l < r && (nums[l] == 0 || count > goal)) {
                if (nums[l] == 1) {
                    prefixZeros = 0;
                } else {
                    prefixZeros++;
                }
                count -= nums[l];
                l++;
            }

            if (count == goal) {
                res += prefixZeros + 1;
            }
        }
        return res;
    }
}

// Solution 3: Sliding window 2-pass using atMost.

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int k) {
        int n = nums.length;
        int l = 0, count = 0, res = 0;

        for (int r = 0; r < n; r++) {
            count += nums[r];

            while (l <= r && count > k) {
                count -= nums[l];
                l++;
            }

            res += r - l + 1;
        }
        return res;
    }
}


// Solution 2: Same with solution 1, but use array for count frequency => easily handle null pointer case and also faster.
class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        int[] count = new int[n + 1];
        count[0] = 1;
        int sum = 0, res = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (sum - goal >= 0) {
                res += count[sum - goal];
            }
            count[sum]++;
        }
        return res;
    }
}

// Solution 1a: Hash table with prefix sum but handle better.

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int totalCount = 0;
        int currentSum = 0;
        // {prefix: number of occurrence}
        Map<Integer, Integer> freq = new HashMap<>(); // To store the frequency of prefix sums

        for (int num : nums) {
            currentSum += num;
            // THIS ONE IS KEY, SO WE do not need to freq.put(0, 1); then do not need to handle edge case for 0
            if (currentSum == goal){
                totalCount++;
            }

            // Check if there is any prefix sum that can be subtracted from the current sum to get the desired goal
            if (freq.containsKey(currentSum - goal)){
                totalCount += freq.get(currentSum - goal);
            }

            freq.put(currentSum, freq.getOrDefault(currentSum, 0) + 1);
        }

        return totalCount;
    }
}

// Solution 1: Hash table with prefix sum. 

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        // Beware case of goal = 0, this may affect the result
        map.put(0, 1);
        int sum = 0, res = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i];
            // If goal equal 0, => if we access map.get(sum-goal) will result in null pointer
            // That means we need to initialize it to 0 here. Also need to use getOrDefault to bypass case [0, 0, 0, ...]
            if (goal == 0) {
                map.put(sum, map.getOrDefault(sum, 0));
            }
            if (sum - goal >= 0) {
                res += map.get(sum - goal);
            }
            // We need to update the map AFTER update the res, to handle case goal = 0, because we already initialize the map with an empty array
            // => If we update the map BEFORE update the res, there is one more than real-array.
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
