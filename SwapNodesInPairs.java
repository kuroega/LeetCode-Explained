public class SwapNodesInPairs {
    /*
        Recursive Solution
    */
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode tmp = head.next;
        head.next = swapPairs(head.next.next);
        tmp.next = head;
        return tmp;
    }

    /*
        Iterative Solution
    */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;

        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;

            current.next = second;
            first.next = second.next;
            second.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
}
