public class WordSearch {
    /*
    [
        ['A','B','C','E'],
        ['S','F','C','S'],
        ['A','D','E','E']
    ]
        word = "ABCCED", -> returns true,
        word = "SEE",    -> returns true,
        word = "ABCB",   -> returns false.
    */

    /*
        Brute Force Way is find
            1. the 1st char, then record all occurances
            2. then search 2nd char in all adjacent cells of 1st word positions
            3. keep searching until all possibe ways are searched
    */

    /*
        From BF way, we can feel that it may be a DFS backtrack problem
            backtrack is:

                loop {
                    if reach the goal:
                        break loop and return
                    if not:
                        1. check current
                            if yes, go deeper
                            if no, skip current
                        2. next
                }
    */

    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searching(board, word, m, n, i, j, 0) == true)
                    return true;
            }
        }
        return false;
    }
    private boolean searching(char[][] board, String word, int m, int n, int i, int j, int start) {
        if (start == word.length()) return true; // all char found

        if (i < 0 || j < 0 || i == m || j == n) return false; // cannot reach cell

        if (word.charAt(start) != board[i][j]) return false; // skip this cell

        board[i][j] ^= 256; // bitwise XOR 256 (1 0000 0000), simply mask
                            // only for avoiding visiting again

        boolean found = searching(board, word, m, n, i - 1, j, start + 1)

                    || searching(board, word, m, n, i + 1, j, start + 1)

                    || searching(board, word, m, n, i, j - 1, start + 1)

                    || searching(board, word, m, n, i, j + 1, start + 1);

        board[i][j] ^= 256; // backtrack and recover the value (unmask)

        return found;
    }

    /*
        Follow-Up:
            the input is a list of words instead of a single word

            Given words = ["oath","pea","eat","rain"] and board =

            [
              ['o','a','a','n'],
              ['e','t','a','e'],
              ['i','h','k','r'],
              ['i','f','l','v']
            ]

            Return ["eat","oath"].
    */
    /*
        Apply the same solution as the original problem

            Time: O(N^3) very low efficiency
    */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        int m = board.length, n = board[0].length;
        for (String w : words) {
            if (set.contains(w)) continue;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (searching(board, w, m, n, i, j, 0) == true) {
                        set.add(w);
                    }
                }
            }
        }
        return new ArrayList<String>(set);
    }
    private boolean searching(char[][] board, String w, int m, int n, int i, int j, int start) {
        if (start == w.length()) return true;
        if (i < 0 || j < 0 || i >= m || j >= n) return false;
        if (board[i][j] != w.charAt(start)) return false;

        board[i][j] ^= 256;

        boolean found = searching(board, w, m, n, i - 1, j, start + 1)
                    || searching(board, w, m, n, i, j - 1, start + 1)
                    || searching(board, w, m, n, i + 1, j, start + 1)
                    || searching(board, w, m, n, i, j + 1, start + 1);

        board[i][j] ^= 256;

        return found;
    }

    /*
        Searching word... emmmmm. what's pop in now?

            Trie Tree !!! 
    */
    static final int ALPHABET_SIZE = 26;
    class TrieNode {
        TrieNode[] children;
        String word;
        public TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
            word = null;
        }
    }   

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = new TrieNode();
        List<String> res = new ArrayList<>();
        int m = board.length, n = board[0].length;
        
        // build up the tree
        for (String w : words)
            insert(root, w);
            
        // df-searching
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                searching(res, root, board, m, n, i, j);
        }
        return res;

    }
    private void insert(TrieNode root, String word) {
        TrieNode pCrawl = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';

            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();

            pCrawl = pCrawl.children[index];
        }

        pCrawl.word = word; // inddicates a complete word
    }
    private void searching(List<String> res, TrieNode root, char[][] board, int m, int n, int i, int j) {
        char c = board[i][j];
        if (c - 0 > 256 || root.children[c - 'a'] == null) return;

        TrieNode cur = root.children[c - 'a'];
        if (cur.word != null) {
            res.add(cur.word);
            cur.word = null; // de-dupe
        }

        board[i][j] ^= 256; // bit mask

        if (i > 0) searching(res, cur, board, m, n, i - 1, j);
        if (j > 0) searching(res, cur, board, m, n, i, j - 1);
        if (i < m - 1) searching(res, cur, board, m, n, i + 1, j);
        if (j < n - 1) searching(res, cur, board, m, n, i, j + 1);

        board[i][j] ^= 256;
    }
}
