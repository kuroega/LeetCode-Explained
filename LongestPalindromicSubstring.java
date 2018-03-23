  public class LongestPalindromicSubstring {
    /* Approach #1: reverse s and use Longest Common Substring
    *  NOT COMPLETED
    */
    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb = sb.reverse();
        String rev_s = sb.toString(); // reverse the input string
        return LCS(s, rev_s);
    }
    private String LCS(String s1, String s2) {
        int m = s1.length();
        int[][] dp = new int[m + 1][m + 1];
        int result = 0;
        int idx = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // result = Math.max(result, dp[i][j]);
                    if (dp[i][j] > result && i != j) {
                        result = dp[i][j];
                        idx = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        // return result;
        StringBuilder sb = new StringBuilder();
        for (int i = result; i > 0; i--) {
            sb.append(s1.charAt(idx - i));
        }
        return sb.toString();
    }
    private boolean isValidCandidate() {
        //..... not interesting
    }

    /* Brute Force
        O(n^3)
    */


    /* DP
        Time O(N^2)
        Space O(1)
    */
    public String longestPalindrome(String s) {
        int start = 0, end = 0; // the position of palindrome
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i, i); // odd length
            int len2 = expandFromCenter(s, i, i + 1); // even length 
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            } // update a longer palindrome's position
        }
        return s.subtring(start, end + 1);
    }
    private int expandFromCenter(String s, int L, int R) {
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        } // check and expand palindrome from center
        return R - L - 1; // length of palindrome
    }

}
