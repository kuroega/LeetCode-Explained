public class BinaryTreeUpsideDown {
    /*
         1
        / \
       2   3
      / \
     4   5         {1,2,3,4,5},

         4
        / \
       5   2      [4,5,2,#,#,3,1].
          / \
         3   1

    */

    /*
        build the tree from bottom up
            the current right child is the previos root

        In case you forget the intuition, please draw a BTree
    */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return dfs(root, null, null);
    }
    private TreeNode dfs(TreeNode left, TreeNode parent, TreeNode right) {
        if (left == null) return parent;
        TreeNode newRoot = new TreeNode(left.val);
        newRoot.right = parent;
        newRoot.left = right;
        return dfs(left.left, newRoot, left.right);
    }
}
