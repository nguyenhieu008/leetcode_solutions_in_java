// https://leetcode.com/problems/merge-two-sorted-lists/description/
// Solution 1: Iterative

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode();
        ListNode i = dummyHead, i1 = list1, i2 = list2;

        while (i1 != null || i2 != null) {
            ListNode newNode = new ListNode();
            if (i1 == null) {
                newNode.val = i2.val;
                i2 = i2.next;
            } else if (i2 == null) {
                newNode.val = i1.val;
                i1 = i1.next;
            } else if (i1.val < i2.val) {
                newNode.val = i1.val;
                i1 = i1.next;
            } else {
                newNode.val = i2.val;
                i2 = i2.next;
            }

            i.next = newNode;
            i = i.next;
        }
        return dummyHead.next;
    }
}

// Solution 2: Recursion

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
}
