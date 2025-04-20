// https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/

// Same as m105: https://github.com/nguyenhieu008/leetcode_solutions_in_java/blob/main/tree/m105_ConstructBinaryTreefromPreorderandInorderTraversal.java
// Only 1 notice that, we must build the children from right -> left. Otherwise, we will get runtime error.

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        HashMap<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idxMap.put(inorder[i], i);
        }
        Deque<Integer> postorderDeque = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            postorderDeque.offer(postorder[i]);
        }
        return build(idxMap, postorderDeque, 0, n - 1);
    }
    private TreeNode build(Map<Integer, Integer> idxMap, Deque<Integer> postorderDeque, int left, int right) {
        if (left > right) {
            return null;
        }  
        int val = postorderDeque.poll();
        int targetIdx = idxMap.get(val);

        TreeNode root = new TreeNode(val);
        root.right = build(idxMap, postorderDeque, targetIdx + 1, right);
        root.left = build(idxMap, postorderDeque, left, targetIdx - 1);

        return root;
    }
}
