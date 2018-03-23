public class WordLadder {

    /*
        Eg: beginWord = "hit"`
            endWord = "cog"
            wordList = ["hot","dot","dog","lot","log","cog"]
            As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",`
    */

    /*
        BFS Approach
            one end BFS

        takes a long time
    */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        return BFS(beginWord, endWord, wordList);
    }
    public int BFS(String beginWord, String endWord, List<String> wordList) {
        Set<Integer> seen = new HashSet<>();
        wordList.add(0, beginWord);
        List<Integer>[] list = new List[wordList.size() + 1]; // an array of lists to store index of words
        list[0] = new ArrayList<Integer>();
        list[0].add(0);
        int i = 0; // ith list in array
        while (!list[i].isEmpty()) {
            list[i + 1] = new ArrayList<Integer>();
            for (int cur : list[i]) {
                String word = wordList.get(cur);
                for (int j = 0; j < wordList.size(); j++) {
                    if (differOneLetter(word, wordList.get(j)) && !seen.contains(j)) {
                        if (wordList.get(j).equals(endWord))
                            return i + 2; // found!
                        list[i + 1].add(j);
                        seen.add(j);
                    }
                }
            }
            i++;
        }
        return 0; // no such transformation found
    }
    private boolean differOneLetter(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
        }
        return count == 1 ? true : false;
    }

    /*
        Improvement:
            Two end BFS
    */

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> allSet = new HashSet<>(wordList);
        if (!wordList.contains(endWord)) return 0;

        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        allSet.remove(beginWord);
        allSet.remove(endWord);
        return bidirectionalBFS(allSet, startSet, endSet, 2);
    }
    private int bidirectionalBFS(Set<String> allSet, Set<String> startSet, Set<String> endSet, int len) {
        if (startSet.isEmpty() || endSet.isEmpty()) return 0;

        if (startSet.size() > endSet.size())
            return bidirectionalBFS(allSet, endSet, startSet, len);
        Set<String> tmp = new HashSet<>();
        for (String s : startSet) {
            char[] ca = s.toCharArray();
            for (int i = 0; i < ca.length; i++) {
                char c = ca[i];
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (c == letter) continue;
                    ca[i] = letter;
                    String newWord = String.valueOf(ca);
                    if (endSet.contains(newWord)) return len;
                    if (allSet.contains(newWord)) {
                        tmp.add(newWord);
                        allSet.remove(newWord);
                    }
                }
                ca[i] = c;
            }
        }
        return bidirectionalBFS(allSet, tmp, endSet, len + 1);
    }

    /*
        Follow-Up: return the a list of lists of words that ALL makes
        the shortest transformation from beginWord to endWord

        [
          ["hit","hot","dot","dog","cog"],
          ["hit","hot","lot","log","cog"]
        ]
    */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> allSet = new HashSet<>(wordList);
        if (!wordList.contains(endWord)) return res;

        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Map<String, List<String>> map = new HashMap<>();
        startSet.add(beginWord);
        endSet.add(endWord);

        bidirectionalBFS(allSet, startSet, endSet, map, false);
        // generate list
        List<String> tmplist = new ArrayList<String>(Arrays.asList(beginWord));
        generateList(beginWord, endWord, map, res, tmplist);
        return res;
    }
    private void bidirectionalBFS(Set<String> allSet, Set<String> startSet, Set<String> endSet, Map<String, List<String>> map, boolean flip) {
        if (startSet.isEmpty() || endSet.isEmpty()) return;

        boolean done = false;
        if (startSet.size() > endSet.size()) {
            bidirectionalBFS(allSet, endSet, startSet, map, !flip);
            return;
        }

        // only need to remove startSet's elements
        // because we loop over words in startSet
        allSet.removeAll(startSet);

        Set<String> tmp = new HashSet<>();
        for (String s : startSet) {
            char[] ca = s.toCharArray();
            for (int i = 0; i < ca.length; i++) {
                char c = ca[i];
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (c == letter) continue;
                    ca[i] = letter;
                    String newWord = new String(ca);

                    // once flipped, we have to "flip" key/val as well
                    String key = flip ? newWord : s;
                    String val = flip ? s : newWord;

                    List<String> list = map.getOrDefault(key, new ArrayList<String>());

                    if (endSet.contains(newWord)) {
                        done = true;
                        list.add(val);
                        map.put(key, list);
                    }
                    if (!done && allSet.contains(newWord)) {
                        tmp.add(newWord);
                        list.add(val);
                        map.put(key, list);
                    }
                }
                ca[i] = c; // recover char array
            }
        }
        if (!done) bidirectionalBFS(allSet, tmp, endSet, map, flip);
    }
    private void generateList(String beginWord, String endWord, Map<String, List<String>> map, List<List<String>> res, List<String> tmplist) {
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList<String>(tmplist));
        } else if (!map.containsKey(beginWord)) {
            return;
        } else {
            for (String next : map.get(beginWord)) {
                tmplist.add(next);
                generateList(next, endWord, map, res, tmplist);
                tmplist.remove(tmplist.size() - 1);
            }
        }
    }
}
