// https://leetcode.com/problems/binary-search-tree-iterator/description/

class BSTIterator {
    Stack<TreeNode> inorder = new Stack<>();

    public BSTIterator(TreeNode root) {
        addMinPathToStack(root);
    }
    
    public int next() {
        // It's guaranteed the next() call will always be valid
        TreeNode minNode = inorder.pop();
        // Because we already check for null before add to stack, we do not need to check null here
        addMinPathToStack(minNode.right);
        return minNode.val;
    }

    private void addMinPathToStack(TreeNode root) {
        TreeNode node = root;
        
        while (node != null) {
            inorder.push(node);
            node = node.left;
        }
    }
    
    public boolean hasNext() {
        return !inorder.isEmpty();
    }
}
