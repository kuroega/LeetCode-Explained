public class LongestSubstringWithAtMostTwoDistinctCharacters {

    /*
        This is similar to the question:
            the longest substring without repeating characters

            Idea: maintains a HashMap to store the first distinct char's starting index
                  maintains a HashSet to check the number of distinct chars
    */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() == 0) return 0;
        Map<Character, Integer> lastUpdated = new HashMap<>();
        Set<Character> twoDistinct = new HashSet<>();
        int start = 0;
        int count = 0;
        int max = 0;
        char[] ca = s.toCharArray();

        for (int i = 0; i < ca.length; i++) {
            if (i > 0 && !lastUpdated.containsKey(ca[i - 1])) {
                lastUpdated.clear();
                lastUpdated.put(ca[i - 1], i - 1);
            }
            if (!twoDistinct.contains(ca[i])) {
                count++;
                if (count <= 2) {
                    twoDistinct.add(ca[i]);
                } else { // 3rd distinct char appears
                    start = lastUpdated.get(ca[i - 1]);
                    twoDistinct.retainAll(lastUpdated.keySet());
                    twoDistinct.add(ca[i]);
                    count = 2;
                }
            }
            max = Math.max(max, i - start + 1);
        }
        return max;
    }

    /*
        Can we come up with a more efficient algorithm which
        requires map/set manipulation
    */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2) return s.length();
        char[] dict = new char[256];
    	char[] ca = s.toCharArray();
    	int start = 0, len = 1, count = 0;
    	for (int i = 0; i < ca.length; i++) {
    	    dict[ca[i]]++;
    	    if (dict[ca[i]] == 1) { // new char
                count++;
                while (count > 2) {
                    dict[ca[start]]--;
                    if (dict[ca[start]] == 0) count--;
                    start++;
                }
            }
            len = Math.max(len, i - start + 1);
    	}
        return len;
    }

}
