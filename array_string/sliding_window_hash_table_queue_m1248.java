// https://leetcode.com/problems/count-number-of-nice-subarrays/description/

// Solution 4: SLIDING WINDOW - same as: 992. Subarrays with K Different Integers, https://leetcode.com/problems/subarrays-with-k-different-integers/description/
// To find nums of subarrays with sum at most k, for each endIndex, we find the first startIndex that where subarray startIndex-endIndex contains exactly k odds (or less than k if startIndex=0)
// => Any index after startIndex will form a subarray that ends at endIndex and have less than k odd nums (satisfy at most condition) => we add (endIndex - startIndex + 1) to result.
// => We keep our sliding window, so that the left will always be the first item that makes the window has exact k odd number.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int n = nums.length;
        int l = 0, res = 0, count = 0;

        for (int r = 0; r < n; r++) {
            count += nums[r] & 1;

            while (count > k) {
                count -= nums[l] & 1;
                l++;
            }

            res += r - l + 1;
        }
        return res;
    }
}

// Solution 3: SLIDING WINDOW - QUEUE: Same idea as the Solution 1, but we save the indexes of odd number in the window into a queue, 
// So when the window has more than k odd nums, we pop the first item and save to a prev, in order to calculate how many subarray that ends at the current endpoint.

// Time complexity: O(n)
// Space complexity: O(k)

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> oddIndexes = new ArrayDeque<>();
        int prev = -1;
        int res = 0;

        for (int i = 0; i < n; i++) {
            if ((nums[i] & 1) == 1) {
                oddIndexes.offerLast(i);
            }
            if (oddIndexes.size() > k) {
                prev = oddIndexes.removeFirst();
            }
            if (oddIndexes.size() == k) {
                res += oddIndexes.peekFirst() - prev;
            }
        }
        return res;
    }
}

// Solution 2: HASH TABLE - PREFIX SUM: With each odd number, we increase the prefix sum by 1.
// for each i, number of subarray with k odd nums that ends at i is the number of subarray with prefix sum = (cur_sum - k)
// => We store count of each prefix sum in a hash table, and look up for (cur_sum - k) to add to the result.

// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[n + 1];
        count[0] = 1;
        int res = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            sum += nums[i] % 2;
            if (sum >= k) {
                res += count[sum - k];
            }
            count[sum]++;
        }
        return res;
    }
}



// Solution 1: SLIDING WINDOW: We keep window l->r is the minimum window that has k odd num. It means the l will be the odd number (r can be even num as it always increases). To do that:
//    - When r meet odd and the odd number > k => move l by 1 item => count--
//    - Move l to the right until we see another odd => it satisfy the condition of "minimum window that has k odd num"
//    - Need to keep the index of prev => (l - prev) will be the number of subarray with k odd num that ends at r (Because for the window prev->r, it will have (k + 1) odd num)
// But this solution will be hard to implement and update indexes.

// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int count = 0, prev = -1;
        int l = 0, res = 0;

        for (int r = 0; r < n; r++) {
            if (isOdd(nums[r])) {
                count++;
                if (count > k) {
                    prev = l;
                    count--;
                    l++;
                }
            }
            // We need to move the left 1-by-1 until we reach an odd number
            // We can save this effort by saving the indexes of odd number into a queue, in Solution 3
            while (count == k && !isOdd(nums[l])) {
                l++;
            }
            if (count == k) {
                res += l - prev;   
            }
        }
        return res;
    }

    private boolean isOdd(int v) {
        return v % 2 == 1;
    }
}
