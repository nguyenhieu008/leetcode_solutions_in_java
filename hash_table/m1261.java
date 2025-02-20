// https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/

// Solution 1: use hash table to store existence of a value => find O(1) and easy code
// Space complexity: O(n). We store additional data structure to reduce time complexity

class FindElements {

    private TreeNode root;
    private HashSet<Integer> hash = new HashSet<>();

    public FindElements(TreeNode root) {
        this.root = root;
        fixNode(root, 0);
    }

    private void fixNode(TreeNode node, int value) {
        if (node == null) {
            return;
        }
        node.val = value;
        hash.add(value);
        fixNode(node.left, value * 2 + 1);
        fixNode(node.right, value * 2 + 2);
    }
    
    public boolean find(int target) {
        return hash.contains(target);
    }
}

// Solution 2: devide the target until we reach root node, store the even or odd => we can go back to find the target node. 
// Time complexity: O(log(n)), max O(20) by constraint
// Space complexity: O(1). No need additional storage.

class FindElements {

    private TreeNode root;

    public FindElements(TreeNode root) {
        this.root = root;
        fixNode(root, 0);
    }

    private void fixNode(TreeNode node, int value) {
        if (node == null) {
            return;
        }
        node.val = value;
        fixNode(node.left, value * 2 + 1);
        fixNode(node.right, value * 2 + 2);
    }
    
    public boolean find(int target) {
        if (target == 0) return true;
        if (target < 0) return false;

        // tempVal       i           even[]
        //   5          0           [false]
        //   2          1            [false, true]
        //   0          2



        boolean[] even = new boolean[20];
        int i = 0;
        int tempVal = target;
        while (tempVal != 0) {
            if (tempVal % 2 == 0) {
                even[i] = true;
                tempVal = (tempVal - 2) / 2;
            } else {
                even[i] = false;
                tempVal = (tempVal - 1) / 2;
            }
            i++;
        }
        
        i--;
        TreeNode cur = root;

        //   i = 1, even[] = [false, true]

        //   i          even[i]         cur       cur.left      cur.right
        //   1              true         0                          2
        //   0              false        2          

        // return false

        while (i >= 0) {
            if (even[i] == true && cur.right != null) {
                cur = cur.right;
            } else if (even[i] == false && cur.left != null) {
                cur = cur.left;
            } else {
                break;
            }
            i--;
        }
        return i < 0;
    }
}
