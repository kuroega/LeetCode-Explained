public class Nqueens {
    /*
        Backtracking Solution

        1 string array for all columns -> (col)
        1 boolean array for all cells -> (row, col)
        1 boolean array for 45° diagonal -> (row + col)
        1 boolean array for 135° diagonal -> (row - col + n) or (row - col + n - 1) here we use the former
            for each column, try all the rows (cells), backtrack
    */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        String[] board = new String[n];
        char[] initial = new char[n]; // only used to initialize the str[]
        Arrays.fill(initial, '.');
        Arrays.fill(board, String.valueOf(initial));

        boolean cols[] = new boolean[n];
        boolean diag1[] = new boolean[n * 2]; // 45°
        boolean diag2[] = new boolean[n * 2]; // 135°

        solver(res, board, cols, diag1, diag2, n, 0);

        return res;
    }
    private void solver(List<List<String>> res, String[] board, boolean[] cols, boolean[] diag1, boolean[] diag2, int n, int row) {
        if (row == n) {
            res.add(new ArrayList<String>(Arrays.asList(board)));
            return;
        } // base case

        for (int col = 0; col < n; col++) { // iterate all rows
            if (cols[col] || diag1[row + col] || diag2[row - col + n]) continue;
            // check if is a valid cell

            char[] cur = board[row].toCharArray(); // extract row
            cur[col] = 'Q'; // update cell
            board[row] = String.valueOf(cur); // put row back
            // fill in, put 'Q' in
            cols[col] = true; diag1[row + col] = true; diag2[row - col + n] = true;

            solver(res, board, cols, diag1, diag2, n, row + 1);
            // backtrack

            cur = board[row].toCharArray();
            cur[col] = '.';
            board[row] = String.valueOf(cur);
            // not work, put '.' back
            cols[col] = false; diag1[row + col] = false; diag2[row - col + n] = false;
        }
    }

    /*
        Follow-Up:
        ONLY return the total number of distinct N-Queens configuration
    */
    private int counter = 0; // make it global
    public int totalNQueens(int n) {
        boolean cols[] = new boolean[n];
        boolean diag1[] = new boolean[n * 2]; // 45°
        boolean diag2[] = new boolean[n * 2]; // 135°

        solver(cols, diag1, diag2, n, 0); // do not prapagate counter in recursion
        return counter;
    }
    private void solver(boolean[] cols, boolean[] diag1, boolean[] diag2, int n, int row) {
        if (row == n) {
            counter++;
            return;
        } // base case

        for (int col = 0; col < n; col++) { // iterate all rows
            if (cols[col] || diag1[row + col] || diag2[row - col + n]) continue;
            cols[col] = true; diag1[row + col] = true; diag2[row - col + n] = true;
            solver(cols, diag1, diag2, n, row + 1);
            cols[col] = false; diag1[row + col] = false; diag2[row - col + n] = false;
        }
    }
}
