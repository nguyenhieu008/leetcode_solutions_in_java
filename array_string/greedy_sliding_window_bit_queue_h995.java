// https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/description/

// Solution 2: Same as solution 1, but we use queue to store the indexes.
// There is one solution where we update the array in-place and restore it after the windows passes => constant space => but i will not try it
// Time complexity: O(n)
// Space complexity: O(k)

class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        Queue<Integer> flips = new LinkedList<>();
        int leftFlip = 0, res = 0;

        for (int l = 0, r = k - 1; l < n; l++, r++) {
            if ((nums[l] + flips.size()) % 2 == 0) {
                if (r >= n) {
                    return -1;
                }
                res++;
                leftFlip++;
                flips.offer(r);
            }
            if (!flips.isEmpty() && flips.peek() == l) {
                flips.poll();
                leftFlip--;
            }
        }
        return res;
    }
}

// Solution 1: same as problem https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/description/
// We flip from left to right, but should not try to flip all k bits because time complexity will be O(n * k)
// We notice that for window (l, r) if we flip it 1, then all items in that window will be flipped 1 time
// So we keep an int leftFlip to count number of flipped applied to the next left item. If we flip the window (l, r), we increment leftFlip and also mark flipped[r] as true.
// Later, when the left side of window pass an index where flipped[l] = true => we decrement the leftFlip
// => For each new index, we can know that whether it needs to be flipped with O(1)
// THE TRICK here to to keep 2 pointers l and r => we do not need to deal with complex handling of (i and k) 

// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        boolean[] flipped = new boolean[n];
        int leftFlip = 0, res = 0;

        for (int l = 0, r = k - 1; l < n; l++, r++) {
            if ((nums[l] + leftFlip) % 2 == 0) {
                if (r >= n) {
                    return -1;
                }
                res++;
                leftFlip++;
                flipped[r] = true;
            }
            if (flipped[l]) {
                leftFlip--;
            }
        }
        return res;
    }
}
