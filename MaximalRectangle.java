public class MaximalRectangle {

    /*
            1 0 1 0 0
            1 0 1 1 1
            1 1 1 1 1
            1 0 0 1 0

        Brute Force Way:
            Time: O(N^3)

    */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        int[] heights = new int[n];
        Arrays.fill(right, n);
        // iterate all rows
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            int cur_left = 0, cur_right = n;
            for (int j = 0; j < n; j++) { // compute height
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            for (int j = 0; j < n; j++) { // compute left boundary
                if (matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], cur_left);
                } else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) { // compute right boundary
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], cur_right);
                } else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            for (int j = 0; j < n; j++) { //compute area
                max = Math.max(max, heights[j] * (right[j] - left[j]));
            }
        }
        return max;
    }

    /*
        OR we can also implement this function by using
        LargestRectangularInHistogram problem
    */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length;
        int[] heights = new int[n + 1];
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            heights[n] = -1;
            max = Math.max(max, largestRectangleArea(heights));
        }
        return max;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<Integer> index = new Stack<>();
        // index.push(-1); // avoid empty stack
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!index.isEmpty() && heights[index.peek()] > heights[i]) {
                int h = heights[index.pop()];
                int sidx = index.isEmpty() ? -1 : index.peek();
                max = Math.max(max, h * (i - sidx - 1));
            }
            index.push(i);
        }
        // while (index.peek() >= 0)
            // max = Math.max(max, heights[index.pop()] * (heights.length - index.peek() - 1));
        return max;
    }


}
