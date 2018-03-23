public class MergeTwoSortedList {
    /*
        sorted result not guaranteed
        follow 1212 sequence
    */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode dummy = new ListNode(0);
        dummy.next = l1;
        ListNode head1 = l1;
        ListNode head2 = l2;

        while (head2 != null) {
            ListNode next1 = head1.next;
            head1.next = head2;

            if (next1 == null && head2.next != null) return dummy.next;

            ListNode next2 = head2.next;
            head2.next = next1;
            head1 = next1;
            head2 = next2;
        }
        return dummy.next;
    }

    /*
        Iterative Solution
        sorted result guaranteed!
        compare the val of two lists each time
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;

        ListNode dummy = new ListNode(0);
        dummy.next = l1.val > l2.val ? l2 : l1;
        // find small and set dummy.next
        ListNode pre = dummy;

        ListNode head1 = l1;
        ListNode head2 = l2;

        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                while (head2 != null && head1.val > head2.val && pre.next == head2) {
                    head2 = head2.next;
                    pre = pre.next;
                } // before interleaving

                pre.next = head1;
            }
            else {
                while (head1 != null && head1.val < head2.val && pre.next == head1) {
                    head1 = head1.next;
                    pre = pre.next;
                } // before interleaving

                pre.next = head2;
            }
        }
        return dummy.next;
    }

    /*
        Recursive solution
    */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        // base case

        if (l1.val > l2.val) {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
    }
}
