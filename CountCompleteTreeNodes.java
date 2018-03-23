public class CountCompleteTreeNodes {
	/*
		The problem asks us to calculate the number of nodes 
		in a complete binary tree, which means all levels of 
		tree is full except the last level, where nodes are as far
		to left as possible. (filled from the leftmost side)

		The best recursive solution


		for general binary tree, the function would easily be 
		
		int func(TreeNode root) {		
			if (root == null) return 0;
			return 1 + func(root.left) + func(root.right);
		}

		but in this problem, we need to return as quick as possible
		because we should leverage the feature of "complete" binary tree.
			
	*/
	public int countNodes(TreeNode root) {
		if (root == null) return 0;
        int count = 1;
        TreeNode l = root.left, r = root.left;
        while (r != null) {
            l = l.left;
            r = r.right;
            count <<= 1;
        }
        // if l is null, which means l and r in the same level, then we can easily 
        // calculate the number of all nodes without right subtree
        // and then go to right subtree
        // if l is not null, which means l is the last level that not full
        // so we can eaily calculate the number of all nodes without left subtree
        // and then go to left subtree
        // *if hard to understand, just draw a complete binary tree*
        return count + (l == null ? countNodes(root.right) : countNodes(root.left));
	}
	// we can also use queue to cache parent nodes, but TLE...
}