class AddAndSearchWord {
    Set<String> set;
    int minLen; // maintains the minimum length of input word

    /** Initialize your data structure here. */
    public WordDictionary() {
        set = new HashSet<>();
        minLen = Integer.MAX_VALUE;
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        set.add(word);
        minLen = Math.min(minLen, word.length());
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (minLen > word.length()) return false; // no possible, early termination
        if (set.contains(word)) return true; // exact match
        for (String s : set) {
            if (s.length() != word.length()) continue; // must be same length

            int i;
            for (i = 0; i < s.length(); i++)
                if (s.charAt(i) != word.charAt(i) && word.charAt(i) != '.')
                    break;

            if (i == s.length()) return true; // regex '.' match
        }
        return false; // no match found
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */


/*
    Solution 2: Trie
*/

class WordDictionary {
    // Trie solution
    static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isFinalWord;

        TrieNode() {
            isFinalWord = false;
        }
    }
    static TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void addWord(String word) {
        TrieNode pCrawl = root;

        // walk through prefix
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }
        pCrawl.isFinalWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }
    private boolean match(char[] ca, int level, TrieNode node) {
        if (level == ca.length) return node.isFinalWord;
        if (ca[level] == '.') {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null)
                    if (match(ca, level + 1, node.children[i]))
                        return true;
            }
        } else {
            if (node.children[ca[level] - 'a'] != null)
                return match(ca, level + 1, node.children[ca[level] - 'a']);
        }
        return false;
    }
}
