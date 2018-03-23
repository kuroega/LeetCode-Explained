public class CopyListWithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode head1 = head;
        while (head1 != null) {
            RandomListNode copy = new RandomListNode(head1.label);
            copy.next = head1.next;
            head1.next = copy;
            head1 = head1.next.next;
        } // side-by-side linking

        head1 = head;
        while (head1 != null) {
            if (head1.random != null) {
                head1.next.random = head1.random.next;
            }
            head1 = head1.next.next;
        } // link random

        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head;
        RandomListNode pre = dummy;
        head1 = head;
        while (head1 != null) {
            pre.next = head1.next;
            head1.next = head1.next.next; // recover original!
            pre = pre.next;
            head1 = head1.next;
        } // decouple the copy and origin and recover the original

        return dummy.next;
    }
}
