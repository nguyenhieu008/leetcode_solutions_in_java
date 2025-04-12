// https://leetcode.com/problems/copy-list-with-random-pointer/description/

// Solution 1: Use a hash map to store the new copied nodes.
// Then make another pass to rewire the random pointers of copied nodes.
// Time complexity: O(n)
// Space complexity: O(n)

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> nodeMap = new HashMap<>();
        Node dummyHead = new Node(0);
        Node iter = dummyHead, origIter = head;

        while (origIter != null) {
            Node newNode = new Node(origIter.val);
            iter.next = newNode;
            iter = iter.next;

            nodeMap.put(origIter, iter);
            origIter = origIter.next;
        }

        origIter = head;
        while (origIter != null) {
            Node copiedNode = nodeMap.get(origIter);
            copiedNode.random = nodeMap.get(origIter.random);
            origIter = origIter.next;
        }
        return dummyHead.next;
    }
}

// Solution 2: We put the new node into the original linked list, right after the original node
// => We can make another pass to point the random pointer based on the original node.
// After that, we need to make another pass to separate the two lists.
// Time complexity: O(n)
// Space complexity: O(1)

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node curNode = head;
        while (curNode != null) {
            Node newNode = new Node(curNode.val);
            newNode.next = curNode.next;
            curNode.next = newNode;
            curNode = newNode.next;
        }

        curNode = head;
        while (curNode != null) {
            if (curNode.random != null) {
                curNode.next.random = curNode.random.next;
            }
            curNode = curNode.next.next;
        }

        Node newHead = head.next;
        curNode = head;
        Node curNewNode = newHead;
        while (curNode != null) {
            curNode.next = curNewNode.next;
            if (curNewNode.next != null) {
                curNewNode.next = curNewNode.next.next;
            }
            curNode = curNode.next;
            curNewNode = curNewNode.next;
        }
        return newHead;
    }
}
