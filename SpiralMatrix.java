public class SpiralMatrix {

    /*
        m * n matrix

        [x] n * n

        layer by layer => ugly code
    */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;
        int wid = matrix.length;
        int len = matrix[0].length;
        int n = wid * len;

        int row = 0, col = 0;
        int level = 0;
        while (wid > 0 && len > 0) {
            while (col - level < len) {
                res.add(matrix[row][col]);
                col++;
            } // →
            if (res.size() == n) return res;

            col--;
            row++;
            while (row - level < wid) {
                res.add(matrix[row][col]);
                row++;
            } // ↓
            if (res.size() == n) return res;

            row--;
            col--;
            while (col >= level) {
                res.add(matrix[row][col]);
                col--;
            } // ←
            if (res.size() == n) return res;

            level++;
            col++;
            row--;
            wid -= 2;
            while (wid > 0 && row >= level) {
                res.add(matrix[row][col]);
                row--;
            } // ↑
            if (res.size() == n) return res;

            row++;
            len -= 2;
            col++;
        }
        return res;
    }

    /*
        Consice version
        layer by layer
    */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;

        int row_min = 0, row_max = matrix.length - 1;
        int col_min = 0, col_max = matrix[0].length - 1;

        while (row_max >= row_min && col_max >= col_min) {
            for (int c = col_min; c <= col_max; c++)
                res.add(matrix[row_min][c]);
            for (int r = row_min + 1; r <= row_max; r++)
                res.add(matrix[r][col_max]);
            if (row_max > row_min && col_max > col_min) { // avoid single column/ row cases
                for (int c = col_max - 1; c >= col_min; c--)
                    res.add(matrix[row_max][c]);
                for (int r = row_max - 1; r > row_min; r--)
                    res.add(matrix[r][col_min]);
            }
            row_min++;
            row_max--;
            col_min++;
            col_max--;
        }
        return res;
    }

    /*
        Follow-Up: SpiralMatrix II
        given positive number n, generate a square matrix in order of spiral
        from 1 to n^2
    */
    public int[][] generateMatrix(int n) {
        if (n < 0) return null; // n = 0 is valid to initialize an array
        int[][] res = new int[n][n];

        int sq_min = 0, sq_max = n - 1;
        int num = 1;

        while (sq_min <= sq_max) {
            for (int col = sq_min; col <= sq_max; col++) {
                res[sq_min][col] = num++;
            }
            for (int row = sq_min + 1; row <= sq_max; row++) {
                res[row][sq_max] = num++;
            }
            for (int col = sq_max - 1; col >= sq_min; col--) {
                res[sq_max][col] = num++;
            }
            for (int row = sq_max - 1; row > sq_min; row--) {
                res[row][sq_min] = num++;
            }
            sq_min++;
            sq_max--;
        }
        return res;
    }

}
