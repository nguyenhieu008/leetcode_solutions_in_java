// https://leetcode.com/problems/construct-quad-tree/description/

// To build each node, we build 4 children of it. After that, check if the 4 children are leaf nodes with same values => create/return new leaf node with that value.
// One trick to implement easier is to construct with length of the edges, rather than the specific coordinations for bottom point.
// Time complexity: O(n^2), where n is the length of 1 side of the grid, because we reach each single grid once.
// Space complexity: O(logn) for the stack.

class Solution {
    public Node construct(int[][] grid) {
        return construct(grid, 0, grid.length - 1, 0, grid.length - 1);
    }

    private Node construct(int[][] grid, int top, int bottom, int left, int right) {
        if (top == bottom && left == right) {
            return new Node(grid[top][left] == 1, true);
        }

        int midHeight = top + (bottom - top) / 2;
        int midWidth = left + (right - left) / 2;
        Node topLeft = construct(grid, top, midHeight, left, midWidth);
        Node topRight = construct(grid, top, midHeight, midWidth + 1, right);
        Node bottomLeft = construct(grid, midHeight + 1, bottom, left, midWidth);
        Node bottomRight = construct(grid, midHeight + 1, bottom, midWidth + 1, right);

        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topLeft.val == bottomLeft.val && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }
}
