public class SortList {
    /*
            Best Approach: merge sort
    */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, slow = head, fast = head;

        // find the middle node 1->2->3->4->5->6
        //                            ^  ^     ^
        //                            p  s     f
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // divide

        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        return merge(left, right);
    }
    private ListNode merge(ListNode left, ListNode right) {
        if (left == null) return right;
        if (right == null) return left;

        if (right.val < left.val) {
            right.next = merge(right.next, left);
            return right;
        } else {
            left.next = merge(left.next, right);
            return left;
        }
    }
}
