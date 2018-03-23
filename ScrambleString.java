public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        int len = s1.length();
        // get length
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0)
                return false;
        } // check all letters' occurance

        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(len - i)) &&
                isScramble(s1.substring(i), s2.substring(0, len - i)))
                return true;
        }
        return false; // try all index as the partition index
    }
}
