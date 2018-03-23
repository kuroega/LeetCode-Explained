public class UniquePaths {
    /*
        Straight-Forward Solution
        Backtracking
        ...but Time Limit Execeeded
        can not handle big number
        since the time complexity is exponential
    */
    int res;
    public int uniquePaths(int m, int n) {
        res = 0;
        backtrack(m, n, 1, 1);
        return res;
    }
    private void backtrack(int m, int n, int cur_m, int cur_n) {
        if (cur_m == m || cur_n == n) {
            res++;
            return;
        } // base case
        else {
            backtrack(m, n, cur_m + 1, cur_n); // ↓
            backtrack(m, n, cur_m, cur_n + 1); // →
        }
        // backtrack
    }

    /*
        Easy Improvement: DP
        maintain a history table to all possible entries
        (row, col)
    */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // cur = ↑ + ←
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
        Follow-Up: Unique Paths II
        we set obstacles in the grid, marked as '1'
        [
          [0,0,0],
          [0,1,0],
          [0,0,0]
        ]

        Solution: DP
    */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) return 0;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    if (i == 0 && j < n - 1) obstacleGrid[i][j + 1] = 1;
                    else if (j == 0 && i < m - 1) obstacleGrid[i + 1][j] = 1;
                    continue;
                }

                if (i == 0 || j == 0)
                    dp[i][j] = 1;
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; // cur = ↑ + ←
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
        Follow-Up: Minimum Path Sum
        [
            [1,3,1],
            [1,5,1],
            [4,2,1]
        ]
        find the minimum sum path from top left position

        Solution: DP
    */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = grid[i][j];
                else if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
                else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
                else
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    /*
        Very Similar question: Dungeon Game
        Knight: Start his fight from the top-left point
        [
            [-2,  -3,  3],
            [-5, -10,  1],
            [10,  30, -5]
        ]
        he is going to rescue the princess in the bottom-right point
    */
    /*
        The best solution is a DP bottom-up way

        The reason we choose bottom-up is because
        we use the destination to find the root,
        in other words, we already know the initial
        HP should be as less as possible and the
        knight can keep alive at least HP = 1,
        so it is clear that
            1. if the bottom-right cell <= 0
                the HP after the cost, remains 1.
            2. if the bottom-right cell > 0
                we end up with 1
    */
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon.length == 0 || dungeon.length > 0 && dungeon[0].length == 0) return 0;
        int m = dungeon.length, n = dungeon[0].length;
        int[][] dp = new int[m][n];

        // each cell means the minimum HP we need to go through current place to the end
        dp[m - 1][n - 1] = (1 - dungeon[m - 1][n - 1]) <= 0 ? 1 : (1 - dungeon[m - 1][n - 1]);

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i == m - 1 && j == n - 1) continue;

                else if (i == m - 1 && j < n - 1) {
                    int hpneed = dp[i][j + 1] - dungeon[i][j];
                    dp[i][j] = hpneed <= 0 ? 1 : hpneed;
                }

                else if (i < m - 1 && j == n - 1) {
                    int hpneed = dp[i + 1][j] - dungeon[i][j];
                    dp[i][j] = hpneed <= 0 ? 1 : hpneed;
                }

                else {
                    int hpneed = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                    dp[i][j] = hpneed <= 0 ? 1 : hpneed;
                }
            }
        }
        return dp[0][0];

    }
}
