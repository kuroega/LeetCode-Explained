public class Search2DMatrix {
    /*

    [
        [1,   3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 50]
    ]
    
    Top-Down + LR binary search

    -> treat as a sorted 1D array 
            m = 3, n = 4
        -> [[1,   3,  5,  7], [10, 11, 16, 20], [23, 30, 34, 50] ]
            00.  01. 02. 03.   10. 11  12. 13.   20. 21. 22. 23
             0    1.  2.  3.    4.  5.  6.  7.   8.  9.  10  11
        index = x * n + y
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int x1 = 0, y1 = 0, x2 = m - 1, y2 = n - 1;
        while ((x1 * n + y1) <= (x2 * n + y2)) {
            int midx = x1 + (x2 - x1) / 2; 
            int midy = y1 + (y2 - y1) / 2;
            if (target == matrix[midx][midy]) {
                return true;
            } else if (target > matrix[midx][midy]) {
                y1++;
                if (y1 == n) {
                    y1 = 0;
                    x1++;
                }
            } else {
                y2--;
                if (y2 < 0) {
                    y2 = n - 1;
                    x2--;
                }
            }
        }
        return false;
    }

    /*
        Search 2D Matrix II
        Follow-Up:
            rows sorted, colums sorted
            nothing else

            [
              [1,   4,  7, 11, 15],
              [2,   5,  8, 12, 19],
              [3,   6,  9, 16, 22],
              [10, 13, 14, 17, 24],
              [18, 21, 23, 26, 30]
            ]
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (target == matrix[x][y]) {
                return true;
            } else if (target > matrix[x][y] ) {
                x++; // not in this row, go down
            } else {
                y--; // not in this col, go left
            }
        }
        return false;
    }
    /*
        we start from the top right corner
        we either go down or left depend on the targer compare to m[x][y]
            if target > m[x][y] => no possible in current row, go down
            if target < m[x][y] => no possible in current col, go left

        the reason why we do not go right or up is becase why we went left or down : )
    
    */
}

