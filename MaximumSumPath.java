public class MaximumSumPath {
    int max;
    public int maxPathSum(TreeNode root) {
        max = Integer.MIN_VALUE;
        traversal(root);
        return max;
    }
    private int traversal(TreeNode node) {
        if (node == null) return 0;
        int leftMax = Math.max(0, traversal(node.left));
        int rightMax = Math.max(0, traversal(node.right));
        max = Math.max(max, node.val + leftMax + rightMax);
        return node.val + Math.max(leftMax, rightMax);
    }
}
