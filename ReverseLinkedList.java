public class ReverseLinkedList {

    /*
        Reverse a linked list from position m to n. Do it in-place and in one-pass.

        For example:
        Given  1->2->3->4->5->NULL, m = 2 and n = 4,

        return 1->4->3->2->5->NULL.

        Given m, n satisfy the following condition:
            1 ≤ m ≤ n ≤ length of list.
    */

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = head;

        int distance = n - m;

        while (distance > 0) {
            fast = fast.next;
            distance--;
        } // fast goes first

        distance = m;
        while (distance > 1) {
            slow = slow.next;
            fast = fast.next;
            distance--;
        }
        ListNode pre = slow;
        slow = slow.next;

        while (slow != fast) { // keep insert
            ListNode next = slow.next;
            slow.next = fast.next;
            fast.next = slow;
            pre.next = next;
            slow = next;
        }

        return dummy.next;


    }
}
