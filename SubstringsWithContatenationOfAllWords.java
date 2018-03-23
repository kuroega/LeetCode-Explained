public class SubstringWithConcatenationOfAllWords {
    /*
        my verbose imcomplete solution -> chaos (Unaccepted)
    */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int wlen = words[0].length();
        int j = 0;
        Map<String, Integer> occurances = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!occurances.containsKey(words[i])) {
                occurances.put(words[i], 1);
            } else {
                occurances.put(words[i], occurances.get(words[i]) + 1);
            }
        } // a hashmap record the occurances
        while (j < s.length()) {
            String s_cur = s.substring(j);

            int[] current = new int[words.length];
            Arrays.fill(current, -1);

            int counter = 0;
            int i = 0;
            Map<String, Integer> occurances_copy = new HashMap<>();
            occurances_copy.putAll(occurances);

            while (i < words.length) {
                int lastIdx = 0;
                String cur_word = words[counter];
                while (occurances_copy.get(cur_word) != 0) {
                    current[i] = s_cur.substring(lastIdx).indexOf(cur_word); // 0
                    i++; //
                    occurances_copy.put(cur_word, occurances_copy.get(cur_word) - 1);
                    lastIdx = lastIdx + wlen;
                }
                counter++; // finish one word (including its duplicates)
            }

            Arrays.sort(current); // sort

            if (isValidConcatenation(current, wlen)) // validate
                res.add(current[0] + j);

            j = j + current[0] + wlen;
        }
        return res;
    }
    private boolean isValidConcatenation(int[] current, int wlen) {
        if (current.length == 1)
            return current[0] == -1 ? false : true;
        int pre = current[0];
        for (int i = 1; i < current.length; i++) {
            if (pre + wlen != current[i]) return false;
            pre = current[i];
        }
        return true;
    }

    /*
        Two Map Solution
        The most clear and elegent solution found on LC so far
        Use a Window to slice the string and check
    */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            count.put(words[i], count.getOrDefault(words[i], 0) + 1);
        // make a count Map (occurances)

        int slen = s.length(), n = words.length, wlen = words[0].length();

        for (int i = 0; i < slen - n * wlen + 1; i++) {
            Map<String, Integer> seen = new HashMap<>();
            // for each i we create a seen map
            int j = 0;
            // a indicater j to indicate nth word in substring to being checked
            while (j < n) {
                String word = s.substring(i + j * wlen, i + (j + 1) * wlen);
                if (count.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > count.getOrDefault(word, 0))
                        break;
                    // if seen time for a w > the real occurance
                    // -> break -> move on to next i
                } else {
                    break;
                }
                j++; // move to next window
            }
            if (j == n)
                res.add(i);
                // if j == N(wd[]) -> means the current start index i is good
                // we find a valid concatenation
                // -> add to result

        } // loop over the String s from i=0 to i=(slen - N(wds[])*len(w) + 1)
        return res;
    }
}
