// https://leetcode.com/problems/subarrays-with-k-different-integers/description/


// Solution 2: For a naive sliding window, e.g., we found (l-r) has k diff ints, but it cannot list all possible subarrays.
// Because if we use i runs inside (l-r), so that the (l->i) is all possible startPoint with k diffs that ends at r, then updates l to i + 1. 
// For the the next r + 1, the l has already been updated and cannot move backward, that means we can lose possible answer, e.g.
// nums: 1  1  2  3  1, k = 3
// l = 0, r = 3, then i = 1 => res = 2;
// But if we update l = i + 1 = 2, then with the next r = 4, l = 2, then we lose some possible ans at l = 0 and l = 1.

// So the naive sliding window does not best fit for finding EXACT K problems, instead it's the best for finding AT MOST K problems.
// We notice that, if the at r, the num of diff ints in the window is k, then any items after l, will produce a subarray with <= k distinct ints that ends at r.
// So we use that approach to find AT MOST K and AT MOST (K - 1) diff ints result. Then atMostK - atMost(K-1) will result int EXACT K diff ints in the windows.

// Time complexity: O(n)
// Space complexity: O(n) because we dont remove items if its frequence down to 0.

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> count = new HashMap<>();
        int l = 0, diffInts = 0, res = 0;

        for (int r = 0; r < n; r++) {
            if (!count.containsKey(nums[r]) || count.get(nums[r]) == 0) {
                diffInts++;
            }
            count.put(nums[r], count.getOrDefault(nums[r], 0) + 1);

            while (diffInts > k) {
                count.put(nums[l], count.get(nums[l]) - 1);
                if (count.get(nums[l]) == 0) {
                    diffInts--;
                }
                l++;
            }

            res += r - l + 1;
        }
        return res;
    }
}


// Solution 1: Brute-force. List all possible subarray and keep track of the different integers using hash set.
// But got TLE.

// Time complexity: O(n^2)
// Space complexity: O(n)

class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int n = nums.length;
        HashSet<Integer> count = new HashSet<>();
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                count.add(nums[j]);
                if (count.size() == k) {
                    res++;
                } else if (count.size() > k) {
                    break;
                }
            }
            count.clear();
        }
        return res;
    }
}

// Solution 3: Reference only: https://leetcode.com/problems/subarrays-with-k-different-integers/solutions/4943720/subarrays-with-k-different-integers/
// We use sliding window, with left will always be updated to the shortest possible window that has k diffs that ends at right.
// We can think of longest possible windows that has k diffs that ends at right => (left - longestIndex) = currCount
// It is the number that we need to add to result, for each right endpoint. 
// If the next item (r + 1) does not change the nums of diffs (same elems as in the window), we need to:
//  - Update left to the shortest possible, frequence of an item that is 1 (=> it will be change to 0 if left go over it) and update currCount++ accordingly.
//  - Add currCount to the result as it's the (left - longestIndex) subarrays the contains k diffs ending at right.

// Time complexity: O(n)
// Space complexity: O(1)
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        // Array to store the count of distinct values encountered
        int[] distinctCount = new int[nums.length + 1];

        int totalCount = 0;
        int left = 0;
        int right = 0;
        int currCount = 0;

        while (right < nums.length) {
            // Increment the count of the current element in the window
            if (distinctCount[nums[right++]]++ == 0) {
                // If encountering a new distinct element, decrement K
                k--;
            }

            // If K becomes negative, adjust the window from the left
            if (k < 0) {
                // Move the left pointer until the count of distinct elements becomes valid again
                --distinctCount[nums[left++]];
                k++;
                currCount = 0;
            }

            // If K becomes zero, calculate subarrays
            if (k == 0) {
                // While the count of left remains greater than 1, keep shrinking the window from the left
                while (distinctCount[nums[left]] > 1) {
                    --distinctCount[nums[left++]];
                    currCount++;
                }
                // Add the count of subarrays with K distinct elements to the total count
                totalCount += (currCount + 1);
            }
        }
        return totalCount;
    }
}
