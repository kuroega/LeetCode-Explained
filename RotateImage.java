public class RotateImage {
    /*
        Given input matrix =
            [   0  1  2  3
            0 [ 5, 1, 9,11],    (0,0) -> (0,3)     (0,1) -> (1,3)
            1 [ 2, 4, 8,10],      ↑        ↓         ↑        ↓
            2 [13, 3, 6, 7],    (3,0) <- (3,3)     (2,0) <- (3,2)
            3 [15,14,12,16]
            ],                  (0,2) -> (2,3)     (0,i)    (i,3)
                                  ↑        ↓
                                (1,0) <- (3,1)    (3-i,0)  (3,3-i)


                                (1,1) (1,2)   (1,i) (i,2)
                                (2,1) (2,2)  (3-i,1) (2,3-i)


        rotate the input matrix in-place such that it becomes:
            [   0  1  2  3
            0 [15,13, 2, 5],
            1 [14, 3, 4, 1],
            2 [12, 6, 8, 9],
            3 [16, 7,10,11]
            ]
    */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // from outside to inside

        // most out
        int start = 0; //0 1
        int end = n - 1; // 3 2
        while (start < end) {
            for (int i = start; i < end; i++) {
                swap4(matrix, i, n, start, end);
            }
            start++;
            end--;
        }
    }

    private void swap4(int[][] matrix, int i, int n, int start, int end) {
        int tmp = matrix[start][i];

        matrix[start][i] = matrix[n - 1 - i][start]; // top left
        matrix[n - 1 - i][start] = matrix[end][n - 1 - i]; // bottom left
        matrix[end][n - 1 - i] = matrix[i][end]; // bottom right
        matrix[i][end] = tmp; // top right

    }
}
