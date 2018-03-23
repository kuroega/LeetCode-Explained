public class BinaryTreeLevelOrderTraversal {

    /*
        two stacks solution
            1 for current level
            1 for next level
    */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root == null) return res;
        stack1.push(root);

        while (!stack1.empty()) {
            List<Integer> list = new ArrayList<>();
            while (!stack1.empty()) {
                list.add(stack1.peek().val);
                stack2.push(stack1.pop());
            }
            res.add(list);

            while (!stack2.empty()) {
                TreeNode tmp = stack2.pop();
                if (tmp.right != null) stack1.push(tmp.right);
                if (tmp.left != null) stack1.push(tmp.left);
            }
        }
        return res;
    }

    /*
        Follow-Up: traversal in bottom-up order
    */

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null) return res;
        q.add(root);
        int size = 1;
        while (q.size() != 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                list.add(cur.val);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            res.add(0, list);
            size = q.size();
        }
        return res;
    }

    /*
        Follow-Up:
            ZigZag Traversal => left, right, left, right...
        similar two stacks solution
    */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        if (root == null) return res;
        stack1.push(root);

        while (!stack1.empty() || !stack2.empty()) {
            List<Integer> list = new ArrayList<>();
            while (!stack1.empty()) {
                TreeNode cur = stack1.pop();
                list.add(cur.val);
                if (cur.left != null) stack2.push(cur.left);
                if (cur.right != null) stack2.push(cur.right);
            }
            res.add(list);

            list = new ArrayList<Integer>();
            while (!stack2.empty()) {
                TreeNode cur = stack2.pop();
                list.add(cur.val);
                if (cur.right != null) stack1.push(cur.right);
                if (cur.left != null) stack1.push(cur.left);
            }
            if (!list.isEmpty()) res.add(list);
        }
        return res;
    }

    /*
        one queue solution
    */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int size = 1;
        boolean order = true;

        while (q.size() != 0) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (order) list.add(cur.val); // left -> right (append)
                else list.add(0, cur.val);    // right -> left (insert)
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }
            res.add(list);
            size = q.size();
            order = order ? false : true;
        }
        return res;
    }


}
