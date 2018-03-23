public class ContructBinaryTreeFromPreorderAndInorderTraversal {

    /*
        1. We can find initially the first node in preorder array
            is the ROOT of the whole binary tree.
        2. Let us find the index of the root value in the inorder array, i
        3. That means the left subtree can be formed in (0 ~ i - 1)
            and the right subtree will be formed in (i + 1 ~ end)
        4. update the next root node

        If forget how to derive the algorithm, just draw a tree :-)
    */

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return traversal(0, 0, preorder.length - 1, preorder, inorder);
    }
    private TreeNode traversal(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        // pre is the root index for current subtree
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int index = 0;
        for (int idx = 0; idx < inorder.length; idx++) {
            if (inorder[idx] == preorder[pre])
                index = idx;
        }

        root.left = traversal(preStart + 1, inStart  , index - 1, preorder, inorder);
        root.right = traversal(preStart + (index - inStart) + 1, index + 1, inEnd, preorder, inorder);

        return root;
    }

    /*
        Follow-Up:
            What if we are given inorder and postorder traversal array?
    */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return traversal(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    private TreeNode traversal(int postStart, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (inStart > inEnd || postStart < 0)
            return null;

        TreeNode root = new TreeNode(postorder[postStart]);
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val)
                index = i;
        }

        root.left = traversal(postStart + index - inEnd - 1, inStart, index - 1, inorder, postorder);
        root.right = traversal(postStart - 1, index + 1, inEnd, inorder, postorder);

        return root;
    }

}
