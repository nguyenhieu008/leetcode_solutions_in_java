// https://leetcode.com/problems/partition-list/description/

/* Solution 1:
- We keep track the end of partition 1, and the start of partition 2, by running a loop until found the first one that >= x.
- After that, we traverse the list from the next item of the start-of-partition-2. If we find a value < x, we move it to the end-of-p1 and concat with the start-of-p2.
- Otherwise, we move forward. This way, we can keep the original order of each partition.
- One edge case is that the end-of-p1 is null, we need to handle the head pointer.
- Time complexity: O(n)
- Space complexity: O(1)
*/
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode endLess = null;
        ListNode startGreater = head;
        
        while (startGreater != null && startGreater.val < x) {
            endLess = startGreater;
            startGreater = startGreater.next;
        }
        
        if (startGreater == null) {
            return head;
        }
        
        ListNode prev = startGreater;
        ListNode temp = startGreater.next;
        
        while (temp != null) {
            if (temp.val >= x) {
                prev = temp;
                temp = temp.next;
            } else {
                ListNode after = temp.next;
                
                if (endLess == null) {
                    head = temp;
                } else {
                    endLess.next = temp;   
                }
                endLess = temp;
                temp.next = startGreater;
                
                prev.next = after;
                temp = after;
            }
        }
        return head;
    }
}

/* Solution 2: 
- We make 2 dummy nodes as start of 2 partitions (s1, s2). And we also keep track the end of 2 partitions (e1, e2), initilized to the dummy start nodes.
- We loop through the array, if found a less value => stitch to e1, otherwise, stitch to e2. Then update the corresponding end of partition.
- After all, we need to clean up by wiring:
  - 1. Points e2.next to null, because e2 not always is at the end of original list.
  - 2. Points e1.next to s2.start, it's okay even if the e1 is still at the dummy start node.
  - 3. Points head to s1.next. It must be done after 2., otherwise there is an edge case, where the partition 1 is empty and head will point to null, even if the partition 2 has items.

- Time complexity: O(n)
- Space complexity: O(1);
*/

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode startP1 = new ListNode();
        ListNode startP2 = new ListNode();
        ListNode endP1 = startP1;
        ListNode endP2 = startP2;

        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                endP1.next = cur;
                endP1 = cur;
            } else {
                endP2.next = cur;
                endP2 = cur;
            }
            cur = cur.next;
        }

        endP1.next = startP2.next;
        head = startP1.next;
        endP2.next = null;
        return head;
    }
}
