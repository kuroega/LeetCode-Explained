public class ValidSudoku {
    /*
        Brute Force Solution:
            1. check each row
            2. check each column
            3. check each cube
    */


    /*
        Smarter Solution
        Use HashSet
            HashSet<> has very helpful methods: add()
            return true if not already contains
    */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> row = new HashSet<>();
            Set<Character> column = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.' && !row.add(board[i][j]))
                    return false;
                    // check ith row
                if (board[j][i] != '.' && !column.add(board[j][i]))
                    return false;
                    // check ith column
                int roffset = (i / 3) * 3;
                int coffset = (i % 3) * 3;
                if (board[roffset + j / 3][coffset + j % 3] != '.' &&
                    !cube.add(board[roffset + j / 3][coffset + j % 3]))
                    return false;
                    // check cube
            }
// row 0 1 2 for row + 0
// row 3 4 5 for row + 3
// row 6 7 8 for row + 6

// row 0 3 6 for col + 0
// row 1 4 7 for col + 3
// row 2 5 8 for col + 6

        }
        return true;
    }
}
