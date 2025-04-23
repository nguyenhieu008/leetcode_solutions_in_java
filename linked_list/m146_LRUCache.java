// https://leetcode.com/problems/lru-cache/description/

// One trick here is to use 2 dummy head and tail, so no need to deal with null pointer.
// And need to generalize to functions for easier implementation.

class LRUCache {
    // get/put O(1) => definitely need a hash map
    // Need to evict based on the least recently used => need a way to update the key to know that it's recently used
    // => Use kind of LIFO structure (queue), when update a key, we need to re-put it to the back of the queue
    // => Need the to access/remove the item inside the queue => Use built-in queue not okay.
    // Use array list not okay, because remove middle item takes O(n). built-in linked list does not provide random access.
    // => Build a custome singly linked list. But when rewire, we need to know the previous one => use doubly linked list

    public static class Node {
        int val;
        int key;
        Node next;
        Node prev;

        Node(int val, int key, Node next, Node prev) {
            this.val = val;
            this.key = key;
            this.next = next;
            this.prev = prev;
        }
    }

    Node head, tail;
    HashMap<Integer, Node> map;
    int capacity;
    int size;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        // The key must not be in range
        head = new Node(0, -1, null, null);
        tail = new Node(0, -2, null, null);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        // get the node out from current linked list, then add to head
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }
        
        // no need to remove node from map
        addToHead(node);
        return node.val;
    }
    
    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            // update the value and put to head as recently used
            node.val = value;
            addToHead(node);
        } else {
            // always add new node to map
            Node newNode = new Node(value, key, null, null);
            map.put(key, newNode);
            
            if (size < capacity) {
                addToHead(newNode);
                size++;
            } else {
                Node lastNode = tail.prev;

                // remove last element and put new item. No need to update size
                // need to remove from map as well.
                map.remove(lastNode.key);
                
                lastNode.prev.next = tail;
                tail.prev = lastNode.prev;
                addToHead(newNode);
            }
        }
    }

    // When get the node, we lift it up from the linked list
    private Node getNode(int key) {
        Node node = map.get(key);
        if (node != null) {
            Node before = node.prev;
            Node after = node.next;

            before.next = after;
            after.prev = before;
        }
        return node;
    }

    private void addToHead(Node node) {
        Node oldFirst = head.next;
        
        node.prev = head;
        node.next = oldFirst;
        oldFirst.prev = node;
        head.next = node;
    }
}
