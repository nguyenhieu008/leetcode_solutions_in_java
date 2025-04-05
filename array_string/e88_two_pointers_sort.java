// https://leetcode.com/problems/merge-sorted-array/description/

// We can go from backward in nums1 => no overlap (confirmed using paper: 0 <= i < m, 0 <= j < n => expected index of an item in merged array will be (i + j) >= i => no overlap)
// Time complexity: O(m + n);
// Space complexity: O(1)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int sortedIdx = m + n - 1;
        int i = m - 1, j = n - 1;
        
        while (i >= 0 && j >= 0) {
            // Use ternary operator so the i and j can be updated only when it's selected for the merged array, then update the sortedIdx as well
            nums1[sortedIdx--] = nums1[i] >= nums2[j] ? nums1[i--] : nums2[j--];
        }
        // If there are something left in nums1, they are already in sorted order => do nothing
        while (j >= 0) {
            nums1[sortedIdx--] = nums2[j--];
        }
    }
}
