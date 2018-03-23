public class WildcardMatching {
    /*
        Greedy
    */
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int match = 0, wildcard = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) {
                i++;
                j++;
            } // exact match OR '.', move both
            else if (j < p.length() && p.charAt(j) == '*') {
                match = i;
                wildcard = j;
                j++;
            } // "*" match, only move pattern's index
            else if (wildcard >= 0) {
                j = wildcard + 1;
                match++;
                i = match;
            } // previous match is "*", only move string's index
            else return false;
            // wrong match
        }
        while (j < p.length() && p.charAt(j) == '*') j++;
        // pass consecutive "*"
        return j == p.length();
    }
}
