/*
    LRUCache cache = new LRUCache( 2 <-> capacity);

    cache.put(1, 1);
    cache.put(2, 2);
    cache.get(1);       // returns 1
    cache.put(3, 3);    // evicts key 2
    cache.get(2);       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    cache.get(1);       // returns -1 (not found)
    cache.get(3);       // returns 3
    cache.get(4);       // returns 4
*/

public class LRUCache {
    private Map<Integer, DoubleLinkedListNode> cache;
    DoubleLinkedListNode head = null;
    DoubleLinkedListNode tail = null;
    int capacity;

    public LRUCache (int capacity) {
        cache = new HashMap<Integer, DoubleLinkedListNode>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DoubleLinkedListNode node = cache.get(key);
            int value = node.val;
            node.update(); // update the Linked list
            return value; // return value
        } else {
            return -1; // no such key
        }
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            DoubleLinkedListNode node = cache.get(key);
            node.val = value; // update the value for the key
            node.update();
        } else {
            if (capacity == 0) return;
            if (cache.size() == capacity) { // evicts least recent key
                cache.remove(head.key); // remove the key from cache
                head.removeFromHead(); // remove the node represents the key
            }
            DoubleLinkedListNode newNode = new DoubleLinkedListNode(key, value);
            newNode.append(); // append new node
            cache.put(key, newNode);
        }
    }

    class DoubleLinkedListNode {
        /*
            the structure is head points to the least recent node,
                             tail points to the most recent node.
            if there is no node, head <-> null, tail <-> null
            if there is only one node, head <-> node1, tail <-> node1
            otherwise, follow the definition of the structure
        */
        int key;
        int val;
        DoubleLinkedListNode prev = null;
        DoubleLinkedListNode next = null;

        public DoubleLinkedListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }

        private void append() {
            if (tail == null) { // insert first node
                head = this;
                tail = this;
            } else { // append later node: tail<->'this'->null
                this.next = null;
                this.prev = tail;
                tail.next = this;
                tail = this; // update tail's reference to the most recent node
            }
        }

        private void removeFromHead() {
            if (tail == this) { // means tail and head points to the same node
                head = null;    // and 'this' is the only node
                tail = null;
            } else { // 1<->2<->3<->4 => null<-2<->3<->4
                head = this.next;
                head.prev = null;
            }
        }

        private void update() {
            if (tail == this) { // already in the right position
                return;
            } else {
                if (head != this) { // in between: 1<->2<->'3'<->4 => 1<->2<->4<->'3'
                    this.prev.next = this.next;
                } else { // both sides: '1'<->2<->3<->4 => 2<->3<->4<->'1'
                    head = this.next;
                }
                this.next.prev = this.prev;
                this.append();
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
