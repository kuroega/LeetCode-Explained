public class IsRegexMatch {
    /*
        Recursion Solution
        Time: O((T + P) * 2^(T + P/2))
        Space: O((T + P) * 2^(T + P/2))
    */
    public boolean isMatch(String s, String p) {
        if (p.length() == 0)
            return s.length() == 0 ? true : false; // empty match

        boolean first_match = (s.length() > 0) &&
            (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        // first match (exact or '.')

        if (p.length() > 1 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2))) ||
                (first_match && isMatch(s.substring(1), p));
        } // second match ('*')
        else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
        // second match (not '*')
    }

    /*
        DP Solution (Bottom-Up)
        Time: O(T * P)
        Space: O((T + 1)(P + 1)) = O(T * P)
    */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[m][n] = true; // empty match

        for (int i = m; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                boolean first_match = (i < m) &&
                    (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                    // first_match (exact match OR '.')
                if (j < n - 1 && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j]);
                    // second match ('*')
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                    // second match (not '*') OR j = n - 1
                }
            }
        }
        return dp[0][0];
    }
}
