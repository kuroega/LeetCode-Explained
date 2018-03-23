public class InterleavingString {
    /*
        Brute Force Way:
            Time: O(2^(m + n))
            Space: O(m + n)
        [TLE]
    */
    public boolean isInterleave(String s1, String s2, String s3) {
        return is_interleave(s1, 0, s2, 0, "", s3);
    }
    private boolean is_interleave(String s1, int i, String s2, int j, String res, String s3) {
        if (res.equals(s3) && i == s1.length() && j == s2.length())
            return true;
        boolean ans = false;
        if (i < s1.length())
            ans |= is_interleave(s1, i + 1, s2, j, res + s1.charAt(i), s3);
        if (j < s2.length())
            ans |= is_interleave(s1, i, s2, j + 1, res + s2.charAt(j), s3);

        return ans;
    }

    /*
        Slight improvement
            Time: O(m * n)
            Space: O(m * n)
    */
    public boolean isInterleave(String s1, String s2, String s3) {
        int[] mem = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                mem[i][j] = -1;
            }
        }
        return is_interleave(s1, 0, s2, 0, s3, 0, mem);
    }
    private boolean is_interleave(String s1, int i, String s2, int j, String s3, int k, int[][] mem) {
        if (i == s1.length())
            return s2.substring(j).equals(s3.substring(k));
        if (j == s2.length())
            return s1.substring(i).equals(s3.substring(k));
        if (mem[i][j] >= 0)
            return mem[i][j] == 1 ? true : false;
        boolean ans = false;
        if (s1.charAt(i) == s3.charAt(k) && is_interleave(s1, i + 1, s2, j, s3, k + 1, mem)
            || (s2.charAt(j) == s3.charAt(k) && is_interleave(s1, i, s2, j + 1, s3, k + 1, mem)))
            ans = true;

        mem[i][j] = ans ? 1 : 0;
        return ans;
    }

    /*
        Real DP Solution
            Time: O(m * n)
            Space: O(m * n)
    */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) dp[i][j] = true;
                else if (i == 0)
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                else if (j == 0)
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                else if (i > 0 && j > 0)
                    dp[i][j] = (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1])
                        || (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /*
        DP space improvement
        Time Same
        Space: O(n) n is the length of s2 (simple table trick, idea is the same)
            -> observe the pattern of updating the
                table in terms of s1 match or s2 match
    */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[] dp = new boolean[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) dp[j] = true;
                else if (i == 0)
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                else if (j == 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                else
                    dp[j] = (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[j - 1])
                        || (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[j]);
            }
        }
        return dp[s2.length()];
    }
}
