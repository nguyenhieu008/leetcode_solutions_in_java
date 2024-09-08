// https://leetcode.com/problems/reverse-linked-list-ii/

/* Solution 1: Iterative way
- We can try to reverse the segment by iterative way (or in-place way). At each node, we link current -> prev node. 
- But because we will lost the after-node-of-current, so we must store the after-node before re-link them.
- After that, we change the before node to current, current to after. At this time, prev and cur do not link together
- e.g. 1 -> (2 -> 3 -> 4) -> 5 . After first run will be: 1 -> (2 <- 3(prev)   4(cur)) -> 5
- After second run will be: 1 -> (2 <- 3 <- 4 (prev)) 5 (cur)
- After reverse all the segment, we must link the node before the segment to the prev node : 1 -> 4. And the first item of the segment to cur: (2 -> 5). That's why, we must store them in advance.
- Time complexity: O(n)
- Space complexity: O(1)
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode beforeSegment = null;
        ListNode cur = head; 
        for (int i = 1; i < left; i++) {
            beforeSegment = cur;
            cur = cur.next;
        }
        
        ListNode endSegment = cur;
        ListNode prev = cur;
        cur = cur.next;
        
        for (int i = left + 1; i <= right; i++) {
            ListNode after = cur.next;
            
            cur.next = prev;
            prev = cur;
            cur = after;
        }
        
        if (beforeSegment == null) {
            head = prev;
        } else {
            beforeSegment.next = prev;
        }
        
        endSegment.next = cur;

        return head;
    }
}

/* Solution 2: Same as solution 1 but use dummy node to simplify the case that we need to reverse segment started from the head.
- So we will always have a node that stays before the segment.
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode beforeSegment = dummy;
        for (int i = 1; i < left; i++) {
            beforeSegment = beforeSegment.next;
        }
        
        ListNode endSegment = beforeSegment.next;
        ListNode prev = endSegment;
        ListNode cur = prev.next;
        
        for (int i = 0; i < (right - left); i++) {
            ListNode after = cur.next;
            
            cur.next = prev;
            prev = cur;
            cur = after;
        }
        
        beforeSegment.next = prev;
        endSegment.next = cur;

        head = dummy.next;
        return head;
    }
}

/* Solution 3: We can think of it the recursive way.
- If we need to reverse n node segment, what if we already have a (n - 1) nodes reversed then do it for n nodes.
- e.g. A -> (n - 1) nodes -> Node N -> B
- What to do is: 
  - Link the end of segment (n - 1) to Node B, which is the N.next.
  - Link Node N to the start of segment (n - 1), in that case, A.next.
  - Link node A (before segment) to Node N
  - Then we do it recursively until we get to n = 1.
- Because with linked list, we cannot have the whole segment from beginning, so we do it in reverse way, that means we try dynamic programming. 
- We try to detect the node A first, then set tail is the A.next and Node N is the tail.next. Then do it until we reach the end of the segment.
- Time complexity: O(n)
- Space complexity: O(1)
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }

        ListNode tail = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode after = tail.next;

            tail.next = after.next;
            after.next = prev.next;
            prev.next = after;
        }

        head = dummy.next;
        return head;
    }
}

/* Solution 4: Resursive
- We pass 2 nodes into the resursive function, called prev and cur
- Store the next of cur, then link cur to prev. Then pass cur and next to the recursive function again, minus the index by 1, until we reach 0.
- At that time, return the prev and cur. prev will be the start of reverse segment, while the cur will be the node after the new tail of segment.
- Time complexity: O(n)
- Space complexity: O(n) for the stack of recursive functions
*/
class Solution {
    public static class MyResult {
        ListNode curNode;
        ListNode nextNode;

        public MyResult(ListNode cur, ListNode nxt) {
            curNode = cur;
            nextNode = nxt;
        }
    }
    public MyResult reverseNodes(int i, ListNode prevNode, ListNode curNode) {
        if (i == 0) {
            return new MyResult(prevNode, curNode);
        }

        ListNode after = curNode.next;
        curNode.next = prevNode;
        return reverseNodes(i - 1, curNode, after);
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 1; i < left; i++) {
            prev = prev.next;
        }
        ListNode tail = prev.next;

        MyResult res = reverseNodes(right - left, tail, tail.next);
        prev.next = res.curNode;
        tail.next = res.nextNode;
        
        head = dummy.next;
        return head;
    }
}

// https://leetcode.com/problems/reverse-linked-list/description/
/* Easy version of previous problem. Also apply iterative and recursive approach. Note that the iterative approach is easier to implement in this case.
  */
// Dynamic programming approach
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode tail = head;
        

        while (tail.next != null) {
            ListNode cur = tail.next;

            tail.next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
        }

        head = dummy.next;
        return head;
    }
}

// Iterative solution
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode after = cur.next;
            cur.next = prev;
            prev = cur;
            cur = after;
        }
        return prev;
    }
}
