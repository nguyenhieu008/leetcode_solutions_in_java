// https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

// Solution 3: BFS - level order traversal. But we do not store nodes in queues.
// Instead, we store the head of the next level => very intelligent idea, because we have the next node pointer => we can travel in the same level without queue. 
// Assume that the current level has all the next nodes constructed (satisfied for first row)
// => looping through the next nodes, we can build the chain of next nodes for the next level.
// => looping until the next level has no item.
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public Node connect(Node root) {
        Node cur = root;
        while (cur != null) {
            Node startLevel = new Node(0);
            Node levelIterator = startLevel;

            while (cur != null) {
                if (cur.left != null) {
                    levelIterator.next = cur.left;
                    levelIterator = cur.left;
                }
                if (cur.right != null) {
                    levelIterator.next = cur.right;
                    levelIterator = cur.right;
                }
                cur = cur.next;
            }
            
            cur = startLevel.next;
        }
        return root;
    }
}

// Solution 2: DFS(node, next node)
// we must find the next possible child for the right node. We do that by loop through every possible next nodes and find the first child. Next child could also be null
// After found the first child, call dfs(node.right, nextChild)
// If Node.right = null => pass dfs(node.left, nextChild). Otherwise, call dfs(node.left, node.right)
// Time complexity: O(n), each node is visted at most 2 * n (with the loop for find next child)
// Space complexity: O(1), the stack is not counted for the space as stated in the problem.

class Solution {
    public Node connect(Node root) {
        return construct(root, null);
    }

    private Node construct(Node node, Node nextNode) {
        if (node == null) {
            return null;
        }

        node.next = nextNode;
        Node nextChild = null;
        while (nextNode != null) {
            if (nextNode.left != null) {
                nextChild = nextNode.left;
                break;
            } else if (nextNode.right != null) {
                nextChild = nextNode.right;
                break;
            }
            nextNode = nextNode.next;
        }

        construct(node.right, nextChild);
        if (node.right == null) {
            construct(node.left, nextChild);
        } else {
            construct(node.left, node.right);
        }
        return node;
    }
}



// Solution 1: BFS. Use null to separate levels. search from right to left, then next of current node is the previous node the in queue.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            // handle edge case
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        Node prevNode = null;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                if (queue.isEmpty()) {
                    break;
                }
                queue.offer(null); // only offer null when popped the null => No way there are 2 nulls in queue
                prevNode = null;
                continue;
            }
            
            node.next = prevNode;

            if (node.right != null) {
                queue.offer(node.right);
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            prevNode = node;
        }
        return root;
    }
}
