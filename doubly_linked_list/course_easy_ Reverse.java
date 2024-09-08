// https://axonenterprise.udemy.com/course/data-structures-and-algorithms-java/learn/quiz/5819194#notes
/* Implement a method called reverse() that reverses the order of the nodes in the list.

This method should reverse the order of the nodes in the list by manipulating the pointers of each node, not by swapping the values within the nodes.


Method Signature:

public void reverse()


Output:

No explicit output is returned. However, the method should modify the doubly linked list such that the order of the nodes is reversed.


Constraints:

The doubly linked list may be empty or have one or more nodes.


Example:

Consider the following doubly linked list:

Head: 1
Tail: 5
Length: 5
 
Doubly Linked List:
1 <-> 2 <-> 3 <-> 4 <-> 5


After calling reverse(), the list should be:

Head: 5
Tail: 1
Length: 5
 
Doubly Linked List:
5 <-> 4 <-> 3 <-> 2 <-> 1

*/

/* Solution 1: Try resursion */
private void reverse(Node cur) {
        if (cur == null) {
            return;
        }
        Node after = cur.next;
        cur.next = cur.prev;
        cur.prev = after;
        
        reverse(after);
    }
    
    public void reverse() {
        reverse(head);
        
        Node temp = head;
        head = tail;
        tail = temp;
    }
}

/* Solution 2: Iterative, at each node, modify its previous and next pointers then move to next one by cur = cur.prev (because the pointers have been swapped)
- Remember to swap head and tail.
  */
public void reverse() {
        Node cur = head;
        
        while (cur != null) {
            Node before = cur.prev;
            cur.prev = cur.next;
            cur.next = before;
            cur = cur.prev;
        }
        
        Node temp = head;
        head = tail;
        tail = temp;
    }
