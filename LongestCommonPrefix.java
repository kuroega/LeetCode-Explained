public class LongestCommonPrefix {
    /*
        Horizontal Scanning
        LCP(S1...Sn) = LCP(LCP(...LCP(S1, S2)))
        Time: O(N)

    */
    public String longestCommonPrefix(String[] strs) {
        if (str.length == 0) return "";
        String prefix = strs[0]; // set a base prefix

        for (int i = 1; i < strs.length(); i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            } // reduce from tail if not found
        } // horizontally scanning
        return prefix;
    }
}
