// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/

// Solution 2: use deque for easy handling preorder, find the targetidx with map => O(1)
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> mapIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            mapIdx.put(inorder[i], i);
        }
        Deque<Integer> preorderDeque = new ArrayDeque<>();
        for (int v : preorder) {
            preorderDeque.offer(v);
        }
        return build(preorderDeque, mapIdx, 0, inorder.length - 1);
    }

    private TreeNode build(Deque<Integer> preorderDeque, Map<Integer, Integer> idxMap, int left, int right) {
        if (left > right) {
            return null;
        }

        int val = preorderDeque.poll();
        int targetIdx = idxMap.get(val);

        TreeNode root = new TreeNode(val);
        root.left = build(preorderDeque, idxMap, left, targetIdx - 1);
        root.right = build(preorderDeque, idxMap, targetIdx + 1, right);
        return root;
    }
}

// Solution 1: loop through the preorder, the first one will be the root of the tree.
// Find that one in the inorder, call target => left side of target is left subtree, and the rest is right subtree.
// Continue to do so, when calling dfs, we pass [left, right] as the range in inorder array to build the tree.
// The preorderIdx is the index of the root node of that subtree to find. We need to calculate the preorderIdx carefully, detail in comment (preorderIdx + (targetIdx - left) + 1)
// Another easy way to deal with it is to use preorderIdx as a private class member => modifiable in recursed function
// Another trick is to use Deque to handle preorder => no need to maintain the preorderIdx
// Time complexity: O(n^2), as in worse case 1-line tree, we need to find the index in whole array.
// Space complexity: O(n) for call stack.

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preorderIdx, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(inorder[left]);
        } 

        int targetIdx = left;
        for (int i = left; i <= right; i++) {
            if (preorder[preorderIdx] == inorder[i]) {
                targetIdx = i;
                break;
            }
        }
        TreeNode root = new TreeNode(inorder[targetIdx]);
        root.left = build(preorder, inorder, preorderIdx + 1, left, targetIdx - 1);
        // targetIdx - left = number of nodes in left side.
        // preorderIdx + (targetIdx - left) + 1 = start index of right side.
        root.right = build(preorder, inorder, preorderIdx + (targetIdx - left) + 1, targetIdx + 1, right);
        return root;
    }
}
