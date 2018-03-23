public class MinimumWindowSubstring {
    /*
        For example,
            S = "ADOBECODEBANC"
            T = "ABC"
        Minimum window is "BANC".
    */

    /*
        Brute Force Way:
            Scan all substrings of S whose length longer or equal
            to the length or target string.
            And find all valid substring(s) among them.
            return the substring that has the minimum length (aka min-Window)
    */

    /*
        How about a O(N) way to solve the problem?
            linear Scanning from left to right
            not return back
            ...that sounds like a DP

        always keep a minimum length window for
        the target string (end in index i)
    */

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray())
            map.put(c, 0);
        // all chars mapping to 0

        for (char c : t.toCharArray()) {
            if (map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                return "";
        } // if t contains char then plus 1

        int start = 0, end = 0, minStart = 0, minWindow = Integer.MAX_VALUE, counter = t.length();
        while (end < s.length()) { // two pointers, move the 2nd pointer from 0 to end
            char c1 = s.charAt(end);
            if (map.get(c1) > 0)
                counter--; // decrease counter if see a char with positive value
            map.put(c1, map.get(c1) - 1);
            // drease the mapping value

            end++;

            while (counter == 0) {
                if (minWindow > end - start) {
                    minWindow = end - start;
                    minStart = start;
                }
                // if all chars are seen, update the min len and increase the value for start char
                char c2 = s.charAt(start);
                map.put(c2, map.get(c2) + 1);

                if (map.get(c2) > 0)
                    counter++;
                // increase counter if value is positive
                start++;
            }
        }
        return minWindow == Integer.MAX_VALUE ? "" : s.substring(minStart, minWindow + minStart);
    }
}
