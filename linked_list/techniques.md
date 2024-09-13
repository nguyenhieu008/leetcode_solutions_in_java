TECHNIQUES

1. Use dummy head: create a new dummy node called "dummy", that dummy.next = head
=> Use a for loop with temp variable from this dummy node, until temp != null, => temp will never have null value.
=> After done working, we can set head = dummy.next => head can get updated if the node order is updated. E.g. the original head node is moved to middle of list and the dummy now points to a new head node.
- And also, because in linked list we need to be at the "previous" node to handle the target node (relink, delete, add new node, etc.). This way, we always have a node before the target node,
event if the target node is head. So we won't need to make an edge case checking for head.

- Additionally, we can also make 2 dummy heads acting as 2 starting nodes for 2 separate lists, rearraged from the original list. e.g. create 2 lists, 1 list containing all odd values,
the other containing all even values. Then we also can link them together to make a final result list.

2. Use two pointers, called slow - fast pointers:
- Fast can go with twice the speed of slow pointers. So, if they meet, the linked list has a cycle. At that time, set slow to the head, fast at the meeting node, then move both with same speed.
Their next meeting node is the start node of the cycle.
- Another technique with slow - fast pointers is:
  - Fast pointer moves first until reach some condition
  - Then move them with same speed. So, when fast pointer reaches the end of the list, slow pointer also reaches a target node at the same time.
  - This technique can be used to get the node with distance k from the end of the list, with 1 for loop only.

3. Recursion, dynamic programming.
- We can think of problems on linked list in resursive approach.
- For example:
  - reverse a list of N pointers is link the last node to the start of a list with (N - 1) nodes that has been reversed already. Then do it from small to large (dynamic programming)
  - Do some thing with cur node, then call the recursion with the next node.
