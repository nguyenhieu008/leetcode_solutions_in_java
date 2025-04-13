// https://leetcode.com/problems/reverse-linked-list-ii/description/

// Solution: self-done. It's like so:
// 1 -> 2 -> 3 -> 4 -> 5 , left = 2, right = 4
// 1 (before) -> 2 (oldStart, prevNode) (-> 1)   (nextNode) 3 -> 4 -> 5
// 1 (before) -> 2 (oldStart) <- (prevNode) 3    (nextNode) 4 -> 5
// 1 (before) -> 2 (oldStart) <- 3  <- (prevNode, oldEnd) 4   (after, nextNode) 5
// 1 (before) -> (oldEnd) 4 -> 3 -> 2 (oldStart) -> (after) 5
// => reverse in-place
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0, head);
        ListNode node = dummyHead;
        for (int i = 1; i < left; i++) {
            node = node.next;
        }

        ListNode before = node, oldStart = node.next;
        ListNode prevNode = before, nextNode = oldStart;
        for (int i = left; i <= right; i++) {
            node = nextNode;
            nextNode = node.next;
            node.next = prevNode;
            prevNode = node;
        }
        ListNode oldEnd = prevNode, after = nextNode;
        oldStart.next = after;
        before.next = oldEnd;
        return dummyHead.next;
    }
}

// Solution 2: reference: https://leetcode.com/problems/reverse-linked-list-ii/solutions/2311084/java-c-tried-to-explain-every-step/
// // 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
    // dummy-> 1 -> 2 -> 3 -> 4 -> 5
    // first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
    // second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5 (finish)
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0); // created dummy node
        dummy.next = head;
        ListNode prev = dummy; // intialising prev pointer on dummy node
        
        for(int i = 0; i < left - 1; i++)
            prev = prev.next; // adjusting the prev pointer on it's actual index
        
        ListNode curr = prev.next; // curr pointer will be just after prev
        // reversing
        for(int i = 0; i < right - left; i++){
            ListNode forw = curr.next; // forw pointer will be after curr
            curr.next = forw.next;
            forw.next = prev.next;
            prev.next = forw;
        }
        return dummy.next;
    }
}
