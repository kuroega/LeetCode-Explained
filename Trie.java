class Trie {
    static final int ALPHABET_SIZE = 26;

    static class TrieNode {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
        boolean isFinalWord;

        TrieNode() {
            isFinalWord = false;
            // for (int i = 0; i < ALPHABET_SIZE; i++) {
            //     children[i] = null;
            // }
            // default is false
        }
    }

    static TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode pCrawl = root;

        // walk through prefix
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        pCrawl.isFinalWord = true; // insert a complete word
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode pCrawl = root;

        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (pCrawl.children[index] == null) return false;

            pCrawl = pCrawl.children[index];
        }
        return (pCrawl != null) && (pCrawl.isFinalWord == true);
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode pCrawl = root;

        for (char c : prefix.toCharArray()) {
            int index = c - 'a';

            if (pCrawl.children[index] == null) return false;

            pCrawl = pCrawl.children[index];
        }

        return pCrawl != null;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
