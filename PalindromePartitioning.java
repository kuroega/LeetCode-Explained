public class PalindromePartitioning {
    /*
        Backtracking:
            Eg. "aab"
                in the main loop we check substring of s
                from "a", "aa" to "aab", and for each substring
                of s, we pass the remaining substring in dfs,
                that is, we substring is "a", we check "ab" is
                a palindrome.
    */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> tmplist = new ArrayList<>();
        dfs(s, res, tmplist, 0);
        return res;
    }
    private void dfs(String s, List<List<String>> res, List<String> tmplist, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(tmplist));
            return;
        } else {
            for (int i = start; i < s.length(); i++) {
                String cur = s.substring(start, i + 1);
                if (isPalindrome(cur)) {
                    tmplist.add(cur);
                    dfs(s, res, tmplist, i + 1); // backtrack
                    tmplist.remove(tmplist.size() - 1);
                }
            }
        }
    }
    private boolean isPalindrome(String a) {
        if (a.length() == 1) return true;
        char[] ca = a.toCharArray();
        int left = 0, right = ca.length - 1;
        while (left < right) {
            if (ca[left] != ca[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    /*
        Follow-Up:
            return the minimum cut of palindrome partitioning
                Eg. "aab" -> return 1, because ["aa", "b"]
    */
    /*
        First of all, we will come up with a similar DFS solution
        But it is not the most efficient way to apply.

        got TLE
    */
    private int min = Integer.MAX_VALUE;
    public int minCut(String s) {
        List<String> tmplist = new ArrayList<>();
        dfs(s, tmplist, 0);
        return min - 1;
    }
    private void dfs(String s, List<String> tmplist, int start) {
        if (start == s.length()) {
            min = Math.min(min, tmplist.size());
            return;
        } else {
            for (int i = start; i < s.length(); i++) {
                String cur = s.substring(start, i + 1);
                if (isPalindrome(cur)) {
                    tmplist.add(cur);
                    dfs(s, tmplist, i + 1); // backtrack
                    tmplist.remove(tmplist.size() - 1);
                }
            }
        }
    }
    private boolean isPalindrome(String a) {
        if (a.length() == 1) return true;
        char[] ca = a.toCharArray();
        int left = 0, right = ca.length - 1;
        while (left < right) {
            if (ca[left] != ca[right]) return false;
            left++;
            right--;
        }
        return true;
    }

    /*
        So... what is the most efficient algorithm?
        look at the question, it asks the MINIMUM CUT,
        when we see xxxest, max, min,...we need to first
        think of DP!
            1. avoid repeated computing
            2. it is what DP being created for!
    */

    public int minCut(String s) {
        char[] ca = s.toCharArray();
        int n = s.length();
        int[] cut = new int[n + 1];
        for (int i = 0; i <= n; i++) cut[i] = i - 1;

        for (int center = 0; center < n; center++) {
            for (int l = center, r = center; l >= 0 && r < n && ca[l] == ca[r]; l--, r++) {
                cut[r + 1] = Math.min(cut[r + 1], cut[l] + 1);
            }
            for (int l = center, r = center + 1; l >= 0 && r < n && ca[l] == ca[r]; l--, r++) {
                cut[r + 1] = Math.min(cut[r + 1], cut[l] + 1);

            }
        }
        return cut[n];
    }
}
