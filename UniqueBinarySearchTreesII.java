public class UniqueBinarySearchTrees {
    /*
        Cartesian Product

        empty    G(0) = 1
        1        G(1) = 1
        1 2      G(2) = G(2-1) * G(2-2) + G(1-1) * G(2-1) = 2
        1 2 3    G(3) = ...
        1 2 3 4  G(4) = ....

    */
    public int countTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }

        return G[n];
    }



    /*
        Follow-Up:
            In addition,
            constructs all unique BSTs
            and put them in a list
    */
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] res = new List[n + 1];
        if (n == 0) return res[0];
        res[0].add(null);

        for (int i = 1; i <= n; i++) {
            res[i] = new ArrayList<TreeNode>();
            for (int j = 0; j < i; j++)
                for (TreeNode nodeL : res[j])
                    for (TreeNode nodeR : res[i - j - 1]) {
                        TreeNode root = new TreeNode(j + 1);
                        root.left = nodeL;
                        root.right = clone(nodeR, j + 1);
                        res[i].add(root);
                    }
        }
        return res[n];
    }
    private TreeNode clone(TreeNode node, int offset) {
        if (node == null)
            return null;
        TreeNode root = new TreeNode(node.val + offset);
        root.left = clone(root.left, offset);
        root.right = clone(root.right, offset);
        return root;
    }
}
