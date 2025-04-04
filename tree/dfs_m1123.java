// https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves

// Solution 1a: Same as solution 1, but handle edge cases (leave node, null node) better => shorter code
// ONE NOTICE is that we need to care about the return type when analyse the problem (because I already return wrong type)

// Time complexity: O(n)
// Space complexity: O(h), for dfs stack

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public static class Result {
        int h;
        TreeNode node;

        Result(int h, TreeNode node) {
            this.h = h;
            this.node = node;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Result res = dfs(root, 0);
        System.out.println("result: " + res.node.val);
        return res.node;
    }

    private Result dfs(TreeNode node, int h) {
        if (node == null) {
            // In fact, the height here will be 1 more than the leaf node
            // But because we do not return height as the final result
            // It's okay just to compare height of leaves.
            return new Result(h, node);
        }
        
        // Event if both the children are null, we will always have these 2 result (not null). Instead, the inside node is null
        // But with the if condition, we guarantee it's not null in the final result
        //      - If both are null => same height => return this node as result
        //      - If one of them is null => it's height is less than the other => return the other => not null
        Result result1 = dfs(node.left, h + 1);
        Result result2 = dfs(node.right, h + 1);

        if (result1.h == result2.h) {
            // Edge case, for a leaf node, both childs is null and return same height
            // => It go to this block and return itself as the returned node

            // And also, internal node with same branches' height will also go here
            return new Result(result1.h, node);
        } else if (result1.h > result2.h) {
            return result1;
        } else {
            return result2;
        }
    }
}

// Solution 1: real thought process, but the corner cases not handle well => longer code
// ONE NOTICE is that we need to care about the return type when analyse the problem (because I already return wrong type)

class Solution {
    public static class Result {
        int h;
        TreeNode node;

        Result(int h, TreeNode node) {
            this.h = h;
            this.node = node;
        }
    }
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Nodes values unique => can represent a node by its value
        // nums nodes = [1, 1000] => O(n^2) can work.
        // Problems: find LCA (lowest common ancestor)
        //  - deepest node (largest depth)
        //  - ancestor of all the deepest leaves
        // Output format?? In example, what is (2, 7, 4)??
        // e.g => 1 -> 2 -> 3
        //          -> 4 -> 5 (6, case 2)
        // => LCA = 1
        // => in case 2, LCA = 6 (LCA of itself)
        // Considering the problem, can we do it recursively?
        // Use DFS, return the depth of deepest leave => it's okay
        // But we need the LCA? what if we have 2 leaves with same depth?
        // => we need to return both the LCA in the subtree, and the depth of leave
        // let's go through example:
        // DFS(3, 0) => DFS(5, 1) => DFS(6, 2) return [[6], 2]
        //                        => DFS(2, 2) => DFS(7, 3) return [[7], 3]
        //                                     => DFS(4, 3) return [[4], 3]
        //                        => DFS(2, 2) return [2, 3] because 2 children return same depth
        // => DFS logic (node, h: Int):
        //      - If this is leave, return [[this.value], h]
        //      - res1 = DFS(this.left, h + 1),
        //      - res2 = DFS(this.right, h + 1)
        //      - if res1.h == res2.h => merge the arrays and return [merged, h]

        return dfs(root, 0).node;
    }

    private Result dfs(TreeNode node, int h) {
        if (node.left == null && node.right == null) {
            return new Result(h, node);
        }
        Result result1 = null, result2 = null;
        int h1 = h, h2 = h;
        if (node.left != null) {
            result1 = dfs(node.left, h + 1);
            h1 = result1.h;
        }
        if (node.right != null) {
            result2 = dfs(node.right, h + 1);
            h2 = result2.h;
        }

        if (h1 == h2) {
            return new Result(h1, node);
        } else if (h1 > h2) {
            return result1;
        } else {
            return result2;
        }
    }
}
