// https://leetcode.com/problems/rotate-list/description/

// Solution 2: Use right pointer to calculate the length => reach last node after loop, and keep it here to rewire later.
// Use left pointer to move until k-steps to end, then rewire here
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        int n = 0;
        ListNode dummyHead = new ListNode(0, head);
        ListNode right = dummyHead;
        while (right.next != null) {
            right = right.next;
            n++;
        }

        k = k % n;
        // Need to handle case k == 0
        // CAUTION: when coding, if we suspect something, should write down comments to remember
        if (k == 0) {
            return head;
        }


        ListNode left = dummyHead;
        for (int i = 0; i < n - k; i++) {
            left = left.next;
        }

        dummyHead.next = left.next;
        left.next = null;
        right.next = head;
        return dummyHead.next;
    }
}

// Solution 1: User an array list to save the nodes, and use it to rewire. 
// Space complexity: O(n);
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ArrayList<ListNode> nodes = new ArrayList<>();
        ListNode dummyHead = new ListNode(0, head);
        ListNode iter = dummyHead;
        while (iter.next != null) {
            iter = iter.next;
            nodes.add(iter);
        }

        int n = nodes.size();
        k = k % n;

        if (k == 0) {
            return head;
        }

        // rewire
        nodes.get(n - 1 - k).next = null;
        dummyHead.next = nodes.get(n - k);
        nodes.get(n - 1).next = head;
        return dummyHead.next;
    }
}

// Solution 3: reference: https://leetcode.com/problems/rotate-list/solutions/5233749/java-easy-to-understand-solution-with-explanation-100-beats/
// After reach end, make the linked list circular and rerun
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int length = 1;
        ListNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        temp.next = head;
        k = k % length;
        k = length - k;

        while (k-- > 0) {
            temp = temp.next;
        }

        head = temp.next;
        temp.next = null;

        return head;
    }
}
