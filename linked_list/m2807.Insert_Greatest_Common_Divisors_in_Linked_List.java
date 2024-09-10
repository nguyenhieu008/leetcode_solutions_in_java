// https://leetcode.com/problems/insert-greatest-common-divisors-in-linked-list/description/?envType=daily-question&envId=2024-09-10

/* Solution: Nothing special. Find gcd using resursion and push them between 2 node.
- Notice to move 2 nodes after insertion
- Time complexity: O(n * log(m))
- Space complexity: O(log(m))
*/
class Solution {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode cur = head;
        while (cur.next != null) {
            int value = gcd(cur.val, cur.next.val);
            ListNode temp = new ListNode(value, cur.next);
            cur.next = temp;
            cur = cur.next.next;
        }
        return head;
    }
}
