public class WordBreaker {

    /*
        For example, given
            s = "leetcode",
            dict = ["leet", "code"].

            Return true because "leetcode" can be segmented as "leet code".
    */
    /*
        My idea: split word and dfs searching the substring

            it does work
            ...but TLE
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        String tmp = "";
        return dfs(s, dict, tmp, 0, false);
    }
    private boolean dfs(String s, Set<String> dict, String tmp, int start, boolean found) {
        if (tmp.equals(s)) {
            found = true;
        } else {
            for (int i = start; i < s.length(); i++) {
                String cur = s.substring(start, i + 1);
                if (dict.contains(cur)) {
                    found = dfs(s, dict, tmp + cur, i + 1, found);
                    if (found) break;
                }
            }
        }
        return found;
    }

    /*
        Think in another way:
            let's search in the given dict,
            the reason is the number of elements in dict
            ususally much less than the number of all substrings!
            , which leads to better time complexity
    */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // dp[i] indicates 0 ~ i is found

        for (int i = 1; i <= s.length(); j++) { // scan from the length 0 to full length
            for (String word : wordDict) {
                if (word.length() <= i) {
                    if (s.substring(i - word.length(), i).equals(word) && dp[i - word.length()]) {
                        dp[i] = true; // only set true when current segment is right after an asserted sub-word
                        break;
                    }
                }
            }
        }
        return dp[s.length()];
    }

    /*
        Follow-Up:

        For example, given
            s = "catsanddog",
            dict = ["cat", "cats", "and", "sand", "dog"].

            A solution is ["cats and dog", "cat sand dog"].
    */

    /*
        DFS solution
            when it comes to a "all possible cases", the first way I
            come up with is backtrack

        ...but again TLE
    */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        List<String> tmplist = new ArrayList<>();
        dfs(res, s, dict, tmplist, 0);
        return res;
    }
    private void dfs(List<String> res, String s, Set<String> dict, List<String> tmplist, int start) {
        if (start == s.length())  {
            res.add(addSpace(tmplist));
            return;
        } else {
            for (int i = start; i < s.length(); i++) {
                String cur = s.substring(start, i + 1);
                if (dict.contains(cur)) {
                    tmplist.add(cur);
                    dfs(res, s, dict, tmplist, i + 1);
                    tmplist.remove(tmplist.size() - 1);
                }
            }
        }
    }
    private String addSpace(List<String> tmplist) {
        String sentence = "";
        for (String s : tmplist) {
            sentence = sentence + s + " ";
        }
        return sentence.substring(0, sentence.length() - 1);
    }

    /*
        Very delicate algorithm and idea
            Use HashMap to avoid repeated computing
    */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<String, ArrayList<String>>();
        return dfs(s, wordDict, map);
    }
    private List<String> dfs(String cur, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }

        List<String> res = new ArrayList<>();
        if (cur.length() == 0) {
            res.add("");
            return res;

        for (String word : wordDict) {
            if (cur.startWith(word)) {
                List<String> nextSentence = dfs(cur.substring(word.length()), wordDict, map);
                for (String ns : nextSentence) {
                    res.add(word + (ns.isEmpty() ? "" : " ") + ns);
                }
            }
        }
        // add the substring 'cur' start from i, end in last
        // character, mapping to a list of all computed solutions
        map.put(cur, res);
        return res;
    }
}
