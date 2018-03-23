public class EditDistance {

    /*
        Explanation:
        https://leetcode.com/problems/edit-distance/discuss/25846/20ms-Detailed-Explained-C++-Solutions-(O(n)-Space)
    */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++)
            dp[i][0] = i;
        for (int j = 0; j <= n; j++)
            dp[0][j] = j;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int del = dp[i + 1][j];
                    int ins = dp[i][j + 1];
                    int rep = dp[i][j];
                    dp[i + 1][j + 1] = del < ins ? (del < rep ? del : rep) : (ins < rep ? ins : rep);
                    dp[i + 1][j + 1]++;
                }
            }
        }
        return dp[m][n];
    }

    /*
        Space improvement -> O(m) or O(n)
    */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int j = 1; j <= n; j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= m; i++) {
            int pre = dp[0];
            dp[0] = i;
            int prev = 0;
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(pre, Math.min(dp[j - 1], dp[j])) + 1;
                }
                pre = tmp;
            }
        }
        return dp[n];
    }
}
