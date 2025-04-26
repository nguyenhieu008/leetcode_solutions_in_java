// https://leetcode.com/problems/count-subarrays-with-fixed-bounds/description/

// Solution 1: self-done. We calculate number of minK and maxK to know how to shrink the left, and number of possible starting positions for the sliding window end at right (gap).
// Detail in comment;
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public long countSubarrays(int[] a, int minK, int maxK) {
        /* check if minK <= maxK
        // left, right
        // if (a[right] > maxK || a[right] < minK) left = right, gap = 0, countMin = 0, countMax = 0
        // if (a[right] == minK) => countMin++;
        //    (          == maxK) => countMax++;
        // while (countMin > 1 && countMax > 1) {
                if a[left] == minK => countMin--
                if a[left] == maxK => countMax--
                left++
                gap++
            }
            res += gap

            beware about edge case, the return type is long
          */  

        if (minK > maxK) {
            // Impossible condition
            return 0;
        }

        int n = a.length;
        int countMin = 0, countMax = 0;
        // gap the number of possible starting positions for the window, where it includes both minK and maxK
        int gap = 0; 
        int left = 0;
        long res = 0;
        for (int right = 0; right < n; right++) {
            if (a[right] > maxK || a[right] < minK) {
                left = right + 1; // because this item already exceed range
                countMin = 0;
                countMax = 0;
                gap = 0;
            }
            if (a[right] == minK) countMin++; 
            if (a[right] == maxK) countMax++;

            // If countMin, countMax not both >= 1 => we are not increasing left => gap not increased
            // => result does not change
            while (countMin > 0 && countMax > 0) {
                // if minK == maxK => we decrease both => no problem.
                if (a[left] == minK) countMin--;
                if (a[left] == maxK) countMax--;
                left++;
                gap++;
            }
            res += gap;
        }
        return res;
    }
}

// Solution 2: reference: https://leetcode.com/problems/count-subarrays-with-fixed-bounds/solutions/2708099/java-c-python-sliding-window-with-explanation/
class Solution {
    public long countSubarrays(int[] a, int minK, int maxK) {
        if (minK > maxK) {
            // Impossible condition
            return 0;
        }

        int n = a.length;
        int minPos = -1, maxPos = -1, badPos = -1;
        long res = 0;
        for (int right = 0; right < n; right++) {
            if (a[right] > maxK || a[right] < minK) {
                badPos = right;
            }
            if (a[right] == minK) minPos = right; 
            if (a[right] == maxK) maxPos = right;

            res += Math.max(0, Math.min(minPos, maxPos) - badPos);
        }
        return res;
    }
}
