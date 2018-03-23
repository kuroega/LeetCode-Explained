public class OneEditDistance {
    /*
        First Solution: minDistance idea -> DP

            This calculation and idea is correct
            but it is not the most efficient way
            to solve this "one" edit distance problem

        TLE...
    */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) dp[i][j] = j;
                else if (j == 0) dp[i][j] = i;
                else if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    int del = dp[i - 1][j] + 1;
                    int ins = dp[i][j - 1] + 1;
                    int rep = dp[i - 1][j - 1] + 1;
                    dp[i][j] = Math.min(del, Math.min(ins, rep));
                }
            }
        }
        return dp[m][n] == 1;
    }

    /*
        just search linearly to find out if we need more than 1 edit
    */
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) return isOneEditDistance(t, s);
        if (s.equals(t) || m - n > 1)
            return false;
        int i = 0, j = 0, count = 0;
        while (i < m && j < n) {
            if (s.charAt(i) != t.charAt(j)) {
                count++; // an edit found
                if (count > 1) return false; // more than 1 edit needed
                if (m != n) {

                    i++;
                    continue; // hold shorter string, move i to next
                }
            }
            i++, j++; // if the same, then compare next
        }
        return true;
    }

}
