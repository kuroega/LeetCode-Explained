/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class PopulatingNextRightPointersInEachNode {
    /*
                1 -> NULL
               /  \
              2 -> 3 -> NULL
             / \  / \
            4->5->6->7 -> NULL

        Given perfect complete BT

        Similar Solution: Level Order Traversal
            use O(N) space
    */

    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> q = new LinkedList<>();
        if (root == null) return;
        q.add(root);
        int size = 1;
        while (q.size() != 0) {
            List<TreeLinkNode> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = q.poll();
                list.add(cur);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            size = q.size();
            TreeLinkNode head = list.remove(0);
            for (TreeLinkNode t : list) {
                head.next = t;
                head = head.next;
            }
            head.next = null;
        }
    }

    /*
        Solution 2: use constant space
    */

    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode cur = root;
        TreeLinkNode pre = null;
        while (cur.left != null) {
            pre = cur;
            cur = cur.left;
            TreeLinkNode cur_head = cur;
            while (cur != null) {
                cur.next = pre.right;
                cur = cur.next;
                if (pre.next == null) break;
                pre = pre.next;
                cur.next = pre.left;
                cur = cur.next;
            }
            cur.next = null;
            cur = cur_head;
        }
    }

    /*
        Follow-Up: given any kind of BT

              1 -> NULL
            /  \
           2 -> 3 -> NULL
          / \    \
         4-> 5 -> 7 -> NULL
    */
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode cur = root;
        TreeLinkNode pre = null;
        while (cur.left != null || cur.right != null) {
            pre = cur;
            cur = cur.left != null ? cur.left : cur.right;
            TreeLinkNode cur_head = cur;
            while (cur != null) {
                if (pre.right != null && pre.right != cur) {
                    cur.next = pre.right;
                    cur = cur.next;
                } // check availability & avoid connect to itself
                if (pre.next == null) break;
                pre = pre.next;
                if (pre.left != null) {
                    cur.next = pre.left;
                    cur = cur.next;
                } // check availability
            }
            cur.next = null;
            cur = cur_head;
            while (cur.next != null && cur.left == null && cur.right == null) {
                cur = cur.next;
            } // move to next available node
        }
    }
}
