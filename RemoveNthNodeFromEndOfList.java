/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class RemoveNthNodeFromEndOfList {
    /*
        Two Pass Solution
        Time: O(L)
        Space: O(1)
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode copy = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int len = 0;
        while (copy != null) {
            copy = copy.next;
            len++;
        } // first pass: calculate length

        int i = 0;
        copy = dummy;
        while (i < len - n) {
            copy = copy.next;
            i++;
        } // second pass: move to nth from the end
        copy.next = copy.next.next;
        // delete node

        return dummy.next;
    }

    /*
        One Pass Solution
    */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode First = dummy; // first pointer
        ListNode Second = dummy; // second pointer

        for (int i = 0; i < n + 1; i++) {
            First = First.next;
        } // move to (n + 1)th node: keep constant distant

        while (First != null) {
            First = First.next;
            Second = Second.next;
        } // move to nth from the end
        Second.next = Second.next.next; // delete node
        return dummy.next;
    }


}
