public class SudokuSolver {
    /*
        Backtracking Solution
    */
    public void solveSudoku(char[][] board) {
        solver(board);
    }

    private boolean solver(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if (board[i][j] == '.') { // fill '.'
                    for (char c = '1'; c < '9'; c++) { // try from 0~9 for '.'
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (solver(board)) // start DFS
                                return true;
                            else
                                board[i][j] = '.'; //put back '.' backtracking
                        }

                    }
                    return false; // if 0~9 all invalid
                }
            }
        }
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;
            if (board[i][col] == c) return false;
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c) return false;
        }
        return true;
    }

    /*
        rewrite the solver() function
        makes it more recursive style
    */
    private boolean solver(char[][] board, int i, int j) {
        if (i == 9 && j == 0) return true; // all rows checked -> true
        if (board[i][j] != '.') // skip non '.'
            solver(board, j + 1 == 9 ? i + 1 : i, j + 1 == 9 ? 0 : j + 1);

        for (char c = '1'; c <= '9'; c++) {
            if (isValid(board, i, j, c)) {
                board[i][j] = c;
                if (solver(board, j + 1 == 9 ? i + 1 : i,
                    j + 1 == 9 ? 0 : j + 1)) // go DFS
                    return true;
                board[i][j] = '.'; // put '.' back and backtrack
            }
        }
        return false;
    }

}
