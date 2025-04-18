// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

// Solution: when divided by 2, two sides of the array differs in size by 1 
// => if both are balanced => the final tree when added to the mid node item will also be balanced.
// Time complexity: O(n), cause we create each node once.
// Space complexity: O(logn), for stack

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start], null, null);
        }

        int mid = start + (end - start) / 2;

        TreeNode root = construct(nums, mid, mid);
        root.left = construct(nums, start, mid - 1);
        root.right = construct(nums, mid + 1, end);

        return root;
    }
}
