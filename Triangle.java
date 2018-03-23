public class Triangle {
    /*
        [
              [2],
             [3,4],
            [6,5,7],
           [4,1,8,3]
        ]
    */

    /*
        Deque + DFS solution

        TLE
    */
    private Deque<Integer> dq = new LinkedList<>();
    public int minimumTotal(List<List<Integer>> triangle) {
        dfs(triangle, 0, 0, 0);
        return dq.peekFirst();
    }
    private void dfs(List<List<Integer>> triangle, int level, int sum, int start) {
        if (level == triangle.size()) {
            maintainMin(sum);
        }
        else {
            for (int i = start; i <= start + 1 && i < triangle.get(level).size(); i++) {
                sum += triangle.get(level).get(i);
                dfs(triangle, level + 1, sum, i);
                sum -= triangle.get(level).get(i);
            }
        }
    }
    private void maintainMin(int sum) {
        while (dq.size() != 0 && dq.peekLast() > sum) {
            dq.removeLast();
        }
        dq.addLast(sum);
    }

    /*
        Dynamic Programming
    */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        List<Integer> bottom = triangle.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                bottom.set(j,
                    Math.min(bottom.get(j), bottom.get(j + 1)) + triangle.get(i).get(j));
            }
        }
        return bottom.get(0);
    }

}
