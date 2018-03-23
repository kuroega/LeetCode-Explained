public class ReorderList {
    /*
        Idea: combination of Reverse LinkedList II and I
    */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        } // find the middle node 1->2->3->4->5->6
          //                            ^        ^
        fast = slow.next;
        while (fast.next != null) { // we must insert the node after fast node right next to the slow node
            ListNode tmp = fast.next;
            fast.next = tmp.next;
            tmp.next = slow.next;
            slow.next = tmp;
        } // 1->2->3->4->5->6 => 1->2->3->6->5->4

        ListNode preMiddle = slow;
        slow = head;
        fast = preMiddle.next;
        while (slow.next != fast) { // insert the node after preMiddle node right next to the slow node
            preMiddle.next = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
            fast = preMiddle.next;
        } // 1->2->3->6->5->4 => 1->6->2->5->3->4
    }
}
