// https://leetcode.com/problems/longest-mountain-in-array/description/

// Solution 1: self-done. From index i, we go to 2 directions to find the possible length.
// Because if one point is within the edge of the mountain, it will no longer be possible to be the peak of the mountain
// => we never expand multiple times through 1 specific index
// => Time complexity: O(n)
// Space complexity: O(1)
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        int res = 0;

        for (int i = 1; i < n - 1; i++) {
            res = Math.max(res, getMountainLength(arr, i));
        }
        return res;
    }
    private int getMountainLength(int[] a, int i) {
        int left = i, right = i;
        if (a[i] <= a[i-1] || a[i] <= a[i+1]) {
            return 0;
        }

        while (left > 0 && a[left - 1] < a[left]) {
            left--;
        }
        while (right < a.length - 1 && a[right + 1] < a[right]) {
            right++;
        }
        return right - left + 1;
    }
}

// Solution 3: Try 2 pointters start, end. Detail in comment
class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        
        int start = 0, end = 0;
        int res = 0;
        // start must < n - 1 to make sure the loop can end
        while (start < n - 1) {
            end = start;
            if (end + 1 < n && arr[end + 1] > arr[end]) {
                while (end + 1 < n && arr[end + 1] > arr[end]) {
                    // increasing slope
                    end++;
                }

                if (end + 1 < n && arr[end + 1] < arr[end]) {
                    // now it really forms a mountain
                    while (end + 1 < n && arr[end + 1] < arr[end]) {
                        end++;
                        res = Math.max(res, end - start + 1);
                    }
                }
            }
            // beware the case where end cannot move any positions
            start = Math.max(start + 1, end);
        }
        return res;
    }
}

// Solution 2: good intuition, but should not use in interview as it's hard to understand
// reference: https://leetcode.com/problems/longest-mountain-in-array/solutions/135593/c-java-python-1-pass-and-o-1-space/

class Solution {
    public int longestMountain(int[] arr) {
        int n = arr.length;
        
        int res = 0;
        int up = 0, down = 0;
        for (int i = 1; i < n; i++) {
            if ((down > 0 && arr[i - 1] < arr[i]) || arr[i - 1] == arr[i]) {
                up = down = 0;
            }
            if (arr[i - 1] < arr[i]) {
                up++;
            }
            if (arr[i - 1] > arr[i]) {
                down++;
            }
            if (up > 0 && down > 0) {
                res = Math.max(res, up + down + 1);
            }
        }
        return res;
    }
}
