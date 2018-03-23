public class DecodeWays {
        /*
            'A' -> 1
            'B' -> 2
            ...
            'Z' -> 26

            Given encoded message "12", it could
             be decoded as "AB" (1 2) or "L" (12).
        */

        /*
            disjoint subsets problem
            (set size is 1 or 2)

            recursion
            total = 1-digit + (if possible)2-digit

            Time: O(N^2)
            TLE
        */
    public int numDecodings(String s) {
        char[] message = s.toCharArray();
        return s.length() == 0 ? 0 : decoder(message, 0);

    }
    private int decoder(char[] m, int i) {
        if (i == m.length) return 1;
        if (m[i] == '0') return 0;
        int res = decoder(m, i + 1);
        if (i < m.length - 1 && (m[i] == '1' || (m[i] == '2' && m[i + 1] < '7')))
            res += decoder(m, i + 2);
        return res;
    }

    /*
        DP
        a table to memorize total possible ways
        that end in index i of the string

        Time: O(N)
        Space: O(N)
    */

    public int numDecodings(String s) {
        char[] m = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (m[i] = '0') dp[i] = 0;
            else {
                dp[i] = dp[i + 1];
                if (i < s.length() - 1 && (m[i] == '1' || (m[i] == '2' && m[i + 1] < '7')))
                    dp[i] += dp[i + 2];
            }
        }
        return s.length() == 0 ? 0 : dp[0];
    }

    /*
        we can also make the space constant
        because in the previous program,
        we can see dp[i]' value only depends
        on dp[i + 1] and dp[i + 2]

        we just need two integer variables to store those
    */

}
