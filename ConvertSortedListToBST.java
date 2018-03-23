public class ConvertSortedListToBST {

    /*
        Observe the LinkedList given
            left, root, right
        what is it? In-order Traversal sequence

        Solution: calculate List's length first
    */
    private TreeNode node;
    public TreeNode sortedListToBST(ListNode head) {
        node = head;
        ListNode copy = head;
        int len = 0;
        while (copy != null) {
            len++;
            copy = copy.next;
        }

        return traversal(len);
    }
    private TreeNode traversal(len) {
        if (len <= 0) return null;

        TreeNode left = traversal(len / 2);
        TreeNode root = new TreeNode(node.val);
        node = node.next;
        TreeNode right = traversal(len - len / 2 - 1);

        root.left = left;
        root.right = right;

        return root;
    }

    /*
        Solution2: slow and fast pointer to find the middle

        I personally perfer this solution bacause the recursive
        definition is more clear and straightforward,
        because what we need is the middle "index", while in the
        scenario of Linked List, we can find the middle "index" by
        using fast and slow pointers naturally.
    */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }
    private TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow);
        root.right = toBST(slow.next, tail);

        return root;
    }

}
