public class RecoverBinarySearchTree {

    /*
        Intuition is In-order Traversal
    */

    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode pre = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        find(root);
        swap(firstNode, secondNode);
    }
    private void find(TreeNode root) {
        if (root == null)
            return;
        find(root.left);
        if (firstNode == null && pre.val >= root.val) {
            firstNode = pre;
        }
        if (firstNode != null && pre.val >= root.val) {
            secondNode = root;
        }
        pre = root;
        find(root.right);
    }
    private void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }
}
