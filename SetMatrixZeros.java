public class SetMatrixZeros {
    /*
        use first row and column as marker
    */
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int col = 0;
        while (col < n && matrix[0][col] != 0) col++;
        // check if first row has zero

        for (int i = 1; i < m; i++)
            for (int j = 0; j < n; j++)
                if (matrix[i][j] == 0)
                    matrix[0][j] = matrix[i][0] = 0;
        // start from second row, set cells on 1st row and col to zero if see 0

        for (int i = 1; i < m; i++)
            for (int j = n - 1; j >= 0; j--) // AVOID influence the later values
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;
        // start from second row, set zero if 1st row OR 1st col cells is 0

        if (col < n) Arrays.fill(matrix[0], 0);
        // fill the first row to 0 if needed
    }
}
