public class ReverseNodesInKGroup {
    /*
        Recursive Solution
    */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;
        int counter = 0;
        while (current != null && counter != k) {
            current = current.next;
            counter++;
        } // find the (k + 1)th node

        if (counter == k) { // found (k+1)th node
            current = reverseKGroup(current, k);

            while (counter> 0) {
                ListNode tmp = head.next;
                head.next = current;
                current = head;
                head = tmp;
                counter--;
            }
            head = current;
        }
        return head;
    }

    /*
        Iterative Solution
    */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        // edge cases: head is null OR only one node in the list OR k = 1

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre, cur = dummy;
        // dummy node

        while (true) {// while true block
            int counter = 0;
            // counter
            while (cur != null && counter != k) {
                counter++;
                cur = cur.next;
            } // find kth node

            if (cur == null) break; // exact reverse
            // if not k that means we reached the end

            ListNode copy = pre.next;
            while (pre.next != cur) {
                ListNode tmp = pre.next;
                pre.next = tmp.next;
                tmp.next = cur.next;
                cur.next = tmp;
            } // reverse technique: delete and insert
            pre = cur = copy;
        }
        return dummy.next;
        // return dummy.
    }
}
