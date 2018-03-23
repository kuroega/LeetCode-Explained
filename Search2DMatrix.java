public class Search2DMatrix {
    /*

    [
        [1,   3,  5,  7],
        [10, 11, 16, 20],
        [23, 30, 34, 50]
    ]

    Top-Down + LR binary search
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        if (matrix[0].length == 0) return false;

        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1;
        while (top <= bottom) {
            int middle = top + (bottom - top) / 2;
            if (matrix[middle][0] == target) return true;
            else if (matrix[middle][0] < target)
                top++;
            else bottom--;
        } // bottom is the row target may be in
        if (bottom < 0) bottom = 0; // for single row cases
        System.out.println(bottom);
        int left = 0, right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (matrix[bottom][middle] == target) return true;
            else if (matrix[bottom][middle] < target)
                left++;
            else right--;
        }
        return false;
    }
}
