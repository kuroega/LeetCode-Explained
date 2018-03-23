public class ClimbingStairs {
    /*
        Totol number of ways
            = (ways of last move is 1-step) + (ways of last move is 2-step)
        Solution: DP
        Time: O(2N)
        Space: O(2N)
    */
    public int climbStairs(int n) {
        int[][] dp = new int[2][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < 2; i++) {
                if (i == 0 && j == 0)
                    dp[i][j] = 1;
                else if (i == 1 && j == 1)
                    dp[i][j] = 1;
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j - 1];
                else if (i == 1 && j > 1)
                    dp[i][j] = dp[i - 1][j - 2] + dp[i][j - 2];
            }
        }
        return dp[0][n - 1] + dp[1][n - 1];
    }

    /*
        DP improvement in Space
            by using Two Pointers
        Time: O(N)
        Space: O(1)
    */
    public int climbStairs(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int pre = 1;
        int cur = 2;
        for (int i = 2; i < n; i++) {
            int tmp = cur;
            cur = cur + pre;
            pre = tmp;
        }
        return cur;
    }
}
