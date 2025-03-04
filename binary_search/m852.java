// https://leetcode.com/problems/peak-index-in-a-mountain-array/

// Solution: For 2 adjacent items, we always have greater one. So, we should proceed with the greater part. Because the peak is always the greatest one.

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0, r = arr.length - 1;

        while (r - l > 0) {
            int mid = l + (r - l) / 2;

            if (arr[mid] < arr[mid + 1]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
