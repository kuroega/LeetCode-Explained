public class InsertionSortList {
    /*
        Use insertion sort to sort the linkedlist
            recap:
                what is insertion sort ?
                swap when next.val > prev.val
                keep swapping through 0~1 to 0~n-1
                (n is the length of the array/list)
    */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        int len = 0;
        ListNode iter = head;
        while (iter != null) {
            iter = iter.next;
            len++;
        } // compute the length of the list

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = len - 1;
        while (i > 0) {
            int j = i;
            iter = pre.next; // back to "current" head;
            while (j > 0) {
                if (iter.val > iter.next.val) {
                    pre = swap(pre, iter);
                } else {
                    iter = iter.next;
                    pre = pre.next;
                }
                j--;
            }
            i--;
            pre = dummy;
        }
        return dummy.next;
    }
    private ListNode swap(ListNode pre, ListNode cur) {
        ListNode first = cur.next;
        cur.next = cur.next.next;
        first.next = cur;
        pre.next = first;
        return first;
    }
}
