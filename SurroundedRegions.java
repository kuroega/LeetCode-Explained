public class SurroundedRegions {
    // two pass
    public void solve(char[][] board) {
        if (board.length < 2) return;
        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            check(board, i, 0, m, n); // check first column
            if (n > 1)
                check(board, i, n - 1, m, n); // check last column
        }

        for (int j = 0; j < n; j++) {
            check(board, 0, j, m, n); // check first row
            if (m > 1)
                check(board, m - 1, j, m, n); // check last row
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '1') board[i][j] = 'O'; // recover
                else if (board[i][j] == 'O') board[i][j] = 'X'; // O-region
            }
        }
    }
    private void check(char[][] board, int i, int j, int m, int n) {
        if (board[i][j] == 'O') {
            board[i][j] = '1';
            if (i > 0) check(board, i - 1, j, m, n);
            if (j > 0) check(board, i, j - 1, m, n);
            if (i + 1 < m) check(board, i + 1, j, m, n);
            if (j + 1 < n) check(board, i, j + 1, m, n);
        }
    }
}
