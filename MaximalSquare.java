public class MaximalSquare {
	/*
		find the largest square of '1's 
		return its area

		1 0 1 0 0
	 	1 0 1 1 1
		1 1 1 1 1
		1 0 0 1 0

		draw a dp table to explain

				0 0 0 0 0 0 <- dummy row
				0 1 0 1 0 0 
				0 1 0 1 1 1
				0 1 1 1 2 2
				0 1 0 0 1 0
				^
	 dummy column

	*/
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int m = matrix.length, n = matrix[0].length, max = 0;
		int[][] dp = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (matrix[i - 1][j - 1] == '1') {
					// find the minimum side among UL, U, L
					dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]) + 1;
					max = Math.max(max, dp[i][j]); // update the maximum SIDE length
				}
			}
		}    	
		return max * max; // get area 
    }
}